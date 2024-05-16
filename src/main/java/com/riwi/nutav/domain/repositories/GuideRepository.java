package com.riwi.nutav.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.riwi.nutav.domain.entities.Guide;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long>{

    //Metodo para buscar por nombre

    //@Query("select g from guide g WHERE g.name like: name")
    //Optional<Guide> findByName(String name);

    //Metodo para buscar por genero

    //Metodo para buscar por idioma
}
