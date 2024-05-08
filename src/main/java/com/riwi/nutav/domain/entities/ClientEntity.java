package com.riwi.nutav.domain.entities;

import java.util.List;

import com.riwi.nutav.utils.enums.ChosenGender;
import com.riwi.nutav.utils.enums.ChosenLanguage;
import com.riwi.nutav.utils.enums.IdentificationType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "client")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name; 
    @Column(length = 100, nullable = false)
    private String lastname;
    @Column(length = 2, nullable = false)
    private int age; 
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private ChosenGender gender;
    @Column(length = 50,nullable = false) 
    @Enumerated(EnumType.STRING)
    private ChosenLanguage Language;
    @Column(length = 100, nullable = false)
    private String nationality; 
    @Column(length = 20, nullable = false)
    private String phone;
    @Column(length = 100, nullable = false)
    private String email; 
    @Column(nullable = false)
    private String password; 
    private String picture; 
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private IdentificationType documentType;
    @Column(nullable = false)
    private String identificationNumber;

    //En el cliente se deben de listar las reservas que tenga
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Reservation> reservations;

    
}
