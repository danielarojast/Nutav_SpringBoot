package com.riwi.nutav.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.nutav.domain.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    
}
