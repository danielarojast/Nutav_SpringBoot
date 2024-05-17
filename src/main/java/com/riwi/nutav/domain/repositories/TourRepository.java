package com.riwi.nutav.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.nutav.domain.entities.Tour;
import com.riwi.nutav.utils.enums.CategoryTour;
import com.riwi.nutav.utils.enums.ChosenLanguage;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    //Metodo para filtrar por categoria
    List<Tour>findByCategory(CategoryTour category);

    //Metodo para filtrar por lenguaje
    List<Tour>findByLanguage(ChosenLanguage language);

    //Metodo para filtrar por lugar
    List<Tour>findByPlace(String place);

} 
