package com.demo;

import com.demo.business.IPersonManager;
import com.demo.dao.IPersonDao;
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
    private IPersonManager manager;


    public static void main(final String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Main p = context.getBean(Main.class);
        p.start();
    }

 
    public void start() {
        final Person person = new Person();
        person.setName("Beautiful name!");
        this.manager.create(person);
        Person loadedPerson = this.manager.read(person.getId());
        LOGGER.info(loadedPerson.toString());
        LOGGER.info(this.manager.findByName("Beautiful").toString());
    }


}
