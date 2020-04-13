package com.bishe.java.controller;


import com.bishe.java.pojo.User;
import com.bishe.java.service.UserService;
import com.bishe.java.util.ResponseError;
import com.bishe.java.util.ResponseOk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.sql.Wrapper;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2020-01-13
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(value = "*",maxAge = 3600)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){

        try {
            Boolean hasRegister = userService.hasRegister(user);
            if(hasRegister){
                return  ResponseError.create("注册失败，已被注册");
            }
            userService.save(user);
            return ResponseOk.create("注册成功");
        }catch (Exception e){
            return  ResponseError.create("注册失败："+e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){
        try {
            Boolean login = userService.login(user);
            if(login){
                return ResponseOk.create("登陆成功");
            }
            return  ResponseError.create("登录失败，请检查账号密码");
        }catch (Exception e){
            return  ResponseError.create("登录失败："+e.getMessage());
        }
    }


}

