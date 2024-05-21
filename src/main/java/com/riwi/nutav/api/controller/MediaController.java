package com.riwi.nutav.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.nutav.api.dto.errors.ErrorsResp;
import com.riwi.nutav.api.dto.request.MediaRequest;
import com.riwi.nutav.api.dto.response.MediaResp;
import com.riwi.nutav.infraestructure.abstract_service.IMediaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/medias")
@AllArgsConstructor
@CrossOrigin(origins="*")
public class MediaController {

    private final IMediaService mediaService;

    @Operation(
        summary = "Crea un nuevo archivo media (foto/video)",
        description = "Debes enviar url y tourId"
    )
    @PostMapping
    public ResponseEntity<MediaResp> insert(
            @Validated @RequestBody MediaRequest request) {
        return ResponseEntity.ok(this.mediaService.create(request));
    }

    @ApiResponse(
        responseCode = "400",
        description = "Cuando el id no es valido",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorsResp.class)
            )
        }
    )
    @Operation(
        summary = "Elimina un archivo media con el id",
        description = "Debes enviar el id de la media que deseas eliminar."
    )
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
