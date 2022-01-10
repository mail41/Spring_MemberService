package ch.fixy.service;

import ch.fixy.order.Order;

public interface OrderService {
    public Order createOrder(Long memberId, String itemName, int itemPrice);

}
