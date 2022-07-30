package com.idb.messagemq.Scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.idb.messagemq.Utils.ZaloNotificationSender;

@Component
public class ZaloSendNotificationFromRabbitMQScheduler {
    @Autowired
    private ZaloNotificationSender zaloNotificationSender;

    Logger LOGGER = LoggerFactory.getLogger(ZaloSendNotificationFromRabbitMQScheduler.class);

    // @Scheduled(cron = "0 0/1 * * * ?")
    @Scheduled(cron = "${idb.properties.schedule.cron-expression}")
    public void SendBulkZaloMessages() {
        Integer noSuccess = zaloNotificationSender.SendBulkMessageZaloNotification();

        LOGGER.info("Send zalo messages success: " + noSuccess);
    }

    public void SendZaloMessage() {
        Boolean isSuccess = zaloNotificationSender.SendMessageZaloNotification();

        if(isSuccess) {
            LOGGER.info("Send zalo message successfully!");
        } else {
            LOGGER.info("There is no message to send in queue!");
        }
    }
}
