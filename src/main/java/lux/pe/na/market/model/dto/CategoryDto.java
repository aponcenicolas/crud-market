package lux.pe.na.market.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;

    @NotBlank(message = "Nombre requerido")
    private String name;

    private int status;
}
