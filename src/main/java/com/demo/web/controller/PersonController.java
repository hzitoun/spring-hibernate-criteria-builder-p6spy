package com.demo.web.controller;

import com.demo.business.IPersonManager;
import com.demo.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/person", produces = "application/json")
public class PersonController {

    @Autowired
    private IPersonManager manager;


    @GetMapping("/hello")
    public String test(){
        return "hello";
    }


    @PostMapping("/create")
    public Person create(@RequestBody Person person) {
        this.manager.create(person);
        return person;
    }

    @GetMapping("/read")
    public Person read(@RequestParam Integer personId) {
        return this.manager.read(personId);
    }
}
