package com.riwi.nutav.infraestructure.abstract_service;

import com.riwi.nutav.api.dto.request.ClientRequest;
import com.riwi.nutav.api.dto.response.ClientResp;

public interface IClientService extends CrudService<ClientRequest, ClientResp, Long>{
    
}
