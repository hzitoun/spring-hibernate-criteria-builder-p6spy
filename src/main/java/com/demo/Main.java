package com.demo;

import com.demo.dao.IPersonSpringDao;
import com.demo.dao.impl.AbstractGenericDaoImpl;
import com.demo.entities.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;


@ComponentScan(basePackages = "com.demo")
public class Main {


    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericDaoImpl.class.getName());

    @Autowired
    private IPersonSpringDao criteriaDao;


    public static void main(final String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Main p = context.getBean(Main.class);
        p.start();
    }

    //we need a transaction for our EntityManager to work
    @Transactional
    public void start() {
        final Person person1 = new Person();
        person1.setName("Beautiful name!");
        this.criteriaDao.create(person1);
        Person read = this.criteriaDao.read(person1.getId());
        LOGGER.info(read.toString());
        LOGGER.info(this.criteriaDao.findByName("Beautiful").toString());
    }


}
