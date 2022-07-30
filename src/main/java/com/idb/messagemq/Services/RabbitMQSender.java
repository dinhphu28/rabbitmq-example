package com.idb.messagemq.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.idb.messagemq.Models.NotificationRabbitMQModel;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${idb.properties.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${idb.properties.rabbitmq.routingkey}")
	private String routingkey;

    @Value("${idb.properties.rabbitmq.queue}")
    private String queue;

    Logger LOGGER = LoggerFactory.getLogger(RabbitMQSender.class);

    public void sendZalo(NotificationRabbitMQModel noti) {
        amqpTemplate.convertAndSend(exchange, routingkey, noti);
        System.out.println("Send msg = " + noti);
    }

    /*
     * Stable
     */
    public NotificationRabbitMQModel receiveAndConvertToPojoZaloMsg() {

        NotificationRabbitMQModel tmp = null;

        try {
            tmp = (NotificationRabbitMQModel)amqpTemplate.receiveAndConvert(queue);
        } catch (Exception e) {

        }

        return tmp;
    }

    public List<NotificationRabbitMQModel> receiveAndConvertToPojoZaloMsgs() {

        List<NotificationRabbitMQModel> messages = new ArrayList<NotificationRabbitMQModel>();

        Boolean isStop = false;

        do {
            try {
                NotificationRabbitMQModel tmp = (NotificationRabbitMQModel)amqpTemplate.receiveAndConvert(queue);

                if(tmp == null) {
                    isStop = true;

                    LOGGER.info("There is no message in Rabbit MQ queue");
                } else {
                    messages.add(tmp);
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
        } while (!isStop);

        return messages;
    }
}
