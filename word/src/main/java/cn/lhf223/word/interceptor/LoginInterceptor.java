package cn.lhf223.word.interceptor;

import cn.lhf223.word.model.RespBean;
import cn.lhf223.word.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: word
 * @description:
 * @author: lhf
 * @create: 2021-02-19 16:13
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user!=null) {return true;}
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.getWriter().write(new ObjectMapper().writeValueAsString(RespBean.error("用户未登录")));
        response.getWriter().flush();
        return false;
    }
}