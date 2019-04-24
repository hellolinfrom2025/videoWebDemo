package org.mintleaf.vo;

import java.util.List;

public class PageLayui {

    private List data;
    private Long count;
    private Integer code;
    private String msg;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
