package servlet.goodsBuy;

import service.GoodsService;
import model.*;
import service.Impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "goods_search",urlPatterns = "/goods_search")
public class GoodsSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private GoodsService gService = new GoodsServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("p", null);
        request.setAttribute("keyword", URLEncoder.encode(null,"utf-8"));
        request.getRequestDispatcher("/goods_search.jsp").forward(request, response);
    }

}
