package com.riwi.nutav.api.dto.response;

import java.sql.Time;
import java.time.LocalDate;

import com.riwi.nutav.utils.enums.PaymentMethod;
import com.riwi.nutav.utils.enums.StatusReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResp {
    private Long id;
    private LocalDate date;
    private Time hour;
    private StatusReservation status;
    private PaymentMethod paymentMethod;

    private ClientResp client;
    private GuideResp guide;
    private TourBasicResp tour;
}
