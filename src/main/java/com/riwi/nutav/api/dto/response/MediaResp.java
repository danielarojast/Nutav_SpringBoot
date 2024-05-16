package com.riwi.nutav.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaResp {
    private Long id;
    private String url;

    private TourBasicResp tour;

}
