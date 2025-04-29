package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.dao.ItemImagesRepository;
import io.binary.coffeenotfound_404.dao.ItemsRepository;
import io.binary.coffeenotfound_404.domain.ItemImages;
import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.domain.ItemsCategory;
import io.binary.coffeenotfound_404.dto.ItemsRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository itemsRepository;
    private final ItemImagesRepository itemImagesRepository;

    public Items save(ItemsRequest request) {

        ItemImages img = itemImagesRepository.save(new ItemImages(request.getImageUrl()));
        ItemsCategory category = ItemsCategory.from(request.getCategory());

        Items items = Items.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(category)
                .images(img)
                .build();

        itemsRepository.save(items);

        return items;
    }
}
