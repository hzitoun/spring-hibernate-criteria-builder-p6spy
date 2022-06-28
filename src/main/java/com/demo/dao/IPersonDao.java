package com.demo.dao;

import com.demo.entities.Person;

public interface IPersonDao extends IGenericDao<Person, Integer> {

    Person findByName(String name);

}

