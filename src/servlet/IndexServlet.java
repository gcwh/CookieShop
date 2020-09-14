package servlet;

import service.GoodsService;
import service.Impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "IndexServlet",urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    private GoodsService gService=new GoodsServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Object> scrollGoods=gService.getScrollGood();
        request.setAttribute("scroll",scrollGoods);

        List<Map<String,Object>>newGoods=gService.getGoodsList(3);
        request.setAttribute("newList",newGoods);

        List<Map<String,Object>>hotGoods=gService.getGoodsList(2);
        request.setAttribute("hotList",hotGoods);

        request.getRequestDispatcher("index.jsp").forward(request,response);

    }
}
