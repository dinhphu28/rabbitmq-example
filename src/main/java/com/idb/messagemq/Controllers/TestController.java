package com.idb.messagemq.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.messagemq.Models.NotificationRabbitMQModel;
import com.idb.messagemq.Models.Client.API.ZaloMessageSenderResponseModel;
import com.idb.messagemq.Models.Client.API.ZaloMessageSenderRequestModelPkg.ZaloMessage;
import com.idb.messagemq.Models.Client.API.ZaloMessageSenderRequestModelPkg.ZaloMessagePkg.DefaultAction;
import com.idb.messagemq.Services.ZaloMessageSender;
import com.idb.messagemq.Utils.ZaloNotificationSender;

@RestController("/api/v1/test")
public class TestController {
    @Autowired
    private ZaloMessageSender zaloMessageSender;

    @Autowired
    private ZaloNotificationSender zaloNotificationSender;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> sendZaloMessages() {
        ResponseEntity<Object> entity;

        String[] arrZaloId = {"6507142651538755728"};
        List<String> listZaloId = Arrays.asList(arrZaloId);

        List<ZaloMessage> zaloMessages = new ArrayList<ZaloMessage>();

        ZaloMessage zaloMessage = new ZaloMessage("This is title", "This is subtitle", "https://stc-developers.zdn.vn/images/bg_1.jpg", new DefaultAction("oa.open.url", "https://developers.zalo.me/docs/api/official-account-api-147"));
        zaloMessages.add(zaloMessage);

        ZaloMessageSenderResponseModel tmp = zaloMessageSender.SendMessages(listZaloId, zaloMessages);

        entity = new ResponseEntity<>(tmp, HttpStatus.OK);

        return entity;
    }

    @GetMapping(
        value = "/zxc",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> sendZaloMessagesFromRabbitMQ() {
        ResponseEntity<Object> entity;

        Boolean isSuccess = zaloNotificationSender.SendMessageZaloNotification();

        entity = new ResponseEntity<>(isSuccess, HttpStatus.OK);

        return entity;
    }
}
