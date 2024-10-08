package com.example.minitomact.http;

import lombok.Data;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 *  封装响应对象
 *  基于HTTP协议的格式进行输出写入。
 * @date 2024/8/13 18:01
 */
@Data
public class MyResponse {
    private OutputStream outputStream;
    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        //构建StringBuffer输出
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n")
                .append("<html><body>")
                .append(content)
                .append("<body><html>");
        //输出
        outputStream.write(httpResponse.toString().getBytes());
        //关闭
        outputStream.close();
    }


}
