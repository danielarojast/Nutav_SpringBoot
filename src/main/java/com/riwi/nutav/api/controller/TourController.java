package com.riwi.nutav.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.riwi.nutav.api.dto.request.TourRequest;
import com.riwi.nutav.api.dto.response.TourResp;
import com.riwi.nutav.infraestructure.abstract_service.ITourService;
import com.riwi.nutav.utils.enums.CategoryTour;
import com.riwi.nutav.utils.enums.ChosenLanguage;
import com.riwi.nutav.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/tours")
@AllArgsConstructor
@Tag(name = "Tours")
@CrossOrigin(origins="*")
public class TourController {

    private final ITourService service;

    @Operation(
        summary = "Lista todos los tours con paginacion",
        description = "Debes enviar la pagina y el tamaño para recibir todos los clientes corresponidnetes."
    )
    @GetMapping
    public ResponseEntity<Page<TourResp>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestHeader(required = false) SortType sortType
    ){
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
    
        return ResponseEntity.ok(this.service.getAll(page -1, size, sortType));
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
        summary = "Muestra el tour por Id",
        description = "Debes enviar el id del tour que deseas ver."
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<TourResp> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.get(id));
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
        summary = "Elimina un tour con el id",
        description = "Debes enviar el id del tour que deseas eliminar."
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
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
        summary = "Actualiza la informacion de un tour existente",
        description = "Debes enviar el id del tour que deseas actualizar."
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<TourResp> update(
        @Validated @RequestBody TourRequest request,
        @PathVariable Long id 
    ){
        return ResponseEntity.ok(this.service.update(request, id));
    }

    @Operation(
        summary = "Crea un nuevo tour",
        description = "Debes enviar title,category, place, duration, language, description, price, guideId, media"
    )
    @PostMapping
    public ResponseEntity<TourResp> insert(
        @Validated @RequestBody TourRequest request
    ){
        return  ResponseEntity.ok(this.service.create(request));
    }

    @GetMapping(path = "/category/{category}")
    public ResponseEntity <List<TourResp>> getCategory(
            @PathVariable String category) {
        return ResponseEntity.ok(this.service.findByCategory(CategoryTour.valueOf(category)));
    }

    @GetMapping(path = "/language/{language}")
    public ResponseEntity <List<TourResp>> getLanguage(
            @PathVariable String language) {
        return ResponseEntity.ok(this.service.findByLanguage(ChosenLanguage.valueOf(language)));
    }

    @GetMapping(path = "/place/{place}")
    public ResponseEntity <List<TourResp>> getPlace(
            @PathVariable String place) {
        return ResponseEntity.ok(this.service.findByPlace(place));
    }
}
