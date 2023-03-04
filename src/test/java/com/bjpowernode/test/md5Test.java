package com.bjpowernode.test;


import com.bjpowernode.utils.MD5Util;
import org.junit.Test;

public class md5Test {

    @Test
    public  void MD5test()
    {
        String md5 = MD5Util.getMD5("000");
        System.out.println(md5);
    }
}
