package com.demo.test;


import com.demo.dao.conf.TestSpringJpaConf;
import com.demo.dao.IPersonDao;
import com.demo.entities.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringJpaConf.class, loader = AnnotationConfigContextLoader.class)
@DirtiesContext
@ActiveProfiles("debug")
public class PersonDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDaoTest.class);

    @Autowired
    @Qualifier("personDaoImpl")
    IPersonDao dao;


    @Before
    public void setUp() {
        final Person testPerson = new Person();
        testPerson.setName("Beautiful name");
        this.dao.create(testPerson);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void whenIdIsNullThrowException() {
        this.dao.read(null);
    }

    @Test
    public final void whenNameContainsBeautifulPersonFound() {
        final Person mockedPerson = spy(new Person());
        when(mockedPerson.getId()).thenReturn(1);
        when(mockedPerson.getName()).thenReturn("Beautiful name");
        final Person found = this.dao.findByName("Beautiful");
        System.out.println(found);
        assertNotNull(found);
        assertThat(found.getId(), equalTo(mockedPerson.getId()));
    }

}
