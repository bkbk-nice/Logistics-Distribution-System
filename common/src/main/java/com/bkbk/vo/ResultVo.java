package com.bkbk.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class ResultVo implements Serializable{
    private Integer status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static final ResultVo DEFAULT_SUCCESS = new ResultVo(0,"成功");

    public static final ResultVo DEFAULT_FAIL = new ResultVo(-1,"失败");

    public static ResultVo success(){

        return DEFAULT_SUCCESS;
    }

    public static ResultVo fail(){

        return DEFAULT_FAIL;
    }
    public static ResultVo success(Object data){
        ResultVo resultVo = new ResultVo(0,"成功",data);
        return resultVo;
    }

    public static ResultVo fail(String message){
        ResultVo resultVo = new ResultVo(-1,message);
        return resultVo;
    }


    public ResultVo(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultVo(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResultVo() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
