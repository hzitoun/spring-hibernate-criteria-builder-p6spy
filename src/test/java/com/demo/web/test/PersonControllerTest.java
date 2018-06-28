package com.demo.web.test;


import com.demo.business.IPersonManager;
import com.demo.entities.Person;
import com.demo.web.conf.SpringWebTestConf;
import com.demo.web.controller.PersonController;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringWebTestConf.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class PersonControllerTest {


    // controller to test
    @InjectMocks
    private PersonController controller;

    // manager to mock
    @Mock
    private IPersonManager manager;


    private MockMvc mvc;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void createUserSuccessfully() throws Exception {
        final Person mock = new Person();
       mock.setName("wonderful name");
        final MvcResult mvcResult = this.mvc.perform(post("/person/create")
                .content(asJsonString(mock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(this.manager, times(1)).create(any());
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);

    }


    @Test
    public void readUserNotFoundReturnsNull() throws Exception {
        final MvcResult mvcResult = this.mvc.perform(get("/person/hello"))
                .andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(content);
    }


    private String asJsonString(final Person person) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            final String jsonContent = mapper.writeValueAsString(person);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
