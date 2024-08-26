package dao;

import entities.Order;

public class OrderDAO extends BaseDAO<Order> {
    public OrderDAO() {
        super(Order.class);
    }
}
