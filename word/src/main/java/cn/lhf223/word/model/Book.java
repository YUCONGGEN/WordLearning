package cn.lhf223.word.model;

import lombok.Data;

@Data
public class Book {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 书名
     */
    private String name;

    /**
     * 书封面
     */
    private String image;

    /**
     * 分类ID
     */
    private Integer classifyId;

    /**
     * 单词数
     */
    private Integer count;

    private StudySet studySet;
}