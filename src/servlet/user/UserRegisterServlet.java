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

@WebServlet(name = "user_register",urlPatterns = "/user_rigister")
public class UserRegisterServlet extends HttpServlet {
    private UserService uService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        String address=request.getParameter("address");

        String msg="";
        //判断邮箱和用户名是否符合规范
        boolean isEm=email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+");
        boolean isUn=username.matches("^[a-zA-Z0-9_]+$");
        if(!isEm){
            msg="邮箱格式错误！";
        }else if(!isUn) {
            msg="用户名只能为字母和下划线！";
        }else {
            boolean uEt=uService.isUsernameExist(username);
            boolean eEt=uService.isEmailExist(email);

            if(uEt && eEt){
                msg="用户名和邮箱都已被注册！";
            }else if(uEt){
                msg="用户名已被注册！";
            }else if(eEt){
                msg="邮箱已被注册！";
            }
        }
        

        //注册失败
        if(!msg.isEmpty()){
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("user_register.jsp").forward(request,response);
            return;
        }

        //注册成功
        User user=new User(username,email,password,name,phone,address);
        uService.addUser(user);
        response.sendRedirect("/user_login.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
