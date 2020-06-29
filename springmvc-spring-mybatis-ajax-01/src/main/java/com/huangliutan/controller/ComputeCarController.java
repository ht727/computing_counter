package com.huangliutan.controller;

import com.huangliutan.entity.Records;
import com.huangliutan.entity.RequestParam;
import com.huangliutan.service.ComputeService;
import com.huangliutan.service.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ComputeCarController {
    @Autowired
    ComputeService computeService;

    @Autowired
    SaveService saveService;
    //响应ajax请求
    @RequestMapping("/compute.do")
    @ResponseBody
    public Object result(RequestParam requestParam, HttpServletRequest request){//对象取参 

        RequestParam computeParam = new RequestParam();
        computeParam.setPay(requestParam.getPay());
        computeParam.setLoan(requestParam.getLoan());
        computeParam.setBorrow(requestParam.getBorrow());
        computeParam.setPeriod(requestParam.getPeriod());
        computeParam.setInterestRate(requestParam.getInterestRate());
        computeParam.setRent(requestParam.getRent());
        computeParam.setCost(requestParam.getCost());    

        //调用方法处理数据
        List<Records> computes = computeService.compute(requestParam);
        
        //为了存储数据
        HttpSession session =request.getSession();       
        session.setAttribute("list", computes);
        
        return  computes;       
    }
    
    //存储结果
    @RequestMapping("/save.do")
    public void save(HttpServletRequest request,String save){
        //获取session中的数据
        HttpSession session = request.getSession();
        List<Records> list = (List) session.getAttribute("list");
        if (list != null) {
            for (Records record : list) {
                saveService.insert(record);
            }
        }
    }
}
