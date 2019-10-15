package com.timeline.controller;

import com.timeline.VO.UserVo;
import com.timeline.dataModel.UserModel;
import com.timeline.error.BussinessException;
import com.timeline.response.CommonReturnType;
import com.timeline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public CommonReturnType login(@RequestBody Map<String, Object> params) throws BussinessException {
        String email = (String) params.get("email");
        String password = (String) params.get("password");
        UserModel userModel = userService.validateLogin(email, password);
        return CommonReturnType.create(userModel);
    }

}
