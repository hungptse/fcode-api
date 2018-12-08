package dev.fcodeapi.controllers;

import dev.fcodeapi.entities.AccountEventEntity;
import dev.fcodeapi.entities.AttendanceEntity;
import dev.fcodeapi.entities.EventDetailEntity;
import dev.fcodeapi.entities.EventEntity;
import dev.fcodeapi.repositories.AccountEventRepository;
import dev.fcodeapi.repositories.AttendanceRepository;
import dev.fcodeapi.repositories.EventDetailRepository;
import dev.fcodeapi.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepository ar;

    @Autowired
    private AccountEventRepository aer;

    @Autowired
    private EventRepository er;

    @GetMapping("{studentId}")
    public List<EventDetailEntity> getAttendanceByDetail(@PathVariable String studentId)
    {
        List<AccountEventEntity> listEventAccount = aer.findAllByAccount_StudentId(studentId);
        List<Integer> listEventId = new ArrayList<>();
        for (AccountEventEntity  ae : listEventAccount) {
            listEventId.add(ae.getEvent().getEventId());
        }
        List<EventDetailEntity> listDetail = new ArrayList<>();
        List<EventEntity> listEvent = er.findAllById(listEventId);
        for (EventEntity event : listEvent) {
            listDetail.addAll(event.getEventDetailsByEventId());
        }
        return listDetail;
    }

    @GetMapping
    public List<AttendanceEntity> getAll()
    {
        return ar.findAll();
    }

}
