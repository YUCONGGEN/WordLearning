package cn.lhf223.word.service.impl;

import cn.lhf223.word.mapper.BookMapper;
import cn.lhf223.word.mapper.StudySetMapper;
import cn.lhf223.word.mapper.UserMapper;
import cn.lhf223.word.model.Book;
import cn.lhf223.word.model.StudySet;
import cn.lhf223.word.model.User;
import cn.lhf223.word.service.BookService;
import cn.lhf223.word.service.WordService;
import cn.lhf223.word.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: word
 * @description:
 * @author: lhf
 * @create: 2021-02-14 21:00
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    StudySetMapper studySetMapper;

    @Autowired
    WordService wordService;


    @Override
    public List<Book> selectPage(Integer limit, Integer pageSize, Integer classifyId) {
        return bookMapper.selectPage(limit,pageSize,classifyId);
    }

    @Override
    public Integer count(Integer classifyId) {
        return bookMapper.count(classifyId);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void studySet(StudySet studySet, User user, Integer id) {
        Book book = bookMapper.selectByPrimaryKey(id);
        studySet.setUserId(user.getId());
        Calendar c = Calendar.getInstance();
        studySet.setStartTime(DateUtils.timeToDate(c.getTime()));
        if (book.getCount()!=null && book.getCount()!=0){
            c.add(Calendar.DAY_OF_MONTH, ((int) Math.ceil(book.getCount() / studySet.getNewWord())));
        }
        studySet.setEndTime(DateUtils.timeToDate(c.getTime()));
        studySet.setDay(studySet.getStartTime());
        studySet.setDayNewWord(studySet.getNewWord());
        studySet.setDayReviewWord(studySet.getReviewWord());
        wordService.updateBookWord(user.getId(),book.getId());
        studySetMapper.deleteByPrimaryKey(user.getId());
        studySetMapper.insert(studySet);
    }

    @Override
    public Book selectBookInfo(Integer bookId,Integer userId) {
        Book book = bookMapper.selectBookInfo(bookId, userId);
        StudySet studySet = book.getStudySet();
        if (DateUtils.dateCompare(new Date(),studySet.getDay())) {
            studySet.setDay(DateUtils.timeToDate(new Date()));
            studySet.setDayNewWord(studySet.getNewWord());
            studySet.setDayReviewWord(studySet.getReviewWord());
            studySetMapper.updateByPrimaryKeySelective(studySet);
        }
        return book;
    }
}