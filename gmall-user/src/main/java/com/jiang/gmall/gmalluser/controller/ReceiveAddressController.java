package com.jiang.gmall.gmalluser.controller;






import com.jiang.gmall.beans.UmsMemberReceiveAddress;
import com.jiang.gmall.service.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceiveAddressController {

    @Autowired
    private ReceiveAddressService receiveAddressService;

    @RequestMapping("/getAllReceiveAddress")
    public List<UmsMemberReceiveAddress> getAllReceiveAddress(){
        return receiveAddressService.getAllReceiveAddress();
    }

}
