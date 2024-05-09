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
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "guide")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Guide {

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
    private Integer experience;
    @Lob
    private String description;
    @Column(nullable = false)
    private String password; 
    private String picture; 
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private IdentificationType documentType;
    @Column(nullable = false)
    private String identificationNumber;
    private String guideCertificate;

    //Se deben listar las reservas que tenga y los tours que tiene el guia creado
    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Tour> tours;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Reservation> reservations;

}
