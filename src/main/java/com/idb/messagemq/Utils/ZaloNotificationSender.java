package com.idb.messagemq.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idb.messagemq.Models.NotificationRabbitMQModel;
import com.idb.messagemq.Models.Client.API.ZaloMessageSenderResponseModel;
import com.idb.messagemq.Models.Client.API.ZaloMessageSenderRequestModelPkg.ZaloMessage;
import com.idb.messagemq.Models.Client.API.ZaloMessageSenderRequestModelPkg.ZaloMessagePkg.DefaultAction;
import com.idb.messagemq.Services.RabbitMQSender;
import com.idb.messagemq.Services.ZaloMessageSender;

@Component
public class ZaloNotificationSender {
    @Autowired
	RabbitMQSender rabbitMQSender;

    @Autowired
    private ZaloMessageSender zaloMessageSender;

    Logger LOGGER = LoggerFactory.getLogger(ZaloNotificationSender.class);

    public Boolean SendMessageZaloNotification() {
        NotificationRabbitMQModel message = rabbitMQSender.receiveAndConvertToPojoZaloMsg();

        Boolean isSuccess = false;

        if(message != null) {
            String zaloIdsStr = message.getZaloID();

            String[] zaloIdsArr = zaloIdsStr.split(",");

            List<String> zaloIdsList = Arrays.asList(zaloIdsArr);

            List<ZaloMessage> zaloMessages = new ArrayList<ZaloMessage>();
            ZaloMessage zaloMessage = new ZaloMessage(message.getTitle() + " | " + message.getStatus(), message.getContent() + " | " + message.getUrl(), "", new DefaultAction("oa.open.url", message.getUrl()));
            zaloMessages.add(zaloMessage);

            ZaloMessageSenderResponseModel tmp = zaloMessageSender.SendMessages(zaloIdsList, zaloMessages);

            isSuccess = true;
        }

        return isSuccess;
    }

    public Integer SendBulkMessageZaloNotification() {
        Integer noSuccess = 0;
        Integer noFail = 0;

        List<NotificationRabbitMQModel> messages = rabbitMQSender.receiveAndConvertToPojoZaloMsgs();

        for (NotificationRabbitMQModel item : messages) {
            String zaloIdsStr = item.getZaloID();

            String[] zaloIdsArr = zaloIdsStr.split(",");

            List<String> zaloIdsList = Arrays.asList(zaloIdsArr);

            List<ZaloMessage> zaloMessages = new ArrayList<ZaloMessage>();
            ZaloMessage zaloMessage = new ZaloMessage(item.getTitle() + " - " + item.getStatus(), item.getContent() + " | " + item.getUrl(), "https://stc-developers.zdn.vn/images/bg_1.jpg", new DefaultAction("oa.open.url", item.getUrl()));
            zaloMessages.add(zaloMessage);

            ZaloMessageSenderResponseModel tmp = zaloMessageSender.SendMessages(zaloIdsList, zaloMessages);

            if(tmp != null) {
                noSuccess++;
            } else {
                noFail++;
            }
        }

        LOGGER.info("Send zalo message fails: " + noFail);

        return noSuccess;
    }
}
