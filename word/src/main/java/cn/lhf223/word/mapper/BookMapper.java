package cn.lhf223.word.mapper;

import cn.lhf223.word.model.Book;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface BookMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Book record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Book record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Book selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Book record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Book record);

    List<Book> selectPage(@Param("limit") Integer limit, @Param("pageSize") Integer pageSize, @Param("classifyId") Integer classifyId);

    Integer count(@Param("classifyId") Integer classifyId);

    Book selectBookInfo(@Param("id") Integer bookId,@Param("userId") Integer userId);
}