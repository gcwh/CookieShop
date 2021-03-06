package servlet.order;

import service.Impl.OrderServiceImpl;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderAcceptServlet",urlPatterns = "/orderAccept")
public class OrderAcceptServlet extends HttpServlet {
    OrderServiceImpl orderService=new OrderServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        orderService.updateStatus(id,4);
        response.getWriter().print("success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
