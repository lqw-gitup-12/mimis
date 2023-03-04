package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.service.adminService;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class adminServiceimpl implements adminService {
    @Autowired
    private AdminMapper adma;
    @Override
    public Admin seekuser(Admin ad){
        AdminExample adminExam = new AdminExample();
        String s = ad.getaPass();
        String md5 = MD5Util.getMD5(s);
        adminExam.createCriteria().andANameEqualTo(ad.getaName()).andAPassEqualTo(md5);
        List<Admin> admins = adma.selectByExample(adminExam);
        if(admins!=null && admins.size()>0)
        {
            return  admins.get(0);
        }
        return  null;
    }
}
