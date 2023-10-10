package com.cms.app.controller;

import com.cms.app.dto.AddressSearchResponseDto;
import com.cms.app.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {


    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressSearchResponseDto> getAddress(
            @PathVariable Long addressId
    ) {
        return ResponseEntity.ok(this.addressService.findByAddressId(addressId));
    }

}
