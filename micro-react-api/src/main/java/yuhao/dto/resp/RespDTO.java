package yuhao.dto.resp;


import yuhao.api.ResultCode;

import java.io.Serializable;

/**
 * 返回给前端信息的 DTO 对象
 * @author 喻浩
 * @create 2020-03-01-11:59
 */
public class RespDTO<T> implements Serializable {
    /**
     * 状态码
     */
    private long status;
    /**
     * 返回的信息
     */
    private String msg;
    /**
     * 任意类型数据
     */
    private T data;

    private RespDTO() {
    }

    private RespDTO(long status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    private RespDTO(long status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> RespDTO<T> build() {
        return new RespDTO<T>();
    }

    public static <T> RespDTO<T> ok(String msg) {
        return commonly(
                ResultCode.SUCCESS.getCode()
                , msg);
    }

    public static <T> RespDTO<T> error(String msg) {
        return commonly(ResultCode.FAILED.getCode(), msg);
    }

    public static <T> RespDTO<T> commonly(long status, String msg, T data){
        return new RespDTO<T>(status, msg, data);
    }
    public static <T> RespDTO<T> commonly(long status, T data){
        return commonly(status, "",data);
    }
    public static <T> RespDTO<T> commonly(long status, String msg){
        return commonly(status, msg,null);
    }

    public long getStatus() {
        return status;
    }

    public RespDTO<T> setStatus(long status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespDTO<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public RespDTO<T> setObj(T data) {
        this.data = data;
        return this;
    }

    public T getObj() {
        return data;
    }


}
