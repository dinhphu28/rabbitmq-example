package com.idb.messagemq.Models.Client.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZaloMessageSenderResponseModel {
    private Integer status;

    private String message;
}
