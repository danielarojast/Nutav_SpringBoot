package com.riwi.nutav.domain.entities;

import java.math.BigDecimal;
import java.util.List;

import com.riwi.nutav.utils.enums.CategoryTour;
import com.riwi.nutav.utils.enums.ChosenLanguage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "tour")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryTour category;
    @Column(length = 100, nullable = false)
    private String place;
    @Column(nullable = false)
    private Integer duration;
    @Column(length = 50 ,nullable = false) 
    @Enumerated(EnumType.STRING)
    private ChosenLanguage language;
    @Lob
    private String description;
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guide_id", referencedColumnName = "id")
    private Guide guide;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Reservation> reservations;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Media> media;

}
