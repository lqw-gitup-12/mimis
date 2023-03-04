package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.ProductInfoExample;
import com.bjpowernode.pojo.vo.ProductVo;
import com.bjpowernode.service.productinfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class productServiceimpl implements productinfoService {
    private final  static Integer  size=5;
     @Autowired
    private ProductInfoMapper  pro;
    @Override
    public List<ProductInfo> selectALL() {
        return pro.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo<ProductInfo> selectPage(int k, int J) {
        PageHelper.startPage(k,J);
        ProductInfoExample product = new ProductInfoExample();
        product.setOrderByClause("p_id desc");
        List<ProductInfo> list = pro.selectByExample(product);
        PageInfo<ProductInfo> pageIn =new PageInfo<>(list);
        return pageIn;
    }

    @Override
    public int save(ProductInfo pro1) {
       return  pro.insert(pro1);

    }

    @Override
    public ProductInfo seek_ID(int id) {
        return  pro.selectByPrimaryKey(id);
    }

    @Override
    public int update(ProductInfo pr) {
        return  pro.updateByPrimaryKeySelective(pr);
    }
    public int delete_one(int id)
    {
        return pro.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<ProductInfo> selectPageseek(ProductVo o) {
        PageHelper.startPage(o.getPage(),size);
        ProductInfoExample product = new ProductInfoExample();
        product.setOrderByClause("p_price,asc");
        List<ProductInfo> list = pro.selectConditionSplitPage(o);
        PageInfo<ProductInfo> pageIn =new PageInfo<>(list);
        return pageIn;
    }


}
