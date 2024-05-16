package com.riwi.nutav.infraestructure.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event.ID;

import com.riwi.nutav.api.dto.request.ReservationRequest;
import com.riwi.nutav.api.dto.response.ClientResp;
import com.riwi.nutav.api.dto.response.GuideResp;
import com.riwi.nutav.api.dto.response.ReservationResp;
import com.riwi.nutav.api.dto.response.TourBasicResp;
import com.riwi.nutav.domain.entities.ClientEntity;
import com.riwi.nutav.domain.entities.Guide;
import com.riwi.nutav.domain.entities.Reservation;
import com.riwi.nutav.domain.entities.Tour;
import com.riwi.nutav.domain.repositories.ClientRepository;
import com.riwi.nutav.domain.repositories.GuideRepository;
import com.riwi.nutav.domain.repositories.ReservationRepository;
import com.riwi.nutav.domain.repositories.TourRepository;
import com.riwi.nutav.infraestructure.abstract_service.IReservationService;
import com.riwi.nutav.utils.enums.SortType;
import com.riwi.nutav.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {

    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired  
    private final GuideRepository guideRepository;
    @Autowired
    private final ClientRepository clientRepository; 
    @Autowired
    private final TourRepository tourRepository; 

    @Override
    public ReservationResp create(ReservationRequest request) {
        
        // Obtener cliente
        ClientEntity client = this.clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new BadRequestException("No hay clientes con el id suministrado"));

        // Obtener guia
        Guide guide = this.guideRepository.findById(request.getGuideId())
                .orElseThrow(() -> new BadRequestException("No hay guias con el id suministrado"));

        //optener tour
        Tour tour = this.tourRepository.findById(request.getTourId())
                .orElseThrow(() -> new BadRequestException("No servicios clientes con el id suministrado"));

        // El guia esté disponible a esa fecha y hora
        if (this.isGuideAvailable(request.getGuideId(), request.getDate()) != 0) {
            throw new BadRequestException("EL guia no esta displonible en esta fecha");
        }

        Reservation reservation = this.requestToEntity(request);

        reservation.setClient(client);
        reservation.setGuide(guide);
        reservation.setTour(tour);

        return this.entityToResponse(this.reservationRepository.save(reservation));
    }

    @Override
    public ReservationResp get(Long id) {
        
        return this.entityToResponse(this.find(id));
    }

    @Override
    public ReservationResp update(ReservationRequest request, Long id) {
        
        Reservation reservation = this.find(id);
        // Obtener cliente
        ClientEntity client = this.clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new BadRequestException("No hay clientes con el id suministrado"));

        // Obtener guia
        Guide guide = this.guideRepository.findById(request.getGuideId())
                .orElseThrow(() -> new BadRequestException("No hay guias con el id suministrado"));

        //optener tour
        Tour tour = this.tourRepository.findById(request.getTourId())
                .orElseThrow(() -> new BadRequestException("No servicios clientes con el id suministrado"));

        // El guia esté disponible a esa fecha y hora
        if (this.isGuideAvailable(request.getGuideId(), request.getDate()) != 0) {
            throw new BadRequestException("EL guia no esta disponible en esta fecha");
        }


        reservation = this.requestToEntity(request);

        reservation.setClient(client);
        reservation.setGuide(guide);
        reservation.setTour(tour);
        reservation.setId(id);

        return this.entityToResponse(this.reservationRepository.save(reservation));

    }

    @Override
    public void delete(Long id) {
        
        this.reservationRepository.delete(this.find(id));
    }

    @Override
    public Page<ReservationResp> getAll(int page, int size, SortType sortType) {
        if(page < 0 )
            page = 0;
        PageRequest pagination = null; 
        
        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
    
        return this.reservationRepository.findAll(pagination)
            .map(this::entityToResponse);
         
    }

    @Override
    public List<ReservationResp> getByClientId(Long id) {
        /*return this.reservationRepository.findByClientId(id).orElseThrow()
        .map(this::entityToResponse);*/
        return this.findByClientId(id)
                    .stream()
                    .map(this::entityToResponse)
                    .collect(Collectors.toList());
    }

    @Override
    public List<ReservationResp> getByTourId(Long id) {
        return this.findByTourId(id)
            .stream()
            .map(this::entityToResponse)
            .collect(Collectors.toList());
    }

    /*------------------------------------------------------------------ */
    
    //Para listarlos todos
    private ReservationResp entityToResponse(Reservation entity) {

        GuideResp guide = new GuideResp();
        BeanUtils.copyProperties(entity.getGuide(), guide);

        TourBasicResp tour = new TourBasicResp();
        BeanUtils.copyProperties(entity.getTour(), tour);

        ClientResp client = new ClientResp();
        BeanUtils.copyProperties(entity.getClient(), client);

        return ReservationResp.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .hour(entity.getHour())
                .status(entity.getStatus())
                .paymentMethod(entity.getPaymentMethod())
                .client(client)
                .guide(guide)
                .tour(tour)
                .build();

    }

    //para buscar el id en finById, delete, end update
    private Reservation find(Long id) {
        return this.reservationRepository.findById(id)
        .orElseThrow();
    }

    //Buscar por id del cliente
    private List<Reservation> findByClientId(Long id){
        return this.reservationRepository.findByClientId(id);
    }

    //Buscar por id del tour
    private List<Reservation> findByTourId(Long id){
        return this.reservationRepository.findByTourId(id);
    }

    // Ver que el guia no tenga reservas la misma fecha (en crear)
    public Long isGuideAvailable(Long guideId, LocalDate date) {
        return reservationRepository.countByGuideIdAndDate(guideId, date);
    }

    //para el create
    private Reservation requestToEntity(ReservationRequest request) {

        return Reservation.builder()
                .date(request.getDate())
                .hour(request.getHour())
                .status(request.getStatus())
                .paymentMethod(request.getPaymentMethod())
                .build();
    }

    
}
