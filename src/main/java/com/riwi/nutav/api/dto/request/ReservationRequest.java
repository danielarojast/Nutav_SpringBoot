package com.riwi.nutav.api.dto.request;

import java.sql.Time;
import java.time.LocalDate;

import com.riwi.nutav.utils.enums.PaymentMethod;
import com.riwi.nutav.utils.enums.StatusReservation;

import jakarta.validation.constraints.Future;
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

    @Future(message = "La fecha debe de ser futura")
    @NotNull (message = "La fecha es requerida")
    private LocalDate date;
    @NotNull (message = "La hora es requerida")
    private Time hour;
    private StatusReservation status;
    @NotNull(message = "El metodo de pago es requerido")
    private PaymentMethod paymentMethod;
    
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
