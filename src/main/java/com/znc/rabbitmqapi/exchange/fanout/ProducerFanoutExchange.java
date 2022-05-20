package com.znc.rabbitmqapi.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.znc.rabbitmqapi.utils.RabbitMqUtils;

/**
 * @Author zhunc
 * @Date 2022/5/13 15:04
 */
public class ProducerFanoutExchange {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机名称
        String exchangeName = "test_fanout_exchange";
        //声明路由key
        String routingKey = "";

        for (int i = 0; i < 5; i++) {
            String message =i+" hello world rabbitmq fanout exchange message";
            channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
        }

        RabbitMqUtils.close();
    }
}
