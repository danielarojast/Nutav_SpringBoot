package com.riwi.nutav.api.dto.request;


import org.hibernate.validator.constraints.URL;

import com.riwi.nutav.utils.enums.ChosenGender;
import com.riwi.nutav.utils.enums.ChosenLanguage;
import com.riwi.nutav.utils.enums.IdentificationType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 100)
    private String name; 
    @NotBlank(message = "El apellido es requerido")
    @Size(min = 3, max = 100)
    private String lastname;
    @NotNull(message = "La edad es requerida")
    @Min(
        value = 18, 
        message = "Debes de ser mayor de 18 años"
    )
    @Max(
        value = 100, 
        message = "La edad que ingresaste es incorrecta"
    )
    private int age; 
    @NotNull(message = "El genero es requerido")
    private ChosenGender gender;
    private ChosenLanguage language;
    private String nationality; 
    @NotBlank
    @Size(min = 6)
    private String password;
    @NotBlank(message = "El telefono es requerido")
    @Size(min = 10, max = 20)
    private String phone;
    @NotBlank(message = "El email es requerido")
    @Size(
        min = 1, 
        max = 100, 
        message = "El email debe tener entre 1 y 100 caracteres"
    )
    @Email
    private String email; 
    @URL 
    private String picture;
    @NotNull(message = "El tipo de documento es requerido")
    private IdentificationType documentType;
    @NotBlank(message = "El numero de documento es requerido")
    @Size(min = 8, max = 10)
    private String identificationNumber;
    
}
