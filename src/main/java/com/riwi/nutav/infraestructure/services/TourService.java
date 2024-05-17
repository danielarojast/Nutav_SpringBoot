package com.riwi.nutav.infraestructure.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.nutav.api.dto.request.MediaRequest;
import com.riwi.nutav.api.dto.request.TourRequest;
import com.riwi.nutav.api.dto.response.GuideResp;
import com.riwi.nutav.api.dto.response.MediaBasicResp;
import com.riwi.nutav.api.dto.response.TourResp;
import com.riwi.nutav.domain.entities.Guide;
import com.riwi.nutav.domain.entities.Media;
import com.riwi.nutav.domain.entities.Tour;
import com.riwi.nutav.domain.repositories.GuideRepository;
import com.riwi.nutav.domain.repositories.TourRepository;
import com.riwi.nutav.infraestructure.abstract_service.ITourService;
import com.riwi.nutav.utils.enums.CategoryTour;
import com.riwi.nutav.utils.enums.ChosenLanguage;
import com.riwi.nutav.utils.enums.SortType;
import com.riwi.nutav.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TourService implements ITourService {

    @Autowired 
    private final TourRepository tourRepository;
    @Autowired
    private final GuideRepository guideRepository;

    @Override
    public TourResp create(TourRequest request){
        Guide guide = guideRepository.findById(request.getGuideId()).orElseThrow(()-> new NoSuchElementException("No hay registros en el id suministrado"));

        Tour tour = requestTourToEntity(request);
        tour.setGuide(guide);
        return entityTourToResponse(tourRepository.save(tour));  
    }

    @Override
    public TourResp get(Long id) {
        return this.entityTourToResponse(this.find(id));
    }

    @Override
    public TourResp update(TourRequest request, Long id) {
        Guide guide = guideRepository.findById(request.getGuideId()).orElseThrow(()-> new NoSuchElementException("No hay registros en el id suministrado"));
        Tour tour = this.find(id);

       tour = this.requestTourToEntity(request);
       tour.setId(id);
       tour.setGuide(guide);
       

       return this.entityTourToResponse(this.tourRepository.save(tour));
    }

    @Override
    public void delete(Long id) {
        this.tourRepository.delete(this.find(id));
    }

    @Override
    public Page<TourResp> getAll(int page, int size, SortType sort) {
        if (page <0) page = 0;
        PageRequest pagination = null;

        switch (sort) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());  
        }
        return this.tourRepository.findAll(pagination)
                .map(this::entityTourToResponse);
    }

    private MediaBasicResp entityToResponseMedia(Media entity){
        return MediaBasicResp.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .build();
    }

    private Media requestMediaToEntity(MediaRequest mediaRequest) {
        return Media.builder()
        .url(mediaRequest.getUrl())
        .build();
    }

    private Tour requestTourToEntity(TourRequest request){
        List<Media> medias = request.getMedia().stream().map(this::requestMediaToEntity).collect(Collectors.toList());

        return Tour.builder()
        .title(request.getTitle())
        .category(request.getCategory()) 
        .place(request.getPlace())
        .duration(request.getDuration())
        .language(request.getLanguage())
        .description(request.getDescription())
        .price(request.getPrice())
        .media(medias)
        .build();
    }

    private TourResp entityTourToResponse(Tour tour){
        List<MediaBasicResp> medias = tour.getMedia()
                .stream()
                .map(this::entityToResponseMedia)
                .collect(Collectors.toList());

        GuideResp guideResp = GuideResp.builder()
        .id(tour.getGuide().getId())
        .name(tour.getGuide().getName())
        .lastname(tour.getGuide().getLastname())
        .age(tour.getGuide().getAge())
        .gender(tour.getGuide().getGender())
        .language(tour.getGuide().getLanguage())
        .nationality(tour.getGuide().getNationality())
        .email(tour.getGuide().getEmail())
        .experience(tour.getGuide().getExperience())
        .description(tour.getGuide().getDescription())
        .picture(tour.getGuide().getPicture())
        .build();

        System.out.println(tour.getGuide());  
        BeanUtils.copyProperties(guideResp, tour.getGuide());

        return TourResp.builder()
        .id(tour.getId())
        .title(tour.getTitle())
        .category(tour.getCategory()) 
        .place(tour.getPlace())
        .duration(tour.getDuration())
        .language(tour.getLanguage())
        .description(tour.getDescription())
        .price(tour.getPrice())
        .media(medias)
        .guide(guideResp)
        .build();
    }

    private Tour find(Long id){
        return this.tourRepository.findById(id)
            .orElseThrow(()-> new BadRequestException("No hay registros con el id suministrado"));
    }

    @Override
    public List<TourResp> findByCategory(CategoryTour category) {
        return this.tourRepository.findByCategory(category)
        .stream()
        .map(this::entityTourToResponse)
        .collect(Collectors.toList());
    }

    @Override
    public List<TourResp> findByLanguage(ChosenLanguage language) {
        return this.tourRepository.findByLanguage(language)
        .stream()
        .map(this::entityTourToResponse)
        .collect(Collectors.toList());
    }

    @Override
    public List<TourResp> findByPlace(String place) {
        return this.tourRepository.findByPlace(place)
        .stream()
        .map(this::entityTourToResponse)
        .collect(Collectors.toList());
    }
}
