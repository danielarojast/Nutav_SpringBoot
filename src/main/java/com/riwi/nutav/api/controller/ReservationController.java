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
import com.riwi.nutav.api.dto.request.ReservationRequest;
import com.riwi.nutav.api.dto.response.ReservationResp;
import com.riwi.nutav.infraestructure.abstract_service.IReservationService;
import com.riwi.nutav.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/reservations")
@AllArgsConstructor
@Tag(name= "Reservation")
@CrossOrigin(origins="*")
public class ReservationController {
    
    private final IReservationService reservationService; 

    /*--- GET ALL ---*/
    @Operation(
        summary = "Lista todos las reservaciones paginadas",
        description = "Debes enviar el numero de pagina y el tamaño para que te liste las reservas correspondientes paginadas."
    )
    @GetMapping
    public ResponseEntity<Page<ReservationResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.reservationService.getAll(page - 1, size, sortType));
    }

    /*---GET BY ID---*/
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
        summary = "Muestra las reservas por Id",
        description = "Debes enviar el id de la reserva que deseas ver."
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.reservationService.get(id));
    }

    /*---GET BY CLIENT---*/
    @Operation(
        summary = "Muestra las reservas por cliente",
        description = "Debes enviar el id del cliente para que te muestre las reservas que este tiene."
    )
    @GetMapping(path = "/client/{id}")
    public ResponseEntity<List<ReservationResp>> getByGuideId(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.reservationService.getByClientId(id));
    }

    /*---GET BY TOUR---*/
    @Operation(
        summary = "Muestra las reservas por tour",
        description = "Debes enviar el id del tour para que te muestre las reservas que este tiene."
    )
    @GetMapping(path = "/tour/{id}")
    public ResponseEntity<List<ReservationResp>> getByTourId(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.reservationService.getByTourId(id));
    }

    /*---INSERT---*/
    @Operation(
        summary = "Crea una nueva reserva",
        description = "Debes enviar date, hour, status, paymentMethod, guideId, clientId, tourId."
    )
    @PostMapping
    public ResponseEntity<ReservationResp>insert(
        @Validated @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(this.reservationService.create(request));
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
        summary = "Actualiza la informacion de una reserva existente",
        description = "Debes enviar el id de la reserva que deseas actualizar."
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationResp> update(
            @Validated @RequestBody ReservationRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.reservationService.update(request, id));
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
        summary = "Elimina una reserva con el id",
        description = "Debes enviar el id de la reserva que deseas eliminar."
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
    

    
}
