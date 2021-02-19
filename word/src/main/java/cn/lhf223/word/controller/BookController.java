package cn.lhf223.word.controller;

import cn.lhf223.word.model.*;
import cn.lhf223.word.service.BookService;
import cn.lhf223.word.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: word
 * @description:
 * @author: lhf
 * @create: 2021-02-14 20:59
 */
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    LoginService loginService;

    @GetMapping
    public RespBean selectPage(PageParameter pageParameter, Integer classifyId){
        Map<String,Object> res = new HashMap<>(4);
        Integer limit = (pageParameter.getCurrent() - 1) * pageParameter.getPageSize();
        List<Book> list = bookService.selectPage(limit,pageParameter.getPageSize(),classifyId);
        pageParameter.setTotalCount(bookService.count(classifyId));
        res.put("list",list);
        res.put("page",pageParameter);
        return RespBean.ok(res);
    }
    @PostMapping("/studySet/{id}")
    public RespBean studySet(@RequestBody StudySet studySet,@PathVariable("id") Integer id,HttpSession session){
        User user = (User) session.getAttribute("user");
        user.setBookId(id);
        user.setBookDate(new Date());
        user.setBookDay(0);
        loginService.updateUserById(user);
        bookService.studySet(studySet,user,id);
        session.setAttribute("user",user);
        return RespBean.ok();
    }

    @GetMapping("/info")
    public RespBean bookInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Book book = bookService.selectBookInfo(user.getBookId(),user.getId());
        return RespBean.ok(book);
    }
}