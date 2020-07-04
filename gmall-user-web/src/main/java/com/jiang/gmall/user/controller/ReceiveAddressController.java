package com.jiang.gmall.user.controller;






import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.gmall.beans.UmsMemberReceiveAddress;
import com.jiang.gmall.service.ReceiveAddressService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceiveAddressController {

    @Reference
    private ReceiveAddressService receiveAddressService;

    @RequestMapping("/getAllReceiveAddress")
    public List<UmsMemberReceiveAddress> getAllReceiveAddress(){
        return receiveAddressService.getAllReceiveAddress();
    }

}
