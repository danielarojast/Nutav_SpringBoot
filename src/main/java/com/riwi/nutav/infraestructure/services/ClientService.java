package com.riwi.nutav.infraestructure.services;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.nutav.api.dto.request.ClientRequest;
import com.riwi.nutav.api.dto.response.ClientResp;
import com.riwi.nutav.api.dto.response.GuideResp;
import com.riwi.nutav.api.dto.response.ReservationBasicClientResp;
import com.riwi.nutav.api.dto.response.TourBasicResp;
import com.riwi.nutav.api.dto.response.TourResp;
import com.riwi.nutav.domain.entities.ClientEntity;
import com.riwi.nutav.domain.entities.Reservation;
import com.riwi.nutav.domain.repositories.ClientRepository;
import com.riwi.nutav.infraestructure.abstract_service.IClientService;
import com.riwi.nutav.utils.enums.IdentificationType;
import com.riwi.nutav.utils.enums.SortType;
import com.riwi.nutav.utils.enums.StatusReservation;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    public Page<ClientResp> getAll(int page, int size, SortType sortType) {
        
        if (page <0) page= 0; 
        PageRequest pagination= null;

        return this.clientRepository.findAll(pagination)
                .map(this::entityToResp);

    }

    private ClientResp entityToResp(ClientEntity entity){
        List<ReservationBasicClientResp> reservation = entity.getReservations()
            .stream()
            .map(this::entityToResponseReservation)
            .collect(Collectors.toList());

            return ClientResp.builder()
              .id(entity.getId())
              .name(entity.getName())
              .lastname(entity.getLastname())
              .age(entity.getAge())
              .gender(entity.getGender())
              .Language(entity.getLanguage())
              .nationality(entity.getNationality())
              .phone(entity.getPhone())
              .email(entity.getEmail())
              .picture(entity.getPicture())
              .build();
    }

    private ReservationBasicClientResp entityToResponseReservation(Reservation entity){

        TourResp tour= new TourResp();
        BeanUtils.copyProperties(entity.getTour(), tour);

        GuideResp guide= new GuideResp();
        BeanUtils.copyProperties(entity.getGuide(), guide);

        return ReservationBasicClientResp.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .hour(entity.getHour())
                .status(entity.getStatus())
                .guide(guide)
                .tour(tour)
                .builder();
    }
    /* 
    private ClientEntity requestToEntity(ClientRequest client){
        return ClientEntity.builder()
                .name(client.getName())
                .lastname(client.getLastname())
                .age(client.getAge())
                .gender(client.getGender())
                .Language(client.getLanguage())
                .nationality(client.getNationality())
                .phone(client.getPhone())
                .email(client.getEmail())
                .picture(client.getPicture())
                .documentType(client.getDocumentType())
                .identificationNumber(client.getIdentificationNumber())
                .build();
    }
*/
    /* 
    private ClientEntity find(Long id){
        
        return this.clientRepository.findAllById(id)
            .orElseThrow(() -> new BadRequestException("No hay clientes con el id suministrado"));
    }
    */
}