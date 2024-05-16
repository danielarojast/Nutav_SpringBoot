package com.riwi.nutav.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.riwi.nutav.api.dto.errors.ErrorsResp;
import com.riwi.nutav.api.dto.request.GuideRequest;
import com.riwi.nutav.api.dto.response.GuideResp;
import com.riwi.nutav.infraestructure.abstract_service.IGuideService;
import com.riwi.nutav.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/guides")
@AllArgsConstructor
@Tag(name= "Guides")
public class GuideController {

    @Autowired
    private final IGuideService guideService;

    @Operation(
        summary = "Lista todos los guias con paginacion",
        description = "Debes enviar la pagina y el tamaño para recibir todos los guias corresponidnetes."
    )
    @GetMapping
    public ResponseEntity<Page<GuideResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.guideService.getAll(page - 1, size, sortType));
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
        summary = "Muestra el guia por Id",
        description = "Debes enviar el id del guia que deseas ver."
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<GuideResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.guideService.get(id));
    }

    @Operation(
        summary = "Crea un nuevo guia",
        description = "Debes enviar name,lastname, age, gender, language, nationality, phone, email,experience,description,password, picture, documentType y identificationNumber, guide certificate."
    )
    @PostMapping
    public ResponseEntity<GuideResp> insert(
            @Validated @RequestBody GuideRequest request) {
        return ResponseEntity.ok(this.guideService.create(request));
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
        summary = "Actualiza la informacion de un guia existente",
        description = "Debes enviar el id del guia que deseas actualizar."
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<GuideResp> update(
            @Validated @RequestBody GuideRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.guideService.update(request, id));
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
        summary = "Elimina un guia con el id",
        description = "Debes enviar el id del guia que deseas eliminar."
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.guideService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
