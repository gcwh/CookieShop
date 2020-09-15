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

@WebServlet(name = "goods_List", urlPatterns = "/goods_list")
public class GoodsListServlet extends HttpServlet {
    private GoodsService gService = new GoodsServiceImpl();
    private TypeService tService = new TypeServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        int pageNumber = 0;
        if (request.getParameter("typeid") != null) {
            id = Integer.parseInt(request.getParameter("typeid"));
        }

        if (request.getParameter("pageNumber") != null) {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }

        Type t = null;

        if (id != 0) {
            t = tService.selectTypeNameByID(id);
        }
        request.setAttribute("t", t);

        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        Page p = gService.selectPageByTypeId(id, pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalCount(1);
            p.setPageNumber(1);
        } else {
            if (pageNumber >= p.getTotalPage() + 1) {
                p = gService.selectPageByTypeId(id, p.getTotalPage());
            }
        }
        request.setAttribute("p", p);
        request.setAttribute("id", String.valueOf(id));
        request.getRequestDispatcher("/goods_list.jsp").forward(request, response);
    }
}
