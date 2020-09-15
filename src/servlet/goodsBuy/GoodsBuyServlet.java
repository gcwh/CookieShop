package servlet.goodsBuy;

import model.Goods;
import model.Order;
import service.GoodsService;
import service.Impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "goods_buy",urlPatterns = "/goods_buy")
public class GoodsBuyServlet extends HttpServlet {
    private GoodsService gService = new GoodsServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = null;
        if(request.getSession().getAttribute("order") != null) {
            order = (Order) request.getSession().getAttribute("order");
        }else {
            order = new Order();
            request.getSession().setAttribute("order", order);
        }
        int goodsId = Integer.parseInt(request.getParameter("goodsid"));
        Goods goods = gService.getGoodsById(goodsId);
        if(goods.getStock()>0) {
            order.addGoods(goods);
            response.getWriter().print("ok");
        }else {
            response.getWriter().print("fail");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
