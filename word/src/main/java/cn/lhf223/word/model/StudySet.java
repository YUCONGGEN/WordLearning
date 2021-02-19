package cn.lhf223.word.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class StudySet {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 每日新词
     */
    private Integer newWord;

    /**
     * 每日复习
     */
    private Integer reviewWord;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy年MM月dd日",timezone = "Asia/Shanghai")
    private Date endTime;

    /**
     * 日期
     */
    private Date day;

    /**
     * 今日应学
     */
    private Integer dayNewWord;

    /**
     * 今日复习
     */
    private Integer dayReviewWord;
}