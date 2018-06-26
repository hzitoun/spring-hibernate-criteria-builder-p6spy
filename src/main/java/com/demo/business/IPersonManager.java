package com.demo.business;

import com.demo.entities.Person;

public interface IPersonManager extends IGenericManager<Person, Integer> {


    Person findByName(String name);
}
