package io.binary.coffeenotfound_404.controller;

import io.binary.coffeenotfound_404.domain.Orders;
import io.binary.coffeenotfound_404.service.OrdersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final OrdersService ordersService;

    @GetMapping
    public ResponseEntity<List<Orders>> findAll() {
        return ResponseEntity.ok(ordersService.findAll());
    }

}
