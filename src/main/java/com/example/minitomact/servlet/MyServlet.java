package com.example.minitomact.servlet;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/13 18:04
 */

import com.example.minitomact.http.MyRequest;
import com.example.minitomact.http.MyResponse;

/**
 * Tomcat是满足servlet规范的容器，提供servlet需求的API，如doGet，doPost，service等
 */
public abstract class MyServlet {

    public abstract void doGet(MyRequest myRequest, MyResponse myResponse);
    public abstract void doPost(MyRequest myRequest, MyResponse myResponse);
    public void service(MyRequest myRequest,MyResponse myResponse){
        if(myRequest.getMethod().equalsIgnoreCase("POST")){
            doPost(myRequest,myResponse);
        }else if (myRequest.getMethod().equalsIgnoreCase("GET")){
            doGet(myRequest,myResponse);
        }
    }
}
