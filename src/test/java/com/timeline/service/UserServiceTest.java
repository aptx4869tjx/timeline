package com.timeline.service;

import com.timeline.TimelineApplication;
import com.timeline.dataModel.UserModel;
import com.timeline.error.BussinessException;
import com.timeline.service.Impl.UserServiceImpl;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TimelineApplication.class)
class UserServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;


    @Test
    void getUserModelByUserId() {
        UserModel userModel = userService.getUserModelByUserId(7);
        System.out.println(userModel.getEncryptPassword());
        assertEquals(userModel.getEmail(), "18717826762");
    }

    @Test
    void register() {
        UserModel userModel = new UserModel();
        userModel.setName("123");
        userModel.setGender((byte) 1);
        userModel.setEmail("18717826762");
        String password = "123456";
        String encryptPassword = UserServiceImpl.getMD5(password);
        userModel.setEncryptPassword(encryptPassword);
        try {
            userService.register(userModel);
        } catch (DataIntegrityViolationException | BussinessException e) {
            if (e instanceof DataIntegrityViolationException) {
                logger.error("手机号已经注册");
            } else {
                logger.error(((BussinessException) e).getErrorMessage());
            }
        }

    }

    @Test
    void validateLogin() {
        try {
            UserModel userModel = userService.validateLogin("18717826762", "123456");
            assertEquals("123", userModel.getName());
        } catch (BussinessException e) {
            System.out.println(e.getErrorMessage());
            e.printStackTrace();
        }
    }
}