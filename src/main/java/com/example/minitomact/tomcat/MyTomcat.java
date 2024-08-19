package com.example.minitomact.tomcat;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/13 18:19
 */

import com.example.minitomact.http.MyRequest;
import com.example.minitomact.http.MyResponse;
import com.example.minitomact.servlet.MyServlet;
import com.example.minitomact.servlet.ServletMapping;
import com.example.minitomact.servlet.ServletMappingConfig;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Tomcat类
 * Tomcat的处理流程：
 * 把URL对应处理的Servlet关系形成，解析HTTP协议，封装请求/响应对象，利用反射实例化具体的Servlet进行处理即可。
 */
public class MyTomcat {

    private int port = 8080;
    private Map<String,String> urlServletMap = new HashMap<String,String>();


    public MyTomcat(int port) {
        this.port = port;
    }
    //启动myTomcat
    public static void main(String[] args) {
        MyTomcat myTomcat = new MyTomcat(8080);
        myTomcat.start();
    }
    /**
     * Tomcat启动类
     * 1. 建立连接，封装request对象和response对象
     * 2. 利用反射，根据url找到对应的servlet进行处理
     * 3. 进行分发输出
     */
    public void start(){
        //初始化 url和servlet的对应关系，urlServletMap中
        initServletMapping();
        //建立ServerSocket通信，此类实现服务器套接字。服务器套接字等待请求通过网络进入。
        // 它根据该请求执行一些操作，然后可能将结果返回给请求者。

        try {
            ServerSocket serverSocket = null;
            serverSocket = new ServerSocket(port);
            System.out.println("MyTomcat starts");
            while (true) {
                //Socket是网络间两个进程通信的端点
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                //利用MyRequest封装request对象
                //利用MyRequest封装request对象
                MyRequest myRequest = new MyRequest(inputStream);
                //利用MyResponse封装response对象
                MyResponse myResponse = new MyResponse(outputStream);
                //请求分发
                dispatch(myRequest,myResponse);

                //关闭socket连接
                socket.close();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 根据request和response进行消息的分发
     * @param myRequest
     * @param myResponse
     */
    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
        //过滤浏览器的favicon.ico请求,否则报错，因为没有对应的servletMapping
        if(!myRequest.getUrl().equals("/favicon.ico")){
            //通过request中的url精确定位对应的servlet
            String clazz = urlServletMap.get(myRequest.getUrl());
            //获取Class对象


            try {
                Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
                //获取实例
                MyServlet myServlet = myServletClass.newInstance();
                //调用实例的方法进行输出
                myServlet.service(myRequest,myResponse);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }


    }

    /**
     * 初始化url和servletMapping，形成对应关系，根据url可以找到对应的servlet
     */
    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(),servletMapping.getClazz());
        }
    }


}
