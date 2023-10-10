package com.cms.app.service;

import com.cms.app.dto.ClientInfoDto;
import com.cms.app.dto.ClientSaveRequestDto;
import com.cms.app.dto.ClientSaveResponseDto;
import com.cms.app.dto.ClientSearchResponseDto;

public interface ClientService {
    ClientSaveResponseDto saveClient(ClientSaveRequestDto clientSaveRequestDto);

    ClientInfoDto getClientById(Long clientId);

    ClientSearchResponseDto getAllClient();
}
