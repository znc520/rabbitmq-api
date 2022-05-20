package com.znc.rabbitmqapi.exchange.direct;

import com.rabbitmq.client.Channel;
import com.znc.rabbitmqapi.utils.RabbitMqUtils;

/**
 * @Author zhunc
 * @Date 2022/5/13 15:04
 */
public class ProducerDirectExchange {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机名称
        String exchangeName = "test_direct_exchange";
        //声明路由key
        String routingKey = "test_direct";
        for (int i = 0; i < 3; i++) {
            String message = i + ": hello world rabbitmq direct exchange message";
            channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
        }

        RabbitMqUtils.close();
    }
}
