package com.jiang.gmall.gmalluser.service.Impl;





import com.jiang.gmall.beans.UmsMemberReceiveAddress;
import com.jiang.gmall.gmalluser.mapper.ReceiveAddressMapper;
import com.jiang.gmall.service.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
