package com.riwi.nutav.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import com.riwi.nutav.api.dto.request.TourRequest;
import com.riwi.nutav.api.dto.response.TourResp;
import com.riwi.nutav.domain.repositories.TourRepository;
import com.riwi.nutav.infraestructure.abstract_service.ITourService;
import com.riwi.nutav.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TourService implements ITourService {

    @Autowired
    private final TourRepository tourRepository;

    @Override
    public TourResp create(TourRequest request) {
        
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public TourResp get(Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public TourResp update(TourRequest request, Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<TourResp> getAll(int page, int size, SortType sort) {
        
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    
}
