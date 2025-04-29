package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.dao.ItemsRepository;
import io.binary.coffeenotfound_404.dao.OrdersRepository;
import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.domain.OrderItems;
import io.binary.coffeenotfound_404.domain.Orders;
import io.binary.coffeenotfound_404.dto.OrderItemsRequest;
import io.binary.coffeenotfound_404.dto.OrdersRequest;
import io.binary.coffeenotfound_404.dto.OrdersUpdateRequest;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final ItemsRepository itemsRepository;

    public Orders save(OrdersRequest request) {
        List<OrderItems> orderItemsList = getOrderItemsList(request);

        Orders orders = Orders.builder()
                .email(request.getEmail())
                .address(request.getAddress())
                .zipCode(request.getZipCode())
                .orderItemsList(orderItemsList)
                .build();

        setOrdersOfOrderItemsList(orders);

        ordersRepository.save(orders);

        return orders;
    }

    public List<Orders> getByEmail(String email) {

        List<Orders> ordersList = ordersRepository.findListByEmail(email);
        if (ordersList.isEmpty()) {
            throw new NoSuchElementException("입력된 email의 주문이 존재하지 않습니다.");
        }

        return ordersList;
    }

    public Orders updateByEmailAndOrdersId(String email, Long ordersId, OrdersUpdateRequest request) {
        List<Orders> ordersList = getByEmail(email);

        Orders orders = getOrderById(ordersId, ordersList);

        orders.setAddress(request.getAddress());
        orders.setZipCode(request.getZipCode());

        return orders;
    }

    private static Orders getOrderById(Long ordersId, List<Orders> ordersList) {
        for (Orders orders : ordersList) {
            if (orders.getId().equals(ordersId)) {
                return orders;
            }
        }

        throw new NoSuchElementException("입력된 ID의 주문이 존재하지 않습니다.");
    }

    private static void setOrdersOfOrderItemsList(Orders orders) {
        for (OrderItems orderItems : orders.getOrderItemsList()) {
            orderItems.setOrders(orders);
        }
    }

    private List<OrderItems> getOrderItemsList(OrdersRequest request) {
        List<OrderItems> orderItemsList = new ArrayList<>();
        List<OrderItemsRequest> orderItemsRequestList = request.getOrderItemsList();

        for (OrderItemsRequest orderItemsRequest : orderItemsRequestList) {
            Items items = itemsRepository.findById(orderItemsRequest.getItemsId())
                    .orElseThrow(() -> new NoSuchElementException("입력된 ID의 상품이 존재하지 않습니다."));

            OrderItems orderItems = OrderItems.builder()
                    .items(items)
                    .quantity(orderItemsRequest.getQuantity())
                    .build();

            orderItemsList.add(orderItems);
            items.setStock(items.getStock() - orderItemsRequest.getQuantity());
        }
        return orderItemsList;
    }
}
