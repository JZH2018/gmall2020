package com.jiang.gmall.service;



import com.jiang.gmall.beans.UmsMember;
import com.jiang.gmall.beans.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {

    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}
