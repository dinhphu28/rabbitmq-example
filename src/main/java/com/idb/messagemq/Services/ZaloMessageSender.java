package com.idb.messagemq.Services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.idb.messagemq.Models.Client.API.ZaloMessageSenderRequestModel;
import com.idb.messagemq.Models.Client.API.ZaloMessageSenderResponseModel;
import com.idb.messagemq.Models.Client.API.ZaloMessageSenderRequestModelPkg.ZaloMessage;

@Component
public class ZaloMessageSender {

    @Value("${idb.external-resource.zalo-api}")
    // private String API_URL = "https://api-thtruemilk.ringbot.co/Joget/zalonotification";
    private String API_URL;

    public ZaloMessageSenderResponseModel SendMessages(List<String> zaloIds, List<ZaloMessage> zaloMessages) {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
        headers.set("Authorization", "Basic 123aaAAww111dAAA2231");

        ZaloMessageSenderRequestModel body = new ZaloMessageSenderRequestModel(zaloIds, zaloMessages);

        HttpEntity<ZaloMessageSenderRequestModel> entity = new HttpEntity<ZaloMessageSenderRequestModel>(body, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ZaloMessageSenderResponseModel> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, ZaloMessageSenderResponseModel.class);

        HttpStatus statusCode = response.getStatusCode();

        ZaloMessageSenderResponseModel responseModel = null; // = new ZaloMessageSenderResponseModel();

        if(statusCode == HttpStatus.OK) {
            responseModel = response.getBody();
        }

        return responseModel;
    }
}
