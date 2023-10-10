package com.cms.app.controller;

import com.cms.app.dto.ClientSaveRequestDto;
import com.cms.app.dto.ClientSaveResponseDto;
import com.cms.app.dto.ClientSearchResponseDto;
import com.cms.app.dto.ClientInfoDto;
import com.cms.app.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientSaveResponseDto> addClient(
            @RequestBody ClientSaveRequestDto clientSaveRequestDto
    ) {
        log.info("START: saving client");

        ClientSaveResponseDto responseDto = this.clientService.saveClient(clientSaveRequestDto);

        log.info("END: saving client");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<ClientSearchResponseDto> getAllClients() {
        return ResponseEntity.ok(this.clientService.getAllClient());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientInfoDto> getClientById(
            @PathVariable Long clientId
    ) {
        return ResponseEntity.ok(this.clientService.getClientById(clientId));
    }


}
