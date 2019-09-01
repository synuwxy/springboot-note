package com.synuwxy.springbootnote.jpa.web.controller;

import com.synuwxy.springbootnote.common.ResultCode;
import com.synuwxy.springbootnote.common.ResultObject;
import com.synuwxy.springbootnote.jpa.entity.User;
import com.synuwxy.springbootnote.jpa.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/jpa")
public class JpaController {

    private final UserService userService;

    @Autowired
    public JpaController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/select")
    public Map<String, Object> selectUsers() {
        return ResultObject.newInstance(ResultCode.SUCCESS,userService.findAll());
    }

    @GetMapping("/add")
    public Map<String, Object> addUser(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "name", defaultValue = "guest") String name,
            @RequestParam(value = "username", defaultValue = "guest") String username,
            @RequestParam(value = "password", defaultValue = "guest") String password) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        return ResultObject.newInstance(ResultCode.SUCCESS,userService.save(user));
    }
}