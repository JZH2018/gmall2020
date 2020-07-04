package com.jiang.gmall.user.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.gmall.beans.UmsMember;
import com.jiang.gmall.beans.UmsMemberReceiveAddress;


import com.jiang.gmall.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    @Reference
    UserService userService;

    @RequestMapping("/getAllUser")
    public List<UmsMember> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping("/getReceiveAddressByMemberId")
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        return userService.getReceiveAddressByMemberId(memberId);
    }

    @RequestMapping("/index")
    public String index() {
        return "This is test";
    }

}
