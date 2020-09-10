package servlet.goods;

import model.Goods;
import model.Page;
import model.Type;
import service.GoodsService;
import service.Impl.GoodsServiceImpl;
import service.Impl.TypeServiceImpl;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "goods_List",urlPatterns = "/goods_list")
public class GoodsListServlet extends HttpServlet {
    private GoodsService gService=new GoodsServiceImpl();
    private TypeService tService=new TypeServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/goods_list.jsp").forward(request,response);
    }
}
