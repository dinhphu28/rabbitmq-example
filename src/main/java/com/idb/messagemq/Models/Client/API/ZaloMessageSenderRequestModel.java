package com.idb.messagemq.Models.Client.API;

import java.util.List;

import com.idb.messagemq.Models.Client.API.ZaloMessageSenderRequestModelPkg.ZaloMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZaloMessageSenderRequestModel {
    private List<String> in_list_id_user_zalo;

    private List<ZaloMessage> in_content_notification;
}
