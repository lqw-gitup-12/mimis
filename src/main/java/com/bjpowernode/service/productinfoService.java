package com.bjpowernode.service;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.vo.ProductVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface productinfoService {
    List<ProductInfo>  selectALL();
    PageInfo<ProductInfo> selectPage(int k,int J);
    int  save(ProductInfo pro);
    ProductInfo seek_ID(int  id);
    int  update(ProductInfo pro);
    int delete_one(int id);
    PageInfo<ProductInfo> selectPageseek(ProductVo o);
}
