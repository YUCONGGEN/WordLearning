package cn.lhf223.word.service;

import cn.lhf223.word.model.User;
import cn.lhf223.word.model.vo.WordVO;

import java.util.List;

public interface WordService {
    List<WordVO> wordStudy(User user);

    void updateBookWord(Integer userId, Integer booId);

    void updateUserWord(Integer id, Integer wordId);

    Integer countWord(Integer id);
}
