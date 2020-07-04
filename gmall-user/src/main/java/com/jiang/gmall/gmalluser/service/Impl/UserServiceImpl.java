package com.jiang.gmall.gmalluser.service.Impl;






import com.jiang.gmall.beans.UmsMember;
import com.jiang.gmall.beans.UmsMemberReceiveAddress;
import com.jiang.gmall.gmalluser.mapper.ReceiveAddressMapper;
import com.jiang.gmall.gmalluser.mapper.UserMapper;
import com.jiang.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;

    @Override
    public List<UmsMember> getAllUser() {
       //List<UmsMember> umsMembers = userMapper.selectAllUser();
        List<UmsMember> umsMembers = userMapper.selectAll();
        return umsMembers;
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        Example e= new Example(UmsMemberReceiveAddress.class);
        e.createCriteria().andEqualTo("memberId",memberId);

        return receiveAddressMapper.selectByExample(e);
    }

}
