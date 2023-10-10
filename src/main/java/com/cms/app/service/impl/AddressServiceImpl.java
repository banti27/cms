package com.cms.app.service.impl;

import com.cms.app.dto.AddressInfoDto;
import com.cms.app.dto.AddressSearchResponseDto;
import com.cms.app.dto.ClientInfoDto;
import com.cms.app.entity.Address;
import com.cms.app.repository.AddressRepository;
import com.cms.app.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressSearchResponseDto findByAddressId(Long addressId) {
        Optional<Address> optionalAddress = this.addressRepository.findById(addressId);

        if (optionalAddress.isPresent()) {

            Address address = optionalAddress.get();

            AddressInfoDto addressInfoDto = getAddressInfoDtoFromAddressEntity(address);

            AddressSearchResponseDto response = new AddressSearchResponseDto();
            response.setAddressInfo(addressInfoDto);

            address.getClients()
                    .stream()
                    .map(entity -> {
                                ClientInfoDto clientInfoDto = new ClientInfoDto();
                                clientInfoDto.setAddresses(null);
                                clientInfoDto.setClientName(entity.getClientName());
                                clientInfoDto.setClientId(entity.getClientId());
                                return clientInfoDto;
                            }
                    )
                    .forEach(clientInfoDto -> response.getClientsInfo().add(clientInfoDto));
            return response;
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Address not found");
    }

    private static AddressInfoDto getAddressInfoDtoFromAddressEntity(Address address) {
        AddressInfoDto addressInfoDto = new AddressInfoDto();
        addressInfoDto.setAddressId(address.getAddressId());
        addressInfoDto.setCity(address.getCity());
        addressInfoDto.setState(address.getState());
        addressInfoDto.setCountry(address.getCountry());
        addressInfoDto.setZipCode(address.getZipCode());
        addressInfoDto.setStreet3(address.getStreet3());
        addressInfoDto.setStreet2(address.getStreet2());
        addressInfoDto.setStreet1(address.getStreet1());
        return addressInfoDto;
    }
}
