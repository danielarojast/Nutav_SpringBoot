package com.riwi.nutav.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.nutav.domain.entities.Guide;

public interface GuideRepository extends JpaRepository<Guide, Long>{
    
}
