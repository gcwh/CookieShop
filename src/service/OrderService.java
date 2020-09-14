package service;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.Order;
import model.OrderItem;

import java.sql.Connection;
import java.util.List;

public interface OrderService {
    default void insertOrder( Order order){return;}
    default void getLastInsertId(){ return; }
    default void insertOrderItem(OrderItem item){return;}
    default List<Order> selectAll(int uesrid){return null;}
    default List<OrderItem> selectAllItem(int orderid){return null;}
    default int getOrderCount(int status){return 0;}
    default List<Order> selectOrderList(int status,int pageNumber,int pageSize){return null;}
    default void updateStatus(int id,int status){return;}
    default void deleteOrder(int id){return;}
    default void deleteOrderItem(int id){return;}
}
