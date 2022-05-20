package com.znc.rabbitmqapi.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 *生产者
 * @Author zhunc
 * @Date 2022/5/13 9:56
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        //1.创建connectionFactory 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置服务地址
        connectionFactory.setHost("192.168.1.101");
        //设置端口号
        connectionFactory.setPort(5672);
        //用户名
        connectionFactory.setUsername("root");
        //密码
        connectionFactory.setPassword("root");
        //设置虚拟主机地址，一个用户可以有多个虚拟主机地址，要确保该用户虚拟主机是对的，可以在控制台界面查看用户的虚拟主机地址
        connectionFactory.setVirtualHost("my_host");
        //2.通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        //3.通过连接创建channel，使用通道才能完成消息相关的操作
        Channel channel = connection.createChannel();
        String queueName = "test001";
        //4.发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "hello rabbitmq!";
            // 向指定的队列中发送消息
            //参数：String exchange, String routingKey, BasicProperties props, byte[] body
            /**
             * 参数明细：
             * 1、exchange，交换机，如果不指定将使用mq的默认交换机（设置为""）
             * 2、routingKey，路由key，交换机根据路由key来将消息转发到指定的队列，如果使用默认交换机，routingKey设置为队列的名称
             * 3、props，消息的属性
             * 4、body，消息内容
             */
            channel.basicPublish("", queueName, null, msg.getBytes());
        }

        //5.关闭连接
        channel.close();
        connection.close();

    }
}
