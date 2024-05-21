package com.riwi.nutav.infraestructure.abstract_service;

import java.util.List;

import com.riwi.nutav.api.dto.request.TourRequest;
import com.riwi.nutav.api.dto.response.TourResp;
import com.riwi.nutav.utils.enums.CategoryTour;
import com.riwi.nutav.utils.enums.ChosenLanguage;

public interface ITourService extends CrudService<TourRequest,TourResp, Long> {
    public final String FIELD_BY_SORT = "price";

    List<TourResp>findByCategory(CategoryTour category);

    List<TourResp>findByLanguage(ChosenLanguage language);

    List<TourResp>findByPlace(String place);


}
