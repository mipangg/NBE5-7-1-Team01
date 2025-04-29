package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.dao.OrdersRepository;
import io.binary.coffeenotfound_404.domain.Orders;
import jakarta.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryService {

    private final OrdersRepository ordersRepository;
    private final LocalTime DELIVERY_START_TIME = LocalTime.of(14, 0, 0);
    private final LocalTime DELIVERY_END_TIME = LocalTime.of(14, 1, 0);

    // 1 현재가 오후 2시 인지 판별
    // 2 오후 2시인 경우, 주문 리스트들을 배송 완료 처리
    // 매 분마다 실행되도록 설정 (실제로는 1분 단위로 점검)
    @Scheduled(cron = "0 * * * * *") // 초 분 시 일 월 요일 (매분 0초에 실행)
    public void deliver() {
        LocalTime now = LocalTime.now();

        if (now.isAfter(DELIVERY_START_TIME) && now.isBefore(DELIVERY_END_TIME)) {
            log.info(goDelivery() + "개 배송 완료");
        }
    }

    public Integer goDelivery() {
        Integer countOfDeliveredOrders = 0;

        List<Orders> ordersList = ordersRepository.findAll();
        for (Orders order : ordersList) {
            if (!order.isDelivered()) {
                order.setDelivered(true);
            }
            countOfDeliveredOrders++;
        }

        return countOfDeliveredOrders;
    }

}
