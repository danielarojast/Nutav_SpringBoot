package com.riwi.nutav.api.controller;


import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.nutav.api.dto.response.ClientResp;
import com.riwi.nutav.infraestructure.abstract_service.IClientService;
import com.riwi.nutav.utils.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/clients")
@AllArgsConstructor
public class ClientController {
    private final IClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.clientService.getAll(page - 1, size, sortType));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.clientService.get(id));
    }

    @PostMapping
    public ResponseEntity<ClientResp> insert(
            @Validated @RequestBody ClientRequest request) {
        return ResponseEntity.ok(this.clientService.create(request));
    }

}


/*en el getAll es necesario poner el sortTypesi no lo voy a organizar por orden alfabetico*/