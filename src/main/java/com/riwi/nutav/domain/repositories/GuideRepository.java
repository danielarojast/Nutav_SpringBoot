package com.riwi.nutav.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.riwi.nutav.domain.entities.Guide;
import com.riwi.nutav.utils.enums.ChosenGender;
import com.riwi.nutav.utils.enums.ChosenLanguage;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long>{

    //Metodo para buscar por nombre
    List<Guide> findByNameContains(String name);

    //Metodo para buscar por genero
    List<Guide> findByGender(ChosenGender gender);

    //Metodo para buscar por idioma
    List<Guide> findByLanguage(ChosenLanguage language);
}
