package cn.lhf223.word.controller;

import cn.lhf223.word.model.RespBean;
import cn.lhf223.word.model.User;
import cn.lhf223.word.service.LoginService;
import cn.lhf223.word.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @program: word
 * @description: 用户相关接口
 * @author: lhf
 * @create: 2021-02-13 22:14
 */
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/register")
    public RespBean register(@RequestBody User loginForm){
        Integer i = loginService.register(loginForm);
        if (i==1) {
            return RespBean.ok("注册成功，请登录");
        }
        return RespBean.error("注册失败，请检测网络！");
    }

    @PostMapping("/login")
    public RespBean login(@RequestBody User loginForm, HttpSession session){
        User user = loginService.Login(loginForm.getUsername(),loginForm.getPassword());
        if (user==null) {
            return RespBean.error("用户名或密码错误！");
        }
        if (DateUtils.dateCompare(new Date(),user.getBookDate())) {
            user.setBookDay(user.getBookDay()+1);
            user.setBookDate(new Date());
            loginService.updateUserById(user);
        }
        session.setAttribute("user",user);
        return RespBean.ok("登录成功",user);
    }
    @GetMapping("/logout")
    public RespBean logout(HttpSession session){
        session.invalidate();
        return RespBean.ok("注销成功");
    }

    @GetMapping("/flag")
    public RespBean flag(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getBookId()==null || user.getBookId()==0) {
            return RespBean.error("没有添加单词书");
        }
        return RespBean.ok();
    }
}