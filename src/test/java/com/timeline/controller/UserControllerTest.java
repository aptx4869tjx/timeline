package com.timeline.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.timeline.TimelineApplication;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TimelineApplication.class)
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void testLogin() throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("email", "1002376198@qq.com");
        map.put("password", "123456");
        String json = JSONObject.toJSONString(map);
        mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void testLogout() throws Exception {
        mockMvc.perform(post("/user/logout"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testRegister() throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("username","test1");
        map.put("email", "1002376198@qq.com");
        map.put("password", "123456");
        String json = JSONObject.toJSONString(map);
        mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isOk())
                .andDo(print());
        map.put("email","2233@33");
        json = JSONObject.toJSONString(map);
        mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testGetAllMessage() throws Exception {
        mockMvc.perform(get("/user/messages/0").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get("/user/messages/110").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());
    }
}