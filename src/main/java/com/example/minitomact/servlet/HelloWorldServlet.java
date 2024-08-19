package com.example.minitomact.servlet;

import com.example.minitomact.http.MyRequest;
import com.example.minitomact.http.MyResponse;

import java.io.IOException;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/13 18:04
 */
public class HelloWorldServlet extends MyServlet{
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try{
            myResponse.write("Welcome to myTomcat!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try{
            myResponse.write("Hello service world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
