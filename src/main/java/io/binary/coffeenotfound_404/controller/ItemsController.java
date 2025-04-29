package io.binary.coffeenotfound_404.controller;

import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.dto.ItemsRequest;
import io.binary.coffeenotfound_404.dto.ItemsUpdateRequest;
import io.binary.coffeenotfound_404.service.ItemsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping
    public ResponseEntity<List<Items>> read() {
        return ResponseEntity.ok(itemsService.get());
    }

    @PutMapping("/{itemsId}")
    public ResponseEntity<Items> update(@PathVariable Long itemsId, @RequestBody ItemsUpdateRequest request) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(itemsService.updateById(itemsId, request));
    }

    @DeleteMapping("/{itemsId}")
    public ResponseEntity<Void> delete(@PathVariable Long itemsId) {
        itemsService.deleteById(itemsId);
        return ResponseEntity.noContent().build();
    }

}
