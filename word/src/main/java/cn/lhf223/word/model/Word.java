package cn.lhf223.word.model;

import lombok.Data;

@Data
public class Word {
    private Integer id;

    private String name;

    private String phonetic;

    private String explain;

    private String example;

    private Integer bookId;
}