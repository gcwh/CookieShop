package servlet.order;

import model.Order;
import model.User;
import service.Impl.OrderServiceImpl;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "order_list", urlPatterns = "/order_list")
public class OrderListServlet extends HttpServlet {
    private OrderService oService = new OrderServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("orderList", null);
        request.getRequestDispatcher("/order_list.jsp").forward(request, response);
    }
}
