package com.riwi.nutav.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.nutav.api.dto.request.ClientRequest;
import com.riwi.nutav.api.dto.response.ClientResp;
import com.riwi.nutav.domain.entities.ClientEntity;
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
        ClientEntity client = this.requestToEntity(request);
        return this.entityToResp(this.clientRepository.save(client));
        
    }

    @Override
    public ClientResp get(Long id) {
       
        return this.entityToResp(this.find(id));
    }

    @Override
    public ClientResp update(ClientRequest request, Long id) {

        ClientEntity clientUpdate = this.requestToEntity(request);
        clientUpdate.setId(id);

        return this.entityToResp(this.clientRepository.save(clientUpdate));
    }

    @Override
    public void delete(Long id) {
       
        ClientEntity client = this.find(id);
        this.clientRepository.delete(client);
    }

    @Override
    public Page<ClientResp> getAll(int page, int size, SortType sortType) {
        
        if (page <0) page= 0; 
        PageRequest pagination= PageRequest.of(page, size);

        return this.clientRepository.findAll(pagination)
                .map(this::entityToResp);

    }

    private ClientResp entityToResp(ClientEntity entity){
        
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
    
    private ClientEntity requestToEntity(ClientRequest client){
        return ClientEntity.builder()
                .name(client.getName())
                .lastname(client.getLastname())
                .age(client.getAge())
                .gender(client.getGender())
                .language(client.getLanguage())
                .nationality(client.getNationality())
                .phone(client.getPhone())
                .email(client.getEmail())
                .picture(client.getPicture())
                .documentType(client.getDocumentType())
                .identificationNumber(client.getIdentificationNumber())
                .password(client.getPassword())
                .build();
    }

     
    private ClientEntity find(Long id){

        return this.clientRepository.findById(id)
            .orElseThrow();
        /*Falta lo del error */
    }
    
    
    
}
