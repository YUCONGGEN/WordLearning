package cn.lhf223.word.service;

import cn.lhf223.word.model.Book;
import cn.lhf223.word.model.StudySet;
import cn.lhf223.word.model.User;

import java.util.List;

public interface BookService {
    List<Book> selectPage(Integer limit, Integer pageSize, Integer classifyId);

    Integer count(Integer classifyId);

    void studySet(StudySet studySet, User user,Integer id);

    Book selectBookInfo(Integer bookId,Integer userId);
}
