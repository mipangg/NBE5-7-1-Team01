package io.binary.coffeenotfound_404.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Setter
    @Column(nullable = false)
    private String address;

    @Setter
    @Column(nullable = false)
    private String zipCode;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Setter
    @Column(nullable = false)
    private boolean delivered = false;

    // Orders가 삭제되면 OrderItems도 삭제
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItemsList;

    public Integer getTotalPrice() {
        int totalPrice = 0;

        for (OrderItems orderItems : orderItemsList) {
            totalPrice += orderItems.getItemTotalPrice();
        }

        return totalPrice;
    }

    @Builder
    public Orders(String email, String address, String zipCode, List<OrderItems> orderItemsList) {
        this.email = email;
        this.address = address;
        this.zipCode = zipCode;
        this.orderItemsList = orderItemsList;
    }
}
