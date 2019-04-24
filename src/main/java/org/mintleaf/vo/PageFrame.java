package org.mintleaf.vo;

import java.util.List;

/**
 * @Author: MengchuZhang
 * @Date: 2018/8/22 11:50
 * @Version 1.0
 */
public class PageFrame {

    private List list;
    private Long pageNum;
    private Long pageSize;
    private Long pages;
    private Long total;


    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


}
