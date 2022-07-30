package com.idb.messagemq.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRabbitMQModel {
    private String zaloID;

    private String url;

    private String content;

    private String title;

    private String status;

    private String id;

    private String type;
}
