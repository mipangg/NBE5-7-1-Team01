package io.binary.coffeenotfound_404.controller;

import io.binary.coffeenotfound_404.domain.Orders;
import io.binary.coffeenotfound_404.dto.OrdersRequest;
import io.binary.coffeenotfound_404.dto.OrdersUpdateRequest;
import io.binary.coffeenotfound_404.service.OrdersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody OrdersRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ordersService.save(request).getId());
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Orders>> get(@PathVariable String email) {
        return ResponseEntity.ok(ordersService.getByEmail(email));
    }

    @PutMapping("/{email}/{ordersId}")
    public ResponseEntity<Orders> update(@PathVariable String email, @PathVariable Long ordersId
            , @RequestBody OrdersUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ordersService.updateByEmailAndOrdersId(email, ordersId, request));
    }

}
