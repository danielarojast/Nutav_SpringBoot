package com.riwi.nutav.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.nutav.api.dto.request.ClientRequest;
import com.riwi.nutav.api.dto.response.ClientResp;
import com.riwi.nutav.domain.repositories.ClientRepository;
import com.riwi.nutav.infraestructure.abstract_service.IClientService;
import com.riwi.nutav.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService implements IClientService{
    
    @Autowired
    private final ClientRepository clientRepository;
    
    @Override
    public ClientResp create(ClientRequest request) {
       
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ClientResp get(Long id) {
       
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public ClientResp update(ClientRequest request, Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
       
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<ClientResp> getAll(int page, int size, SortType sort) {
        
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    
}
