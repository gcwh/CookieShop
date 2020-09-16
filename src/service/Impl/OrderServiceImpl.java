package service.Impl;

import dao.OrderDao;
import model.Order;
import model.OrderItem;
import service.OrderService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDao();

    @Override
    public void insertOrder(Order order) {
        try {
            orderDao.insertOrder(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return;
    }

    @Override
    public void getLastInsertId() {
        try {
            orderDao.getLastInsertId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return;
    }

    @Override
    public void insertOrderItem( OrderItem item) {
        try {
            orderDao.insertOrderItem(item);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return;
    }

    @Override
    public List<Order> selectAll(int uesrid) {
        try {
            return orderDao.selectAll(uesrid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderItem> selectAllItem(int orderid) {
        try {
            return orderDao.selectAllItem(orderid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int getOrderCount(int status) {
        try {
            return orderDao.getOrderCount(status);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Order> selectOrderList(int status, int pageNumber, int pageSize) {
        try {
            return orderDao.selectOrderList(status,pageNumber,pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateStatus(int id, int status) {
        try {
            orderDao.updateStatus(id,status);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return;
    }

    @Override
    public void deleteOrder(int id) {
        try {
            orderDao.deleteOrder(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return;
    }

    @Override
    public void deleteOrderItem(int id) {
        try {
            orderDao.deleteOrderItem(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return;
    }
}
