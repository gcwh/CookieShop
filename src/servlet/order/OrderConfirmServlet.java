package servlet.order;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfig;
import model.*;
import org.apache.commons.beanutils.BeanUtils;
import service.Impl.OrderServiceImpl;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Random;

@WebServlet(name = "order_confirm",urlPatterns = "/order_confirm")
public class OrderConfirmServlet extends HttpServlet {
    private OrderService oService = new OrderServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order o = (Order) request.getSession().getAttribute("order");
        try {
            BeanUtils.copyProperties(o, request.getParameterMap());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        o.setDatetime(new Date());
        o.setStatus(2);
        o.setUser((User) request.getSession().getAttribute("user"));
        request.getSession().removeAttribute("order");
        oService.insertOrder(o);
        if(o.getPaytype()==2){
            pay(request,response);
        }
        request.setAttribute("msg", "订单支付成功！");
        request.getSession().removeAttribute("order");
        request.getRequestDispatcher("/order_success.jsp").forward(request, response);

    }
    protected void pay(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        AlipayClient alipayClient=new DefaultAlipayClient(AlipayConfig.URL,AlipayConfig.APPID,AlipayConfig.RSA_PRIVATE_KEY,AlipayConfig.FORMAT,AlipayConfig.CHARSET,AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //填充业务参数
        Order order=(Order)request.getSession().getAttribute("order");
        try {
            BeanUtils.copyProperties(order,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //必填
        //商户订单号，需保证在商户端不重复
        String out_trade_no =String.valueOf(Math.random()*1000+1);
        //销售产品码，与支付宝签约的产品码名称。目前仅支持FAST_INSTANT_TRADE_PAY
        String product_code = "FAST_INSTANT_TRADE_PAY";
        //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]。
        String total_amount = String.valueOf(order.getTotal());
        //订单标题
        String subject = "支付宝付款";

        //选填
        //商品描述，可空
        String body = "商品描述";

        alipayRequest.setBizContent("{" +
                "\"out_trade_no\":\"" + out_trade_no + "\"," +
                "\"product_code\":\"" + product_code + "\"," +
                "\"total_amount\":\"" + total_amount + "\"," +
                "\"subject\":\"" + subject + "\"," +
                "\"body\":\"" + body + "\"}" );
        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();//调用SDK生成表单

        } catch (AlipayApiException e) {
            e.printStackTrace();
            response.getWriter().write("捕获异常出错");
            response.getWriter().flush();
            response.getWriter().close();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
