package com.riwi.nutav.api.dto.response;

import java.util.List;

import com.riwi.nutav.domain.entities.Reservation;
import com.riwi.nutav.utils.enums.ChosenGender;
import com.riwi.nutav.utils.enums.ChosenLanguage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuideResp {
    private Long id;
    private String name;
    private String lastname; 
    private int age;
    private ChosenGender gender;
    private ChosenLanguage Language;
    private String nationality; 
    private String phone;
    private String email; 
    private Integer experience;
    private String description;
    private String picture; 
    private List<Reservation> reservations; 
    
    //Cambiar reservacion por ReservartionBasicResp
}
