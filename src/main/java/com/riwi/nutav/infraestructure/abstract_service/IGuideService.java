package com.riwi.nutav.infraestructure.abstract_service;

import java.util.List;

import com.riwi.nutav.api.dto.request.GuideRequest;
import com.riwi.nutav.api.dto.response.GuideResp;
import com.riwi.nutav.utils.enums.ChosenGender;
import com.riwi.nutav.utils.enums.ChosenLanguage;

public interface IGuideService extends CrudService<GuideRequest, GuideResp, Long> {
    public final String FIELD_BY_SORT = "experience";

    List<GuideResp>findByNameContains(String name);

    List<GuideResp>findByGender(ChosenGender gender);

    List<GuideResp>findByLanguage(ChosenLanguage language);
}
