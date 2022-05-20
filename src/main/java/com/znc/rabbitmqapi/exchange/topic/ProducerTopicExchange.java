package com.znc.rabbitmqapi.exchange.topic;

import com.rabbitmq.client.Channel;
import com.znc.rabbitmqapi.utils.RabbitMqUtils;

/**
 * @Author zhunc
 * @Date 2022/5/13 15:04
 */
public class ProducerTopicExchange {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机名称
        String exchangeName = "test_topic_exchange";
        //声明路由key
        String routingKey1 = "user.apple";
        String routingKey2 = "user.orange.yellow";
        String routingKey3 = "user.znc.sdf";
        String routingKey4 = "znc.user";

        String message = ": hello world rabbitmq topic exchange message";
        channel.basicPublish(exchangeName, routingKey1, null, (routingKey1+message).getBytes());
        channel.basicPublish(exchangeName, routingKey2, null, (routingKey2+message).getBytes());
        channel.basicPublish(exchangeName, routingKey3, null, (routingKey3+message).getBytes());
        channel.basicPublish(exchangeName, routingKey4, null, (routingKey4+message).getBytes());

        RabbitMqUtils.close();
    }
}
