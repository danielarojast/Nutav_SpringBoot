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

import com.riwi.nutav.api.dto.request.ReservationRequest;
import com.riwi.nutav.api.dto.response.ReservationResp;
import com.riwi.nutav.infraestructure.abstract_service.IReservationService;
import com.riwi.nutav.utils.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/reservations")
@AllArgsConstructor
public class ReservationController {
    
    private final IReservationService reservationService; 

    @GetMapping
    public ResponseEntity<Page<ReservationResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.reservationService.getAll(page - 1, size, sortType));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.reservationService.get(id));
    }


    @PostMapping
    public ResponseEntity<ReservationResp>insert(
        @Validated @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(this.reservationService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationResp> update(
            @Validated @RequestBody ReservationRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.reservationService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
    

    
}
