package com.znc.rabbitmqapi.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author zhunc
 * @Date 2022/5/13 15:03
 */
public class RabbitMqUtils {
     private static  ConnectionFactory connectionFactory;
     private static  Connection connection;
     private static  Channel channel;
    static {
        connectionFactory = new ConnectionFactory();
        //1.创建connectionFactory 并进行配置
        connectionFactory.setHost("192.168.1.101");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        connectionFactory.setVirtualHost("my_host");
        //2.通过连接工厂创建连接
        try {
             connection = connectionFactory.newConnection();
             channel = connection.createChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Channel getChannel() throws  Exception{

        //3.通过连接创建channel
        return channel;
    }

    public static void close() throws Exception {
        channel.close();
        connection.close();
    }
}
