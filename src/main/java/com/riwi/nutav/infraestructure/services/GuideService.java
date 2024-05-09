package com.riwi.nutav.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.nutav.api.dto.request.GuideRequest;
import com.riwi.nutav.api.dto.response.GuideResp;
import com.riwi.nutav.domain.repositories.GuideRepository;
import com.riwi.nutav.infraestructure.abstract_service.IGuideService;
import com.riwi.nutav.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuideService implements IGuideService {

    @Autowired
    private final GuideRepository guideRepository;

    @Override
    public GuideResp create(GuideRequest request) {
        
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public GuideResp get(Long id) {
    
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public GuideResp update(GuideRequest request, Long id) {
    
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
    
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<GuideResp> getAll(int page, int size, SortType sort) {
  
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    
}
