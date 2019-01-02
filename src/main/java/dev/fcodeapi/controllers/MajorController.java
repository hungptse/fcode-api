package dev.fcodeapi.controllers;

import dev.fcodeapi.repositories.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private MajorRepository mr;

    @GetMapping
    public ResponseEntity getAllMajor()
    {
        if (!mr.findAll().isEmpty())
        {
           return new ResponseEntity(mr.findAll(),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
