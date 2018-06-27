package com.demo.web.test;


import com.demo.web.conf.SpringWebTestConf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringWebTestConf.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class PersonControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;


    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    public void readUserNotFoundReturnsNull() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/person/read"))
                .andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(content);
    }
}
