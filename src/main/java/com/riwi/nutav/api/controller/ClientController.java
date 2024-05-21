package com.riwi.nutav.api.controller;


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

import com.riwi.nutav.api.dto.response.ClientResp;
import com.riwi.nutav.api.dto.errors.ErrorsResp;
import com.riwi.nutav.api.dto.request.ClientRequest;
import com.riwi.nutav.infraestructure.abstract_service.IClientService;
import com.riwi.nutav.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/clients")
@AllArgsConstructor
@Tag(name= "Client")
@CrossOrigin(origins="*")
public class ClientController {
    private final IClientService clientService;

    /*---GET ALL---*/
    @Operation(
        summary = "Lista todos los clientes con paginacion",
        description = "Debes enviar la pagina y el tamaño para recibir todos los clientes corresponidnetes."
    )
    @GetMapping
    public ResponseEntity<Page<ClientResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.clientService.getAll(page - 1, size, sortType));
    }

    /*---GET BY ID---*/
    //@ApiResponse es la notacion para el error cuando no esta el id (es para la documentacion).
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
        summary = "Muestra el cliente por Id",
        description = "Debes enviar el id del cliente que deseas ver."
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.clientService.get(id));
    }

    /*---INSERT---*/
    @Operation(
        summary = "Crea un nuevo cliente",
        description = "Debes enviar name,lastname, age, gender, language, nationality, password, phone, email, picture, documentType y identificationNumber."
    )
    @PostMapping
    public ResponseEntity<ClientResp> insert(
            @Validated @RequestBody ClientRequest request) {
        return ResponseEntity.ok(this.clientService.create(request));
    }

    /*--- UPDATE ---*/
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
        summary = "Actualiza la informacion de un cliente existente",
        description = "Debes enviar el id del cliente que deseas actualizar."
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<ClientResp> update(
            @Validated @RequestBody ClientRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.clientService.update(request, id));
    }

    /*--- DELETE ---*/
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
        summary = "Elimina un cliente con el id",
        description = "Debes enviar el id del cliente que deseas eliminar."
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}