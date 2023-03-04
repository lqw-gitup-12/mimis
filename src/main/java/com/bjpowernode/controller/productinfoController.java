package com.bjpowernode.controller;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.vo.ProductVo;
import com.bjpowernode.service.productinfoService;
import com.bjpowernode.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class productinfoController {
    private String filename="";
    @Autowired
    private productinfoService  produc;
    @RequestMapping("/selectall")//不分页
    public String  showspring(HttpServletRequest request)
    {
        List<ProductInfo> productInfos = produc.selectALL();
        request.setAttribute("lists",productInfos);
        return "product";
    }

    @RequestMapping("/plit")//实现第一页分页
    public String  selectsplit(ProductVo o,HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Object prVo = session.getAttribute("PrVo");
        PageInfo<ProductInfo> selectsome=null;
        if(prVo==null)
        {
            o.setPage(1);
            selectsome= produc.selectPageseek(o);
        } else{ selectsome = produc.selectPageseek((ProductVo) prVo);
        request.setAttribute("Prt",prVo);
          session.removeAttribute("PrVo");
        }
        request.setAttribute("info",selectsome);
        return "product";
    }

    @ResponseBody
    @RequestMapping("/ajaxsplit")//动态分页
    public void selectsplitbetter(ProductVo o,HttpServletRequest request)
    {
        PageInfo<ProductInfo> selectsome = produc.selectPageseek(o);
        HttpSession session = request.getSession();
        session.setAttribute("info",selectsome);
    }
    @ResponseBody
    @RequestMapping("/ajxImg")//添加信息时图片回显
    private String  returnimg(MultipartFile pimage ,HttpServletRequest request) throws IOException {
        //为文件创建为文件名并获取文件类型
         filename = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目的路径(从根目录开始到达项目包)， 并追加子目录   用来存放获取的文件
         String realPath = request.getServletContext().getRealPath("/image_big");
         //转存
         pimage.transferTo(new File(realPath+File.separator+filename));
        //这里的File.separator相当于/
         JSONObject  a=new JSONObject();
         a.put("imgurl",filename);
         return a.toString();
    }
    @RequestMapping("/save")//添加产品
    private String add( ProductInfo pro,HttpServletRequest request)
    {
        pro.setpImage(filename);
        pro.setpDate(new Date());
        int save = produc.save(pro);
        if(save>0)
        {
            request.setAttribute("msg","添加成功！");
        }else{
         request.setAttribute("msg","添加失败！");
        }
        filename="";
        return "forward:/prod/plit.action";
    }
    //修改时信息回显
    @RequestMapping("/one")
    private String showOne(int pid, ProductVo Vo,HttpServletRequest request)
    {
        ProductInfo o = produc.seek_ID(pid);
        request.setAttribute("prod",o);
        HttpSession session = request.getSession();
        session.setAttribute("PrVo",Vo);
        return "update";
    }
    //修改
    @RequestMapping("/update")
    private  String  update( ProductInfo  obj,HttpServletRequest request)
    {
        System.out.println("update");
        if(!filename.equals("")){
            obj.setpImage(filename);
        }
        int update = produc.update(obj);
        if(update>0)
        {
            request.setAttribute("msg","更新成功！");
        }
        else {
         request.setAttribute("msg","更新失败");
        }
        filename="";
        return "forward:/prod/plit.action";
    }
    @RequestMapping("/delete")
    public  String delone(Integer id,ProductVo Vo,HttpServletRequest request)
    {
       int i= produc.delete_one(id);
       if(i<0)
       {
           request.setAttribute("msg","删除失败！");
       }
       else {
           request.setAttribute("msg","删除成功！");
           request.getSession().setAttribute("PrVo",Vo);
       }
       return "forward:/prod/plit.action";
    }
    @RequestMapping("/deletebatch")
    public String deletes(String str)
    {
        String[] split = str.split(",");
        for(String s:split)
        {
            produc.delete_one(Integer.parseInt(s));
        }

        return "forward:/prod/plit.action";
    }
}
