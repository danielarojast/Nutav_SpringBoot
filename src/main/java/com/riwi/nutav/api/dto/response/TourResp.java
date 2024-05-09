package com.riwi.nutav.api.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.riwi.nutav.domain.entities.Guide;
import com.riwi.nutav.domain.entities.Reservation;
import com.riwi.nutav.utils.enums.CategoryTour;
import com.riwi.nutav.utils.enums.ChosenLanguage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourResp {
    private Long id;
    private String title;
    private CategoryTour category;
    private String place;
    private Integer duration;
    private ChosenLanguage Language;
    private String description;
    private BigDecimal price;

    private Guide guide;
    private List<Reservation> reservations;
    private List<MediaBasicResp> media;
}