package com.cms.app.service.impl;

import com.cms.app.dto.*;
import com.cms.app.entity.Address;
import com.cms.app.entity.Client;
import com.cms.app.repository.AddressRepository;
import com.cms.app.repository.ClientRepository;
import com.cms.app.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public ClientServiceImpl(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public ClientSaveResponseDto saveClient(ClientSaveRequestDto clientSaveRequestDto) {
        // convert from DTO to ENTITY
        Address address = null;

        if (clientSaveRequestDto.getAddressId() != null) {
            log.info("Address Id: {}", clientSaveRequestDto.getAddressId());
            Optional<Address> optionalAddress = this.addressRepository.findById(clientSaveRequestDto.getAddressId());
            if (optionalAddress.isPresent()) {
                address = optionalAddress.get();
            }
        }

        if (address == null) {
            log.info("Address not found creating new address");
            address = new Address();
            address.setStreet1(clientSaveRequestDto.getStreet1());
            address.setCity(clientSaveRequestDto.getCity());
            address.setState(clientSaveRequestDto.getState());
            address.setCountry(clientSaveRequestDto.getCountry());
            address.setZipCode(clientSaveRequestDto.getZipCode());
        }


        Client clientEntity = new Client();
        clientEntity.setClientName(clientSaveRequestDto.getClientName());
        clientEntity.getAddresses().add(address);

        // save ENTITY to database using repository

        Client savedClientEntity = this.clientRepository.save(clientEntity);

        // create a response object and set the required info from saved entity object and return
        ClientSaveResponseDto responseDto = new ClientSaveResponseDto();
        responseDto.setClientId(savedClientEntity.getClientId());
        return responseDto;
    }

    @Override
    public ClientInfoDto getClientById(Long clientId) {
        // Get Client entity by ID
        Client clientEntity = this.clientRepository.getReferenceById(clientId);

        // Create a ClientInfoDto via Client entity object
        ClientInfoDto clientInfoDto = buildClientInfoDtoFromClientEntity(clientEntity);

        return clientInfoDto;
    }

    @Override
    public ClientSearchResponseDto getAllClient() {

        // Get All Clients
        // Convert or Map every Client entity object to ClientInfoDto ==> map(Function)
        // Collect to list
        List<ClientInfoDto> clients = this.clientRepository
                .findAll()
                .stream()
                .map(clientEntity -> buildClientInfoDtoFromClientEntity(clientEntity))
                .collect(Collectors.toList());

        // set client info list into the search response and return
        ClientSearchResponseDto clientSearchResponseDto = new ClientSearchResponseDto();
        clientSearchResponseDto.setClients(clients);
        return clientSearchResponseDto;
    }

    private static ClientInfoDto buildClientInfoDtoFromClientEntity(Client clientEntity) {
        ClientInfoDto clientInfoDto = new ClientInfoDto();
        clientInfoDto.setClientId(clientEntity.getClientId());
        clientInfoDto.setClientName(clientEntity.getClientName());

        clientEntity.getAddresses()
                .stream()
                .map(
                        entity -> {
                            AddressInfoDto addressInfoDto = new AddressInfoDto();
                            addressInfoDto.setAddressId(entity.getAddressId());
                            addressInfoDto.setStreet1(entity.getStreet1());
                            addressInfoDto.setStreet2(entity.getStreet2());
                            addressInfoDto.setStreet3(entity.getStreet3());
                            addressInfoDto.setCity(entity.getCity());
                            addressInfoDto.setState(entity.getState());
                            addressInfoDto.setCountry(entity.getCountry());
                            addressInfoDto.setZipCode(entity.getZipCode());
                            return addressInfoDto;
                        }
                )
                .forEach(addressInfoDto -> clientInfoDto.getAddresses().add(addressInfoDto));
        return clientInfoDto;
    }

}
