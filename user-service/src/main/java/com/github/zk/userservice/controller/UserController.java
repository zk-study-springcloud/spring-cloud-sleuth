package com.github.zk.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaokai
 * @date 2019-09-15 20:55
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/hi")
    public String hi() {
        return "i`m zk";
    }
}
