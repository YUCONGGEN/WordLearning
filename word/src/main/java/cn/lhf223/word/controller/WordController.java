package cn.lhf223.word.controller;

import cn.lhf223.word.model.RespBean;
import cn.lhf223.word.model.User;
import cn.lhf223.word.model.vo.WordVO;
import cn.lhf223.word.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: word
 * @description:
 * @author: lhf
 * @create: 2021-02-18 19:07
 */
@RestController
@RequestMapping("word")
public class WordController {
    @Autowired
    WordService wordService;

    @GetMapping("/study")
    public RespBean wordStudy(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<WordVO> words = wordService.wordStudy(user);
        return RespBean.ok(words);
    }

    @PutMapping("/study/{wordId}")
    public RespBean updateWord(@PathVariable("wordId") Integer wordId,HttpSession session){
        User user = (User) session.getAttribute("user");
        wordService.updateUserWord(user.getId(),wordId);
        return RespBean.ok();
    }

    @GetMapping("/countWord")
    public RespBean countWord(HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer count = wordService.countWord(user.getId());
        return RespBean.ok(count);
    }
}