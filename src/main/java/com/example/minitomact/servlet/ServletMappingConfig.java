package com.example.minitomact.servlet;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/13 18:14
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet配置
 */
public class ServletMappingConfig {

    public static List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();


    static {
        servletMappingList.add(new ServletMapping("HelloWorld","/","com.example.minitomact.servlet.HelloWorldServlet"));
        servletMappingList.add(new ServletMapping("Time","/time","com.example.minitomact.servlet.TimeServlet"));
    }
}
