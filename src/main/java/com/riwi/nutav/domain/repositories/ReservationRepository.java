package com.riwi.nutav.domain.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.riwi.nutav.domain.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

    //Metodo que busca Reserva por cliente
    @Query(value = "select a from reservation a join fetch a.client c where c.id = :idClient")
    Optional<Reservation> findByClientId(Long idClient);

    //Metodo que busca reservacion por guia y fecha
    @Query("SELECT COUNT(a) FROM reservation a WHERE a.guide.id = :guideId AND a.date = :date")
    public Long countByGuideIdAndDate(@Param("guideId") Long guideId, @Param("date") LocalDate date);

    //Metodo que busca Reserva por tour
    @Query(value = "select a from reservation a join fetch a.tour c where c.id = :idTour")
    Optional<Reservation> findByTourId(Long idTour);
}
