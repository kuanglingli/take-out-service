package com.itaem.crazy.shirodemo.project.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {


    //数据是否正常请求
    private String code;
    //返回信息
    private String msg;
    //具体返回的数据
    private T data;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success(){
        Result result = new Result();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static Result fail(){
        Result result = new Result();
        result.setCode("-9999");
        result.setMsg("失败");
        return result;
    }

    public static Result<Object> successAndData(Object o){
        Result<Object> result = new Result();
        result.setCode("0");
        result.setMsg("成功");
        result.setData(o);
        return result;
    }
}
