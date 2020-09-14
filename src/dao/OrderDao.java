package dao;

import model.*;
import org.apache.commons.dbutils.*;
import utils.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import org.apache.commons.dbutils.handlers.*;

public class OrderDao {
    private QueryRunner r;
    public OrderDao(){
        r=new QueryRunner(DBUtil.getDataSource());
    }
    public void insertOrder(Order order) throws SQLException {
        String sql = "insert into `order`(total,amount,status,paytype,name,phone,address,datetime,user_id) values(?,?,?,?,?,?,?,?,?)";
        r.update(sql,
                order.getTotal(),order.getAmount(),order.getStatus(),
                order.getPaytype(),order.getName(),order.getPhone(),
                order.getAddress(),order.getDatetime(),order.getUser().getId() );
    }
    public int getLastInsertId() throws SQLException {
        String sql = "select last_insert_id()";
        BigInteger bi = r.query(sql,new ScalarHandler<BigInteger>());
        return Integer.parseInt(bi.toString());
    }
    public void insertOrderItem( OrderItem item) throws SQLException {
        String sql ="insert into orderitem(price,amount,goods_id,order_id) values(?,?,?,?)";
        r.update(sql,item.getPrice(),item.getAmount(),item.getGoods().getId(),item.getOrder().getId());
    }
    public List<Order> selectAll(int userid) throws SQLException {
        String sql = "select * from `order` where user_id=? order by datetime desc";
        return r.query(sql, new BeanListHandler<Order>(Order.class),userid);
    }
    public List<OrderItem> selectAllItem(int orderid) throws SQLException{
        String sql = "select i.id,i.price,i.amount,g.name from orderitem i,goods g where order_id=? and i.goods_id=g.id";
        return r.query(sql, new BeanListHandler<OrderItem>(OrderItem.class),orderid);
    }
    public int getOrderCount(int status) throws SQLException {
        String sql = "";
        if(status==0) {
            sql = "select count(*) from `order`";
            return r.query(sql, new ScalarHandler<Long>()).intValue();
        }else {
            sql = "select count(*) from `order` where status=?";
            return r.query(sql, new ScalarHandler<Long>(),status).intValue();
        }
    }
    public List<Order> selectOrderList(int status, int pageNumber, int pageSize) throws SQLException {
        if(status==0) {
            String sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id order by o.datetime desc limit ?,?";
            return r.query(sql, new BeanListHandler<Order>(Order.class), (pageNumber-1)*pageSize,pageSize );
        }else {
            String sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id and o.status=? order by o.datetime desc limit ?,?";
            return r.query(sql, new BeanListHandler<Order>(Order.class),status, (pageNumber-1)*pageSize,pageSize );
        }
    }
    public void updateStatus(int id,int status) throws SQLException {
        String sql ="update `order` set status=? where id = ?";
        r.update(sql,status,id);
    }
    public void deleteOrder(int id) throws SQLException {
        String sql ="delete from `order` where id = ?";
        r.update(sql,id);
    }
    public void deleteOrderItem(int id) throws SQLException {
        String sql ="delete from orderitem where order_id=?";
        r.update(sql,id);
    }
}
