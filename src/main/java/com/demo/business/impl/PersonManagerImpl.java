package com.demo.business.impl;

import com.demo.business.IPersonManager;
import com.demo.dao.IGenericDao;
import com.demo.dao.IPersonDao;
import com.demo.entities.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonManagerImpl extends AbstractGenericManagerImpl<Person, Integer> implements IPersonManager {

    private final IPersonDao personDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericManagerImpl.class);

    @Autowired
    public PersonManagerImpl(IPersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    protected IGenericDao<Person, Integer> getDao() {
        return this.personDao;
    }

    @Override
    public Person findByName(String name) {
        LOGGER.info(this.getBusinessMessage("info.read.person.by.name"));
        return this.personDao.findByName(name);
    }
}
