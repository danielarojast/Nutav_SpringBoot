package com.riwi.nutav.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.nutav.domain.entities.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    
} 
