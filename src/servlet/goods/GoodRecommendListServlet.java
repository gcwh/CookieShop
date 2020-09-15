package servlet.goods;

import model.Page;
import service.GoodsService;
import service.Impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "goodrecommendList",urlPatterns = "/goodsrecommend_list")
public class GoodRecommendListServlet extends HttpServlet {
    private GoodsService gService = new GoodsServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int type=Integer.parseInt(request.getParameter("type"));
        int pageNumber = 1;
        if(request.getParameter("pageNumber") != null) {
            try {
                pageNumber=Integer.parseInt(request.getParameter("pageNumber") ) ;
            }
            catch (Exception e)
            {

            }
        }
        if(pageNumber<=0){
            pageNumber=1;
        }
        Page page = gService.getGoodsRecommendPage(type, pageNumber);
        if(page.getTotalPage()==0)
        {
            page.setTotalPage(1);
            page.setPageNumber(1);
        }
        else {
            if(pageNumber>=page.getTotalPage()+1)
            {
                page = gService.getGoodsRecommendPage(type, page.getTotalPage());
            }
        }
        request.setAttribute("p", page);
        request.setAttribute("t", type);

        request.getRequestDispatcher("goodsrecommend_list.jsp").forward(request, response);
    }
}
