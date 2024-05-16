package com.riwi.nutav.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.riwi.nutav.api.dto.request.MediaRequest;
import com.riwi.nutav.api.dto.response.MediaResp;
import com.riwi.nutav.infraestructure.abstract_service.IMediaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/medias")
@AllArgsConstructor
public class MediaController {

    private final IMediaService mediaService;

    @PostMapping
    public ResponseEntity<MediaResp> insert(
            @Validated @RequestBody MediaRequest request) {
        return ResponseEntity.ok(this.mediaService.create(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MediaResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.mediaService.get(id));
    }
    
}
