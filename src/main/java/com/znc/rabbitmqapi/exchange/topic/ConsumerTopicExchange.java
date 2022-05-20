package com.znc.rabbitmqapi.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.znc.rabbitmqapi.utils.RabbitMqUtils;

/**
 * @Author zhunc
 * @Date 2022/5/13 15:05
 */
public class ConsumerTopicExchange {
    public static void main(String[] args) throws  Exception{
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机名称
        String exchangeName = "test_topic_exchange";
        //声明路由key
        String routingKey = "user.*";
        //交换机类型direct
        String exchangeType = "topic";
        //队列名称
        String queueName = "test_topic_queue";
        /**
         * 声明一个交换机
         * 参数明细
         * 1.exchange 交换机名称
         * 2.type 交换机类型 direct、topic、fanout、headers
         * 3.durable 是否需要持久化，true为持久化
         * 4.autoDelete 当最后一个绑定到Exchange上的队列删除后，自动删除改Exchange
         * 5.internal 当前Exchange是否用于内部使用、默认为false
         * 6.arguments 扩展参数、用于扩展AMQP协议自定化使用
         */
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        //声明一个队列
        channel.queueDeclare(queueName, false, false, false, null);
        //创建一个绑定关系
        channel.queueBind(queueName, exchangeName, routingKey);

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //6.设置channel
        // 监听队列，第二个参数：是否自动进行消息确认。
        //参数：String queue, boolean autoAck, Consumer callback
        /**
         * 参数明细：
         * 1、queue 队列名称
         * 2、autoAck 自动回复，当消费者接收到消息后要告诉mq消息已接收，如果将此参数设置为true表示会自动回复mq，如果设置为false要通过编程实现回复
         * 3、callback，消费方法，当消费者接收到消息要执行的方法
         */
        channel.basicConsume(queueName,true, queueingConsumer);
        while (true) {
            //7.获取消息

            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("消费端：" + msg);


        }
    }
}
