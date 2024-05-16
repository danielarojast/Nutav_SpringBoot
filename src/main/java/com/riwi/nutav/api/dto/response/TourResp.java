package com.riwi.nutav.api.dto.response;

import java.math.BigDecimal;
import java.util.List;

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
    private ChosenLanguage language;
    private String description;
    private BigDecimal price;

    private GuideResp guide;
    private List<MediaBasicResp> media;
}
