package lux.pe.na.market.model.dto;

import lombok.*;

@Getter
@Setter
public class ProductListDto {

    private Long id;
    private String name;
    private int stock;
    private double price;
    private String category;
}
