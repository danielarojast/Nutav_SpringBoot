package com.riwi.nutav.api.dto.request;

import java.math.BigDecimal;
import java.util.List;

import com.riwi.nutav.utils.enums.CategoryTour;
import com.riwi.nutav.utils.enums.ChosenLanguage;

import jakarta.validation.constraints.DecimalMin;
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
public class TourRequest {
    @NotBlank(message = "El titulo del tour es requerido")
    @Size(min = 3,max = 100,message = "El titulo debe tener entre 3 y 100 caracteres")
    private String title;
    @NotNull(message = "La categoría es requerida")
    private CategoryTour category;
    @NotBlank(message = "El lugar del tour es requerido")
    @Size(min = 3,max = 100,message = "El titulo debe tener entre 3 y 100 caracteres")
    private String place;
    @NotNull(message = "La duración es requerido")
    @Min(value = 1)
    private Integer duration;
    @NotNull(message = "El idioma en que se realizará el tour es requerido")
    private ChosenLanguage language;
    private String description;
    @NotNull(message = "El precio es requerido")
    @DecimalMin(
        value = "0.01",
        message = "El valor del servicio debe ser mayor a 0"
    )
    private BigDecimal price;
    private Long guideId;
    private List<MediaRequest> media;
}
