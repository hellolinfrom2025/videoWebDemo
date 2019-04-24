package org.mintleaf.vo;

/**
 * Created by Administrator on 2018/1/16.
 */
public class ResultMsg {

    private boolean success = true;
    private String msg = "操作成功";

    private Integer code=0;

    private Object data ;

    public Integer getCode(){
        return this.code;
    }

    public ResultMsg setCode(Integer code){
        this.code=code;
        return this;
    }

    public ResultMsg() {
    }
    public ResultMsg(Object data) {
        this.data = data;
    }

    public ResultMsg(String msg, boolean status) {
        this.msg = msg;
        this.success = status;
        this.code = 1;
    }

    public static ResultMsg ok(){
        return new ResultMsg();
    }

    public static ResultMsg ok(Object data){
        return new ResultMsg(data);
    }

    public static ResultMsg fail(){
        return new ResultMsg("操作失败",false);
    }

    public static ResultMsg fail(String msg){
        return new ResultMsg(msg,false);
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setInfo(String msg) {
        this.msg = msg;
    }

    public void setInfo(String msg,boolean success){
        this.msg = msg;
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
