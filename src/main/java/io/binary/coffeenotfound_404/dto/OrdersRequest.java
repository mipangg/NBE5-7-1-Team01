package io.binary.coffeenotfound_404.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRequest {

    private String email;
    private String address;
    private String zipCode;

    // itemId, quantity
    private List<OrderItemsRequest> orderItemsList;

}
