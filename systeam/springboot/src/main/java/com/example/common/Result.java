package com.example.common;

//封装统一的返回数据结构
public class Result {

    private static final String SUCCESS="0";
    private static final String ERROR="-1";
    private String code;//返回的状态码，调用是成功还是失败
    private String msg;//前台获取的报错信息
    private Object data;//接受任何类型的数据

    //返回成功但不把数据给前台，只把成功信息给前台：如新增成功，删除成功
    public static Result success(){
        Result result=new Result();
        result.setCode(SUCCESS);
        return result;
    }
    //要把数据给前台：如查询成功需要把查询结果在前端展示
    public static Result success(Object data){
        Result result=new Result();
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }
    //失败要把失败信息传给前端
    public static Result error(String msg){
        Result result=new Result();
        result.setCode(ERROR);
        result.setMsg(msg);
        return result;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
