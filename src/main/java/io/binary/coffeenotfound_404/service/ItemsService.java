package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.dao.ItemsRepository;
import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.domain.ItemsCategory;
import io.binary.coffeenotfound_404.dto.ItemsRequest;
import io.binary.coffeenotfound_404.dto.ItemsUpdateRequest;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository itemsRepository;

    public Items save(ItemsRequest request) {

        ItemsCategory category = ItemsCategory.from(request.getCategory());

        Items items = Items.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(category)
                .imageUrl(request.getImageUrl())
                .build();

        itemsRepository.save(items);

        return items;
    }

    public List<Items> get() {
        return itemsRepository.findAll();
    }

    public Items getById(Long itemsId) {
        return itemsRepository.findById(itemsId)
                .orElseThrow(() -> new NoSuchElementException("입력된 ID의 상품이 존재하지 않습니다."));
    }

    public Items updateById(Long itemsId, ItemsUpdateRequest request) {
        Items items = itemsRepository.findById(itemsId)
                .orElseThrow(() -> new NoSuchElementException("입력된 ID의 상품이 존재하지 않습니다."));

        items.setName(request.getName());
        items.setPrice(request.getPrice());
        items.setStock(request.getStock());

        ItemsCategory category = ItemsCategory.from(request.getCategory());
        items.setCategory(category);

        return items;
    }

    public void deleteById(Long itemsId) {
        Items items = itemsRepository.findById(itemsId)
                .orElseThrow(() -> new NoSuchElementException("입력된 ID의 상품이 존재하지 않습니다."));

        itemsRepository.delete(items);
    }
}
