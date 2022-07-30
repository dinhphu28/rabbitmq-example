package com.idb.messagemq.Models.Client.API.ZaloMessageSenderRequestModelPkg;

import com.idb.messagemq.Models.Client.API.ZaloMessageSenderRequestModelPkg.ZaloMessagePkg.DefaultAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZaloMessage {
    private String title;

    private String subtitle;

    private String image_url;

    private DefaultAction default_action;
}
