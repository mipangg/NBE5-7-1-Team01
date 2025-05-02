package io.binary.coffeenotfound_404.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    private Integer price;

    @Setter
    @Column(nullable = false)
    private Integer stock;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemsCategory Category;

    @Column(columnDefinition = "LONGTEXT")
    private String imageUrl;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public Items(String name, Integer price, Integer stock, ItemsCategory category, String imageUrl) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        Category = category;
        this.imageUrl = imageUrl;
    }
}
