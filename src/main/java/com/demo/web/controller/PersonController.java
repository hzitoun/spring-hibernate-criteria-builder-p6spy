package com.demo.web.controller;

import com.demo.business.IPersonManager;
import com.demo.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/person", produces = "application/json")
public class PersonController {

    private final IPersonManager manager;

    @Autowired
    public PersonController(IPersonManager manager) {
        this.manager = manager;
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
