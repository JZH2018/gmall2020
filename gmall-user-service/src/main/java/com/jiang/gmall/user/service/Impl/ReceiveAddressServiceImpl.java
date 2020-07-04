package com.jiang.gmall.user.service.Impl;





import com.alibaba.dubbo.config.annotation.Service;
import com.jiang.gmall.beans.UmsMemberReceiveAddress;

import com.jiang.gmall.service.ReceiveAddressService;
import com.jiang.gmall.user.mapper.ReceiveAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class ReceiveAddressServiceImpl implements ReceiveAddressService {

    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;

    @Override
    public List<UmsMemberReceiveAddress> getAllReceiveAddress() {
        return receiveAddressMapper.selectAll();
    }
}
