package cn.lhf223.word.mapper;

import cn.lhf223.word.model.StudySet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudySetMapper {
    /**
     * delete by primary key
     *
     * @param userId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(StudySet record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(StudySet record);

    /**
     * select by primary key
     *
     * @param userId primary key
     * @return object by primary key
     */
    StudySet selectByPrimaryKey(Integer userId);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(StudySet record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(StudySet record);
}