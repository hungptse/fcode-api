package dev.fcodeapi.controllers;

import dev.fcodeapi.entities.EventEntity;
import dev.fcodeapi.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepository er;

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

    @GetMapping("publish")
    public List<EventEntity> publishEvent()
    {
        return er.findAllByPublishIsTrue();
    }


}
