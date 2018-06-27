package com.demo.business.test;


import com.demo.business.conf.SpringBusinessTestConfig;
import com.demo.business.impl.PersonManagerImpl;
import com.demo.dao.IPersonDao;
import com.demo.entities.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBusinessTestConfig.class, loader = AnnotationConfigContextLoader.class)
public class PersonManagerTest {


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private PersonManagerImpl manager;

    @Mock
    private IPersonDao dao;


    @Test(expected = IllegalArgumentException.class)
    public void whenIdIsNullThrowsException() {
        when(this.dao.read(null)).thenThrow(new IllegalArgumentException());
        this.manager.read(null);
    }


    @Test
    public void whenPersonExistsReturnsPerson() {
        final Person mock = Mockito.mock(Person.class);
        when(mock.getName()).thenReturn(UUID.randomUUID().toString());
        final int id = new Random().nextInt();
        when(mock.getId()).thenReturn(id);
        when(this.dao.read(id)).thenReturn(mock);
        assertThat(this.manager.read(id), equalTo(mock));
    }

}
