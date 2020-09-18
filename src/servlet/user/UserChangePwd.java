package servlet.user;

import model.User;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user_changepwd",urlPatterns = "/user_changepwd")
public class UserChangePwd extends HttpServlet {

    private UserService uService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        String password= request.getParameter("password");
        String newPassword=request.getParameter("newPassword");

        User user= uService.selectById(id);
        if(user.getPassword().equals(password)){
            user.setPassword(newPassword);
            uService.updatePwd(user);
            request.getSession().setAttribute("user",uService.selectById(id));
            request.setAttribute("msg","修改密码成功！");
        }else {
            request.setAttribute("failMsg","原密码错误，修改密码失败！");
        }
        request.getRequestDispatcher("user_center.jsp").forward(request,response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
