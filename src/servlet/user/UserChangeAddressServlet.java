package servlet.user;

import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "user_changeaddress",urlPatterns = "/user_changeaddress")
public class UserChangeAddressServlet extends HttpServlet {
    UserService us=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        String address=request.getParameter("address");

        User user=new User(null,null,null,name,phone,address);
        user.setId(id);

        us.updateUserAddress(user);

        request.setAttribute("msg","个人信息修改成功！");

        request.getSession().setAttribute("user",us.selectById(id));
        request.getRequestDispatcher("user_center.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
