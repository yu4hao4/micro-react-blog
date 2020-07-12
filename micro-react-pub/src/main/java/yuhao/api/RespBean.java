package yuhao.api;


import java.io.Serializable;

/**
 * 返回给前端信息的 bean 对象
 * @author 喻浩
 * @create 2020-03-01-11:59
 */
public class RespBean<T> implements Serializable {
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

    private RespBean() {
    }

    private RespBean(long status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    private RespBean(long status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> RespBean<T> build() {
        return new RespBean<T>();
    }

    public static <T> RespBean<T> ok(String msg) {
        return commonly(
                ResultCode.SUCCESS.getCode()
                , msg);
    }

    public static <T> RespBean<T> error(String msg) {
        return commonly(ResultCode.FAILED.getCode(), msg);
    }

    public static <T>  RespBean<T> commonly(long status,String msg,T data){
        return new RespBean<T>(status, msg, data);
    }
    public static <T>  RespBean<T> commonly(long status,T data){
        return commonly(status, "",data);
    }
    public static <T>  RespBean<T> commonly(long status,String msg){
        return commonly(status, msg,null);
    }

    public long getStatus() {
        return status;
    }

    public RespBean<T> setStatus(long status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public RespBean<T> setObj(T data) {
        this.data = data;
        return this;
    }

    public T getObj() {
        return data;
    }


}
