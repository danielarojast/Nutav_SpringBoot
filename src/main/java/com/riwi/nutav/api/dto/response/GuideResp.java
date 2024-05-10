package com.riwi.nutav.api.dto.response;

import java.util.List;

import com.riwi.nutav.utils.enums.ChosenGender;
import com.riwi.nutav.utils.enums.ChosenLanguage;
import com.riwi.nutav.utils.enums.PaymentMethod;

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
    private ChosenLanguage language;
    private String nationality; 
    private String email; 
    private Integer experience;
    private String description;
    private String picture;
    
}
