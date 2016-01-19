package com.dev.controllers;

import com.dev.configs.BaseConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Common resource test. Performs preparations for mvc testing in Spring.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseConfig.class})
@WebAppConfiguration
public abstract class ResourceTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    
    protected static final RestTemplate API = new RestTemplate();

    public static final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    protected MockMvc mockMvc;
    protected ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        mockMvc = standaloneSetup(getController()).build();
    }
    protected abstract Object getController();

}
