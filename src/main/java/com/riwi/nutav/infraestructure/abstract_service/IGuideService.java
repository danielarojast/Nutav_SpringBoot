package com.riwi.nutav.infraestructure.abstract_service;

import com.riwi.nutav.api.dto.request.GuideRequest;
import com.riwi.nutav.api.dto.response.GuideResp;

public interface IGuideService extends CrudService<GuideRequest, GuideResp, Long> {
    public final String FIELD_BY_SORT = "experience";
}
