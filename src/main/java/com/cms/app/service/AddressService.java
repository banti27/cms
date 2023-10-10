package com.cms.app.service;

import com.cms.app.dto.AddressSearchResponseDto;

public interface AddressService {
    AddressSearchResponseDto findByAddressId(Long addressId);
}
