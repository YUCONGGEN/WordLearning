package cn.lhf223.word.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: word
 * @description:
 * @author: lhf
 * @create: 2021-02-18 19:14
 */
@Data
public class WordVO {
    private Integer id;

    private String name;

    private List<String> phonetic;

    private List<String> explain;

    private List<String> example;

    private Integer bookId;
}