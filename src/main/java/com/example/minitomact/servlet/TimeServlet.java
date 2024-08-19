package com.example.minitomact.servlet;

import com.example.minitomact.http.MyRequest;
import com.example.minitomact.http.MyResponse;

import java.io.IOException;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/13 18:17
 */
public class TimeServlet  extends MyServlet{
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try{
            myResponse.write("GET TIME");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try{
            myResponse.write("SERVICE TIME");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
