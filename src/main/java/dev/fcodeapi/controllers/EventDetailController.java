package dev.fcodeapi.controllers;

import dev.fcodeapi.entities.*;
import dev.fcodeapi.repositories.AccountEventRepository;
import dev.fcodeapi.repositories.AttendanceRepository;
import dev.fcodeapi.repositories.EventDetailRepository;
import dev.fcodeapi.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("detail")
public class EventDetailController {

    @Autowired
    private EventDetailRepository edr;

    @Autowired
    private EventRepository er;

    @Autowired
    private AccountEventRepository aer;

    @Autowired
    private AttendanceRepository ar;

    @GetMapping("{detail}")
    public EventDetailEntity getAttendanceByEventDetail(@PathVariable String detail){
        return edr.findByEventDetail(Integer.parseInt(detail));
    }

    @GetMapping("all/{event}")
    public ResponseEntity getListDetailByEventId(@PathVariable String event)
    {
        List<EventDetailEntity> list = (List<EventDetailEntity>) er.findByEventId(Integer.parseInt(event)).getEventDetailsByEventId();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("{event}")
    public ResponseEntity createDetail(@RequestBody Map<String,String> body, @PathVariable String event)
    {
        EventEntity ee = er.getOne(Integer.parseInt(event));
        String name = body.get("detailName");
        String date = body.get("date");
        EventDetailEntity ede = new EventDetailEntity();
        ede.setDateEvent(Timestamp.valueOf(date));
        ede.setDetailName(name);
        ede.setEventByEventId(ee);
        List<AccountEventEntity> listAccountEvent = aer.findAllByEvent_EventId(Integer.parseInt(event));
        List<AccountEntity> listAccount = new ArrayList<>();
        for (AccountEventEntity accountEvent :listAccountEvent) {
            if (accountEvent.getStatus() != null) {
                listAccount.add(accountEvent.getAccount());
            }
        }
        if (!listAccount.isEmpty())
        {
            edr.saveAndFlush(ede);
            for (int i = 0; i < listAccount.size(); i++) {
                AttendanceEntity ae = new AttendanceEntity();
                ae.setNote("");
                ae.setStudentId(listAccount.get(i).getStudentId());
                ae.setEventDetailByEventDetail(ede);
                ae.setPresent(false);
                ar.save(ae);
            }
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
