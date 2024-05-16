package com.riwi.nutav.infraestructure.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.nutav.api.dto.request.MediaRequest;
import com.riwi.nutav.api.dto.response.GuideResp;
import com.riwi.nutav.api.dto.response.MediaResp;
import com.riwi.nutav.api.dto.response.TourBasicResp;
import com.riwi.nutav.domain.entities.Media;
import com.riwi.nutav.domain.entities.Tour;
import com.riwi.nutav.domain.repositories.MediaRepository;
import com.riwi.nutav.domain.repositories.TourRepository;
import com.riwi.nutav.infraestructure.abstract_service.IMediaService;
import com.riwi.nutav.utils.enums.SortType;
import com.riwi.nutav.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MediaService implements IMediaService {

    @Autowired
    private final MediaRepository mediaRepository;
    @Autowired
    private final TourRepository tourRepository;

    @Override
    public MediaResp create(MediaRequest request) {
        Tour tour = tourRepository.findById(request.getTourId()).orElseThrow(()-> new NoSuchElementException("No hay registros en el id suministrado"));

        Media media = requestToEntityMedia(request);
        media.setTour(tour);
        return entityToResponseMedia(mediaRepository.save(media)); 
    }

    @Override
    public MediaResp get(Long id) {

        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public MediaResp update(MediaRequest request, Long id) {

        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {

        this.mediaRepository.delete(this.find(id));
    }

    @Override
    public Page<MediaResp> getAll(int page, int size, SortType sort) {

        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    private Media requestToEntityMedia(MediaRequest request) {
        Tour tour = tourRepository.findById(request.getTourId()).orElse(null);

        return Media.builder()
            .url(request.getUrl())
            .tour(tour)
            .build();
    }

    private MediaResp entityToResponseMedia(Media entity) {
        GuideResp guia = GuideResp.builder()    
            .id(entity.getTour().getGuide().getId())
            .name(entity.getTour().getGuide().getName())
            .lastname(entity.getTour().getGuide().getLastname())
            .age(entity.getTour().getGuide().getAge())
            .gender(entity.getTour().getGuide().getGender())
            .language(entity.getTour().getGuide().getLanguage())
            .nationality(entity.getTour().getGuide().getNationality())
            .email(entity.getTour().getGuide().getEmail())
            .experience(entity.getTour().getGuide().getExperience())
            .description(entity.getTour().getGuide().getDescription())
            .picture(entity.getTour().getGuide().getPicture())
            .build();

        TourBasicResp tourResp = TourBasicResp.builder()
        .id(entity.getTour().getId())
        .title(entity.getTour().getTitle())
        .category(entity.getTour().getCategory())
        .place(entity.getTour().getPlace())
        .duration(entity.getTour().getDuration())
        .language(entity.getTour().getLanguage())
        .description(entity.getTour().getDescription())
        .price(entity.getTour().getPrice())
        .guide(guia)
        .build();

        return MediaResp.builder()
            .id(entity.getId())
            .url(entity.getUrl())
            .tour(tourResp)
            .build();
    }

    private Media find(Long id) {
        return this.mediaRepository.findById(id)
               .orElseThrow(()-> new BadRequestException("No hay media con el id suministrado"));
    }
    
}

