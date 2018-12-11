package dev.fcodeapi.controllers;

import dev.fcodeapi.entities.AccountEntity;
import dev.fcodeapi.entities.AccountEventEntity;
import dev.fcodeapi.entities.EventEntity;
import dev.fcodeapi.repositories.AccountEventRepository;
import dev.fcodeapi.repositories.AccountRepository;
import dev.fcodeapi.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepository er;

    @Autowired
    private AccountRepository ar;

    @Autowired
    private AccountEventRepository aer;

    @GetMapping("type/{type}")
    public List<EventEntity> getEventByType(@PathVariable int type){
        return er.findAllByType_TypeId(type);
    }

    @GetMapping
    public List<EventEntity> getAll()
    {
        return er.findAll();
    }

    @GetMapping("{id}")
    public Optional<EventEntity> getAEvent(@PathVariable String id)
    {
        return er.findById(Integer.parseInt(id));
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody Map<String,String> body)
    {
        System.out.println(body.get("type"));
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{id}")
    public ResponseEntity updateEvent(@RequestBody Map<String,String> body, @PathVariable String id)
    {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("publish")
    public List<EventEntity> publishEvent()
    {
        return er.findAllByPublishIsTrue();
    }


    @PostMapping("{event}/{studentId}")
    public ResponseEntity joinEvent(@PathVariable String event, @PathVariable String studentId)
    {
        AccountEntity ae = ar.findByStudentId(studentId);
        EventEntity ee = er.getOne(Integer.parseInt(event));

        AccountEventEntity aee = aer.findByEvent_EventIdAndAccount_StudentId(Integer.parseInt(event), studentId);
        if (aee == null)
        {
            AccountEventEntity newAee = new AccountEventEntity();
            newAee.setAccount(ae);
            newAee.setEvent(ee);
            aer.save(newAee);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("pending/{event}")
    public ResponseEntity getPendingList(@PathVariable String event)
    {
        Map<String, Object> map = new HashMap<>();
        List<AccountEventEntity> listAccountEvent = aer.findAllByEvent_EventIdAndStatusIsNull(Integer.parseInt(event));
        List<AccountEntity> listAccount = new ArrayList<>();
        if (listAccountEvent.size() == 0)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        for (AccountEventEntity aee : listAccountEvent) {
            listAccount.add(aee.getAccount());
        }
        map.put("account",listAccountEvent);
        map.put("event", getEventByType(3));
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @PutMapping("approve/{event}/{studentId}")
    public ResponseEntity approveJoin(@PathVariable String event, @PathVariable String studentId)
    {
        AccountEntity ae = ar.findByStudentId(studentId);
        EventEntity ee = er.getOne(Integer.parseInt(event));

        AccountEventEntity aee = aer.findByEvent_EventIdAndAccount_StudentId(Integer.parseInt(event), studentId);
        if (aee != null)
        {
            aee.setStatus(true);
            aer.save(aee);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("reject/{event}/{studentId}")
    public ResponseEntity rejectJoin(@PathVariable String event, @PathVariable String studentId)
    {
        AccountEntity ae = ar.findByStudentId(studentId);
        EventEntity ee = er.getOne(Integer.parseInt(event));

        AccountEventEntity aee = aer.findByEvent_EventIdAndAccount_StudentId(Integer.parseInt(event), studentId);
        if (aee != null)
        {
            aee.setStatus(false);
            aer.save(aee);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }




}
