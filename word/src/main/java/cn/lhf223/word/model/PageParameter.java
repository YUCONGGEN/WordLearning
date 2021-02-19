package cn.lhf223.word.model;

import lombok.Data;

/**
 * @program: word
 * @description: 分页参数
 * @author: lhf
 * @create: 2021-02-14 22:32
 */
@Data
public class PageParameter {
    private Integer current;
    private Integer pageSize;
    private Integer totalCount;
}