package service;

import model.Order;
import model.Page;

import java.util.List;

public interface OrderService {
    default void insertOrder( Order order){return;}
    default List<Order> selectAll(int uesrid){return null;}
    default Page getOrderPage(int status,int pageNumber){return null;}
    default void updateStatus(int id,int status){return;}
    default void delete(int id){return;}
}
