package cn.lhf223.word.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: vhr
 * @description: 统一返回实体类
 * @author: lhf
 * @create: 2021-01-11 18:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;

    public static RespBean ok(){
        return new RespBean(200, null, null);
    }
    public static RespBean ok(String msg){
        return new RespBean(200, msg, null);
    }
    public static RespBean ok(Object obj){
        return new RespBean(200, null, obj);
    }
    public static RespBean ok(String msg, Object obj){
        return new RespBean(200, msg, obj);
    }
    public static RespBean error(){
        return new RespBean(500, null, null);
    }
    public static RespBean error(String msg){
        return new RespBean(500, msg, null);
    }
    public static RespBean error(String msg, Object obj){
        return new RespBean(500, msg, obj);
    }
}