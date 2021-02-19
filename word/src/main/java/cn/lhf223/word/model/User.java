package cn.lhf223.word.model;

import java.util.Date;
import lombok.Data;

@Data
public class User {
    /**
     * ID
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 住宅电话
     */
    private String telephone;

    /**
     * 联系地址
     */
    private String address;

    private Boolean enabled;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String userface;

    private String remark;

    /**
     * 正在学习单词书ID
     */
    private Integer bookId;

    /**
     * 开始学习时间
     */
    private Date bookDate;

    /**
     * 学习天数
     */
    private Integer bookDay;
}