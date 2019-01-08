package dev.fcodeapi.controllers;

import dev.fcodeapi.entities.AccountEntity;
import dev.fcodeapi.repositories.AccountRepository;
import dev.fcodeapi.repositories.CourseRepository;
import dev.fcodeapi.repositories.MajorRepository;
import dev.fcodeapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository ar;

    @Autowired
    private CourseRepository cr;

    @Autowired
    private MajorRepository mr;

    @Autowired
    private RoleRepository rr;

//    @PostAuthorize("(returnObject.studentId == authentication.name)")
    @GetMapping("{id}")
    public ResponseEntity find(@PathVariable String id)
    {
        AccountEntity accountEntity = ar.findByStudentId(id);
        Map<String,Object> ae = new HashMap<>();
        ae.put("email",accountEntity.getEmail());
        ae.put("name",accountEntity.getName());
        ae.put("gender",accountEntity.isGender());
        ae.put("phone",accountEntity.getPhone());
        ae.put("dayOfBirth",accountEntity.getDayOfBirth());
        ae.put("address",accountEntity.getAddress());
        ae.put("aboutMe",accountEntity.getAboutMe());
        ae.put("linkFb",accountEntity.getLinkFb());
        ae.put("role",accountEntity.getRole().getRoleName());
        ae.put("course", accountEntity.getCourse().getCourseName());
        ae.put("major",accountEntity.getMajor().getMajorName());
        ae.put("active",accountEntity.getActive());
        ae.put("studentId",accountEntity.getStudentId());
        return new ResponseEntity(ae,HttpStatus.OK);
    }

    @GetMapping("name/{search}")
    public List<AccountEntity> findByLikeName(@PathVariable String search)
    {
        return ar.findByNameContaining(search);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity findAllAccount()
    {
        List<AccountEntity> listInDB = ar.findAll();
        if (listInDB.isEmpty()) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        List<Map<String,Object>> listAll = new ArrayList<>();
        for (AccountEntity accountEntity :listInDB) {
            Map<String,Object> ae = new HashMap<>();
            ae.put("email",accountEntity.getEmail());
            ae.put("name",accountEntity.getName());
            ae.put("gender",accountEntity.isGender());
            ae.put("phone",accountEntity.getPhone());
            ae.put("dayOfBirth",accountEntity.getDayOfBirth());
            ae.put("address",accountEntity.getAddress());
            ae.put("aboutMe",accountEntity.getAboutMe());
            ae.put("linkFb",accountEntity.getLinkFb());
            ae.put("role",accountEntity.getRole().getRoleName());
            ae.put("course", accountEntity.getCourse().getCourseName());
            ae.put("major",accountEntity.getMajor().getMajorName());
            ae.put("active",accountEntity.getActive());
            ae.put("studentId",accountEntity.getStudentId());
            listAll.add(ae);
        }
        return new ResponseEntity(listAll,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity editAccount(@PathVariable String id, @RequestBody Map<String, String> body)
    {
        if (ar.existsById(id)) {
            AccountEntity ae = ar.getOne(id);
            ae.setEmail(body.get("email"));
            ae.setLinkFb(body.get("linkFb"));
            ae.setName(body.get("name"));
            ae.setPhone(Integer.parseInt(body.get("phone")));
            ae.setGender(Boolean.parseBoolean(body.get("gender")));
            ae.setAddress(body.get("address"));
//            ae.setAboutMe(body.get("aboutMe"));
//            ae.setDayOfBirth(body.get("dayOfBirth"));
            ar.save(ae);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{id}/active")
    public ResponseEntity changeStatusAccount(@PathVariable String id, @RequestBody Map<String, String> body)
    {
        if (ar.existsById(id))
        {
            AccountEntity ae = ar.getOne(id);
            ae.setActive(Boolean.parseBoolean(body.get("isActive")));
            ar.save(ae);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("notNew")
    public List<AccountEntity> getListNotIsNewCommer(){
        return ar.findAllByRole_RoleIdNotLike(5);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body)
    {
        AccountEntity ae = ar.findByStudentIdAndPassword(body.get("studentId"), body.get("password"));
        if (ae == null)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PostMapping("signUp")
    public ResponseEntity createAccount(@RequestBody Map<String,String> body)
    {
        AccountEntity ae = new AccountEntity();

        for (String key : body.keySet()) {
            if (key.equals("studentId")) ae.setStudentId(body.get(key));
            if (key.equals("password")) ae.setPassword(body.get(key));
            if (key.equals("name")) ae.setName(body.get(key));
            if (key.equals("course")) ae.setCourse(cr.getOne(Integer.parseInt(body.get(key))));
            if (key.equals("major")) ae.setMajor(mr.getOne(Integer.parseInt(body.get(key))));
            if (key.equals("gender")) ae.setGender(Boolean.parseBoolean(body.get(key)));
            if (key.equals("email")) ae.setEmail(body.get(key));
            System.out.println(key + " : " + body.get(key));
        }
        ae.setActive(true);
        ae.setRole(rr.getOne(4));
        ae.setDayJoin(new Timestamp(new Date().getTime()));
        System.out.println(ae.getDayJoin());
        if (!ar.existsById(ae.getStudentId()))
        {
            ar.saveAndFlush(ae);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

//    @PutMapping("about/{id}")
//    public ResponseEntity updateAboutMe(@PathVariable String id, @RequestBody Map<String, String> body)
//    {
//        if (ar.existsById(id)) {
//            AccountEntity ae = ar.getOne(id);
//            ae.setAboutMe(body.get("aboutMe"));
//            ar.save(ae);
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }



}
