package com.jcokee.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author JIANGCHONG
 */
@ApiModel(description = "分页模型")
public class PageResult<T> {
    @ApiModelProperty(value = "总页数")
    private int pages;
    @ApiModelProperty(value = "当前页数")
    private int pageNum;
    @ApiModelProperty(value = "每页数")
    private int pageSize;
    @ApiModelProperty(value = "总记录数")
    private long total;
    @ApiModelProperty(value = "分页记录")
    private List<T> results;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
