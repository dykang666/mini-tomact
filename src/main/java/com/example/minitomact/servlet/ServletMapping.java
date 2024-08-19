package com.example.minitomact.servlet;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/8/13 18:14
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * servlet映射类,封装自定义servlet的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServletMapping {
    //servlet别名
    private String servletName;
    //servlet的对应url
    private String url;
    //servlet的类
    private String clazz;
}
