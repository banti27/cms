package com.cms.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressInfoDto {
    private Long addressId;
    private String street1;
    private String street2;
    private String street3;
    private String city;
    private String state;
    private Integer zipCode;
    private String country;
}
