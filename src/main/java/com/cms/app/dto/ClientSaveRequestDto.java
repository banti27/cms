package com.cms.app.dto;

import lombok.Data;

@Data
public class ClientSaveRequestDto {

    // CLIENT
    private String clientName;

    // ADDRESS
    private Long addressId;
    private String street1;
    private String street2;
    private String street3;
    private String city;
    private String state;
    private Integer zipCode;
    private String country;
}
