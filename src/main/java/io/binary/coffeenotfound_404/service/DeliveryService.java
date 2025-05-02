package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.dao.OrdersRepository;
import io.binary.coffeenotfound_404.domain.Orders;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryService {

    private final OrdersRepository ordersRepository;

    public Orders updateByEmailandOrderId(String email, Long orderId) {
        List<Orders> ordersList = ordersRepository.findListByEmail(email);

        for (Orders order : ordersList) {
            if (order.getId().equals(orderId)) {
                order.setDelivered(true);
                return order;
            }
        }
        throw new NoSuchElementException("입력된 email와 ID의 주문이 존재하지 않습니다.");
    }
}
