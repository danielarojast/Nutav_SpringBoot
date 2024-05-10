package com.riwi.nutav.api.dto.response;

import java.sql.Time;
import java.time.LocalDate;

import com.riwi.nutav.utils.enums.StatusReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationBasicClientResp {
    private Long id;
    private LocalDate date;
    private Time hour;
    private StatusReservation status;

    private GuideBasicResp guide;
    private TourBasicResp tour;
}
