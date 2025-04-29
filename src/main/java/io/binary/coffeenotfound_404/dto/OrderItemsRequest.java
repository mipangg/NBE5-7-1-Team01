package io.binary.coffeenotfound_404.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsRequest {

    private Long itemsId;
    private Integer quantity;
    
}
