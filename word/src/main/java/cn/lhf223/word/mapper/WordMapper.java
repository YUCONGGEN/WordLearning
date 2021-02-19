package cn.lhf223.word.mapper;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

import cn.lhf223.word.model.Word;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WordMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Word record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Word record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Word selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Word record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Word record);

    void deleteUserWord(Integer userId);

    List<Word> selectAllByBookId(@Param("bookId")Integer bookId);


    void insertUserWords(@Param("userId") Integer userId,@Param("words") List<Word> words);

    List<Word> selectUserWordByFlag(@Param("userId") Integer userId, @Param("flag") Integer flag, @Param("limit") Integer limit, @Param("date") Date data);

    void updateUserWord(@Param("userId") Integer userId,@Param("wordId") Integer wordId);

    Integer selectUserWordFlag(@Param("userId") Integer userId,@Param("wordId") Integer wordId);

    Integer countWord(Integer userId);
}