package servlet.order;

import model.Order;
import model.OrderItem;
import model.User;
import service.Impl.OrderServiceImpl;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "order_list", urlPatterns = "/order_list")
public class OrderListServlet extends HttpServlet {
    OrderServiceImpl orderService=new OrderServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=(User) request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect("/index.jsp");
        }
        List<Order> orderList= orderService.selectAll(2);
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/order_list.jsp").forward(request, response);
    }
}
