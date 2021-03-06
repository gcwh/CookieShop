package service.Impl;


import dao.OrderDao;
import model.Order;
import model.OrderItem;
import model.Page;
import service.OrderService;
import java.sql.SQLException;
import java.util.List;


public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDao();

    @Override
    public void insertOrder(Order order) {
        try {
            orderDao.insertOrder(order);
            int id=orderDao.getLastInsertId();
            order.setId(id);
            for(OrderItem item:order.getItemList()){
                orderDao.insertOrderItem(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Order> selectAll(int uesrid) {
        List<Order>list=null;
        try {
            list=orderDao.selectAll(uesrid);
            for(Order o:list){
                List<OrderItem> orderItems=orderDao.selectAllItem(o.getId());
                o.setItemList(orderItems);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }



    @Override
    public void updateStatus(int id, int status) {
        try {
            orderDao.updateStatus(id,status);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        try {
            orderDao.deleteOrderItem(id);
            orderDao.deleteOrder(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Page getOrderPage(int status,int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 10;
        int totalCount = 0;
        try {
            totalCount = orderDao.getOrderCount(status);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list=null;
        try {
            list = orderDao.selectOrderList(status, pageNumber, pageSize);
            for(Order o :(List<Order>)list) {
                List<OrderItem> l = orderDao.selectAllItem(o.getId());
                o.setItemList(l);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }
}
