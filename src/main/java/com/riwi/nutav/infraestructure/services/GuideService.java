package com.riwi.nutav.infraestructure.services;

import com.riwi.nutav.utils.enums.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.nutav.api.dto.request.GuideRequest;
import com.riwi.nutav.api.dto.response.GuideResp;
import com.riwi.nutav.domain.entities.Guide;
import com.riwi.nutav.domain.repositories.GuideRepository;
import com.riwi.nutav.infraestructure.abstract_service.IGuideService;
import com.riwi.nutav.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuideService implements IGuideService {

    @Autowired
    private final GuideRepository guideRepository;

    @Override
    public GuideResp create(GuideRequest request) {
        Guide entity = this.requestToEntity(request);
        return this.entityToResponse(this.guideRepository.save(entity));
    }

    @Override
    public GuideResp get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public GuideResp update(GuideRequest request, Long id) {
        Guide guide = this.find(id);
        guide = this.requestToEntity(request);
        guide.setId(id);

        return this.entityToResponse(this.guideRepository.save(guide));
    }

    @Override
    public void delete(Long id) {
        this.guideRepository.delete(this.find(id));
    }

    @Override
    public Page<GuideResp> getAll(int page, int size, SortType sortType) {
         if (page < 0)
            page = 0;

        PageRequest pagination = null;
        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.guideRepository.findAll(pagination)
                .map(this::entityToResponse);      
    }

    private Guide find(Long id) {
        return this.guideRepository.findById(id)
               .orElseThrow(()-> new BadRequestException("No hay guias con el id suministrado"));
    }

    private GuideResp entityToResponse(Guide entity) {
        return GuideResp.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .age(entity.getAge())
                .gender(entity.getGender())
                .language(entity.getLanguage())
                .nationality(entity.getNationality())
                .email(entity.getEmail())
                .experience(entity.getExperience())
                .description(entity.getDescription())
                .picture(entity.getPicture())
                .build();
    }

    private Guide requestToEntity(GuideRequest request) {
        return Guide.builder()
                .name(request.getName())
                .lastname(request.getLastname())
                .age(request.getAge())
                .gender(request.getGender())
                .language(request.getLanguage())
                .nationality(request.getNationality())
                .phone(request.getPhone())
                .email(request.getEmail())
                .experience(request.getExperience())
                .description(request.getDescription())
                .password(request.getPassword())
                .picture(request.getPicture())
                .documentType(request.getDocumentType())
                .identificationNumber(request.getIdentificationNumber())
                .guideCertificate(request.getGuideCertificate())
                .build();
    }

    
}
