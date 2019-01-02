package dev.fcodeapi.controllers;

import dev.fcodeapi.entities.CourseEntity;
import dev.fcodeapi.repositories.CourseRepository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepository cr;

    @GetMapping
    public ResponseEntity getAllCourse()
    {
        if (!cr.findAll().isEmpty())
        {
           return new ResponseEntity(cr.findAll(),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity createACourse(@RequestBody Map<String,String> body)
    {
        CourseEntity ce = new CourseEntity();
        if (cr.findByCourseName(body.get("name")) != null) {
            ce.setCourseName(body.get("name"));
            cr.saveAndFlush(ce);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
