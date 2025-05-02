package io.binary.coffeenotfound_404.controller;

import io.binary.coffeenotfound_404.domain.Orders;
import io.binary.coffeenotfound_404.service.DeliveryService;
import io.binary.coffeenotfound_404.service.OrdersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final OrdersService ordersService;
    private final DeliveryService deliveryService;

    @GetMapping
    public String showAdminPage() {
        return "admin";
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> findAll() {
        return ResponseEntity.ok(ordersService.findAll());
    }

    @PutMapping("/{email}/{ordersId}")
    public ResponseEntity<Orders> updateDeliveryStatus(@PathVariable String email, @PathVariable Long ordersId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryService.updateByEmailandOrderId(email, ordersId));
    }

    @ResponseBody
    @DeleteMapping("/{ordersId}")
    public ResponseEntity<Orders> deleteOrder(@PathVariable Long ordersId) {
        ordersService.deleteById(ordersId);
        return ResponseEntity.noContent().build();
    }
}
