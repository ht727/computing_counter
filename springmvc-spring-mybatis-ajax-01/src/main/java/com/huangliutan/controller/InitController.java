package com.huangliutan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

@Controller
public class InitController {
    //静态初始化，类加载执行
    public Properties init() {
        //读取配置文件信息
        InputStream config = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/config.properties");
        //使用property集合加载流数据
        Properties properties = new Properties();
        //使用 properties.load(config）出现中文乱码，可以使用转换流，将字节流转换成字符流，同时指定转换文件编码方式
        try {
            properties.load(new InputStreamReader(config, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    //void，不是视图也不是返回值，目的就是响应ajax请求
    @RequestMapping("/mor.do")
    public void initMor(HttpServletResponse response) {
        //读取资源信息
        //抵押方式
        Properties properties = init();
        String mortgageType = properties.getProperty("mortgageType");

        //设置响应内容及响应字符集
        response.setContentType("application/json;charset=utf-8");

        //响应数据到浏览器
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(mortgageType);
        writer.flush();
        writer.close();
    }

    @RequestMapping("/pay.do")
    public void initPay(HttpServletResponse response) {
        //读取资源信息
        //抵押方式
        Properties properties = init();
        String payType = properties.getProperty("payType");

        //设置响应内容及响应字符集
        response.setContentType("application/json;charset=utf-8");

        //响应数据到浏览器
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(payType);
        writer.flush();
        writer.close();
    }

    @RequestMapping("/per.do")
    public void initPer(HttpServletResponse response) {
        //读取资源信息
        //抵押方式
        Properties properties = init();
        String periodNum = properties.getProperty("periodNum");

        //设置响应内容及响应字符集
        response.setContentType("application/json;charset=utf-8");

        //响应数据到浏览器
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(periodNum);
        writer.flush();
        writer.close();
    }

}
