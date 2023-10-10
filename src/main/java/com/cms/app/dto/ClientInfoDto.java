package com.cms.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientInfoDto {
    private Long clientId;
    private String clientName;
    private List<AddressInfoDto> addresses = new ArrayList<>();
}
