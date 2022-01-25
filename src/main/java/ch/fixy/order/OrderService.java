package ch.fixy.order;

public interface OrderService {
    public Order createOrder(Long memberId, String itemName, int itemPrice);

}
