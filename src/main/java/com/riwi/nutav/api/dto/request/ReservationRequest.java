package com.riwi.nutav.api.dto.request;

import java.sql.Time;
import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    @FutureOrPresent(message = "La fecha y hora deben de ser futuras")
    @NotNull (message = "La fecha y hora son requeridas")
    private LocalDate date;
    @NotNull (message = "La hora es requerida")
    private Time hour; 

    //Preguntar la fecha y hora si mejor que este juntoas o separadas 
    // private StatusReservation status;---- preguntar como se hace para que se cambie automatic 
    //Preguntar como hacer con el id del guia por que se supone que al traer el id del tour trae el guia 
    

    @NotNull(message = "El id del guia es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero ")
    private Long guideId;

    @NotNull(message = "El id del cliente es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero ")
    private Long clientId;
    
    @NotNull(message = "El id del tour es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero ")
    private Long tourId;
    
}
