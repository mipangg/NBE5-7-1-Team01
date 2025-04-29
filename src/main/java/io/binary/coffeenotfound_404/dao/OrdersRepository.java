package io.binary.coffeenotfound_404.dao;

import io.binary.coffeenotfound_404.domain.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    public List<Orders> findListByEmail(String email);
}
