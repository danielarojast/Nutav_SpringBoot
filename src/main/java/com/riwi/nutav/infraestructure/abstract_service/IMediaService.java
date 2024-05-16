package com.riwi.nutav.infraestructure.abstract_service;

import com.riwi.nutav.api.dto.request.MediaRequest;
import com.riwi.nutav.api.dto.response.MediaResp;

public interface IMediaService extends CrudService<MediaRequest, MediaResp, Long> {
    
}
