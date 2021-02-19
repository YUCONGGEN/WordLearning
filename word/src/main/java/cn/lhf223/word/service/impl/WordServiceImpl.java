package cn.lhf223.word.service.impl;

import cn.lhf223.word.mapper.BookMapper;
import cn.lhf223.word.mapper.StudySetMapper;
import cn.lhf223.word.mapper.WordMapper;
import cn.lhf223.word.model.Book;
import cn.lhf223.word.model.StudySet;
import cn.lhf223.word.model.User;
import cn.lhf223.word.model.Word;
import cn.lhf223.word.model.vo.WordVO;
import cn.lhf223.word.service.WordService;
import cn.lhf223.word.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: word
 * @description:
 * @author: lhf
 * @create: 2021-02-18 19:08
 */
@Service
public class WordServiceImpl implements WordService {

    @Autowired
    StudySetMapper studySetMapper;

    @Autowired
    WordMapper wordMapper;

    @Override
    public List<WordVO> wordStudy(User user) {
        StudySet studySet = studySetMapper.selectByPrimaryKey(user.getId());
        List<Word> newWords = wordMapper.selectUserWordByFlag(user.getId(), 0, studySet.getDayNewWord(),null);
        List<Word> reviewWords = wordMapper.selectUserWordByFlag(user.getId(), 1, studySet.getDayReviewWord(), DateUtils.timeToDate(new Date()));
        newWords.addAll(reviewWords);
        if (newWords==null || newWords.size()==0) {
            studySet.setDayNewWord(0);
            studySet.setDayReviewWord(0);
            studySetMapper.updateByPrimaryKeySelective(studySet);
        }
        return getWordVO(newWords);
    }

    private List<WordVO> getWordVO(List<Word> words){
        List<WordVO> list = words.stream().map(word -> {
            WordVO wordVO = new WordVO();
            wordVO.setId(word.getId());
            wordVO.setName(word.getName());
            wordVO.setPhonetic(Arrays.asList(word.getPhonetic().split(";")));
            wordVO.setExplain(Arrays.asList(word.getExplain().split(";")));
            wordVO.setExample(Arrays.asList(word.getExample().split(";")));
            wordVO.setBookId(word.getBookId());
            return wordVO;
        }).collect(Collectors.toList());
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBookWord(Integer userId, Integer bookId) {
        wordMapper.deleteUserWord(userId);
        List<Word> words = wordMapper.selectAllByBookId(bookId);
        wordMapper.insertUserWords(userId,words);
    }

    @Override
    public void updateUserWord(Integer userId, Integer wordId) {
        Integer flag = wordMapper.selectUserWordFlag(userId,wordId);
        StudySet studySet = studySetMapper.selectByPrimaryKey(userId);
        if (flag==0 && studySet.getDayNewWord()>0) {
            studySet.setDayNewWord(studySet.getDayNewWord()-1);
        } else if (flag==1 && studySet.getDayReviewWord()>0) {
            studySet.setDayReviewWord(studySet.getDayReviewWord()-1);
        }
        studySetMapper.updateByPrimaryKeySelective(studySet);
        wordMapper.updateUserWord(userId,wordId);
    }

    @Override
    public Integer countWord(Integer userId) {
        return wordMapper.countWord(userId);
    }
}