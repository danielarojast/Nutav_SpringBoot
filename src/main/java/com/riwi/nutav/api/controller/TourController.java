package com.riwi.nutav.api.controller;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.nutav.api.dto.request.TourRequest;
import com.riwi.nutav.api.dto.response.TourResp;
import com.riwi.nutav.infraestructure.abstract_service.ITourService;
import com.riwi.nutav.utils.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/tours")
@AllArgsConstructor
public class TourController {

    private final ITourService service;

    @GetMapping
    public ResponseEntity<Page<TourResp>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestHeader(required = false) SortType sortType
    ){
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
    
        return ResponseEntity.ok(this.service.getAll(page -1, size, sortType));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TourResp> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TourResp> update(
        @Validated @RequestBody TourRequest request,
        @PathVariable Long id 
    ){
        return ResponseEntity.ok(this.service.update(request, id));
    }

    @PostMapping
    public ResponseEntity<TourResp> insert(
        @Validated @RequestBody TourRequest request
    ){
        return  ResponseEntity.ok(this.service.create(request));
    }
}
