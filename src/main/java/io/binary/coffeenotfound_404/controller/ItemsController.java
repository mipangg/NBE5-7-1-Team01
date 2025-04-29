package io.binary.coffeenotfound_404.controller;

import io.binary.coffeenotfound_404.dto.ItemsRequest;
import io.binary.coffeenotfound_404.service.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemsController {

    private final ItemsService itemsService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ItemsRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itemsService.save(request).getId());
    }

}
