package servlet.user;

import model.User;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "user_login",urlPatterns = "/user_login")
public class UserLoginServlet extends HttpServlet {
    private UserService uService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        User user=isExistAndGet(request.getParameter("ue"),request.getParameter("password"));

        //登陆信息错误
        if(user==null){
            request.setAttribute("failMsg","用户名或邮箱或密码错误！");
            request.getRequestDispatcher("user_login.jsp").forward(request,response);
            return;
        }

        //成功登陆
        HttpSession session= request.getSession();
        session.setAttribute("user",user);

        response.sendRedirect("/index");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private User isExistAndGet(String username,String password){
        User user=null;
        //邮箱正则表达式判断是否为邮箱
        boolean isEm=username.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+");
        if(isEm){
            user=uService.selectByEmailPassword(username,password);
        }else{
            user=uService.selectByUsernamePassword(username,password);
        }
        return user;
    }
}
