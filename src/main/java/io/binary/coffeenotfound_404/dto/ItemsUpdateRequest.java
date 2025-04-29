package io.binary.coffeenotfound_404.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsUpdateRequest {

    private String name;

    private Integer price;
    private Integer stock;

    private String category;

}
