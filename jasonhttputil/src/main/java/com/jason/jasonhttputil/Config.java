package com.jason.jasonhttputil;

public interface Config {
    String LINEND = "\r\n";// 换行符
    String PREFIX = "--";
    String BOUNDARY = java.util.UUID.randomUUID().toString();// 定义数据分隔线

}
