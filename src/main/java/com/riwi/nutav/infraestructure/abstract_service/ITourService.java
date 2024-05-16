package com.riwi.nutav.infraestructure.abstract_service;

import com.riwi.nutav.api.dto.request.TourRequest;
import com.riwi.nutav.api.dto.response.TourResp;

public interface ITourService extends CrudService<TourRequest,TourResp, Long> {
    public final String FIELD_BY_SORT = "price";
    
}
