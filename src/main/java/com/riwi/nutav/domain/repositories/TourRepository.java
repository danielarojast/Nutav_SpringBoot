package com.riwi.nutav.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.riwi.nutav.domain.entities.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    //Metodo que busca tour por guia
    @Query(value = "select a from tour a join fetch a.guide c where c.id = :idGuide")
    Optional<Tour> findByGuidetId(Long idGuide);
} 
