package com.snykta.tools.web.result;

import com.snykta.tools.utils.CyObjUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回消息体
 * @param <T>
 */
@Data
@NoArgsConstructor
public class Ret<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据体
     */
    private T data;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息内容
     */
    private String message;



    public static <T> Ret<T> success() {
        return restResult(null, ResultCode.SUCCESS, "操作成功");
    }

    public static <T> Ret<T> success(T data) {
        return restResult(data,  ResultCode.SUCCESS, "操作成功");
    }

//    public static <T> Ret<T> success(String msg) {
//        return restResult(null, ResultCode.SUCCESS, msg);
//    }

    public static <T> Ret<T> success(String msg, T data) {
        return restResult(data, ResultCode.SUCCESS, msg);
    }

    public static <T> Ret<T> fail() {
        return restResult(null, ResultCode.ERROR, "操作失败");
    }

    public static <T> Ret<T> fail(String msg) {
        return restResult(null, ResultCode.ERROR, msg);
    }

    public static <T> Ret<T> fail(T data) {
        return restResult(data, ResultCode.ERROR, "操作失败");
    }

    public static <T> Ret<T> fail(String msg, T data) {
        return restResult(data, ResultCode.ERROR, msg);
    }

    public static <T> Ret<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }




    private static <T> Ret<T> restResult(T data, int code, String message) {
        Ret<T> ret = new Ret<>();
        ret.setCode(code);
        ret.setData(data);
        ret.setMessage(message);
        return ret;
    }


    public static <T> Boolean isError(Ret<T> ret) {
        return !isSuccess(ret);
    }


    public static <T> Boolean isSuccess(Ret<T> ret) {
        return CyObjUtil.equal(ResultCode.SUCCESS, ret.getCode());
    }



}
