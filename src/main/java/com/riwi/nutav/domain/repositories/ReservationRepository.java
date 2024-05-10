package com.riwi.nutav.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.nutav.domain.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    
}
