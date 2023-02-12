package com.example.powerstationmanagesystem.common;

import lombok.Data;

@Data
//定义该类包装我们返回的数据，统一返回数据类型为Result类
public class Result {

    //成功情况下返回的code
    private static final String SUCCESS_CODE = "200";
    //失败情况下返回的code
    private static final String ERROR_CODE = "-1";

    //存储返回代码，200。。。表示是否成功
    private String code;
    //存储我们需要返回的数据
    private Object data;
    //存储我们需要携带的信息
    private String msg;

    //成功情况下，无数据返回
    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    //成功情况下返回,参数我们需要的数据
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        //设计result中的data为我们传入的data
        result.setData(data);
        return result;
    }

    //失败情况下返回,参数我们需要返回的错误信息，因为错误查不到数据
    public static Result error(String msg){
        Result result = new Result();
        result.setCode(ERROR_CODE);
        //设计result中的data为我们传入的data
        result.setMsg(msg);
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
