package com.example.commom.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "分页信息")
public class PageUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //总记录数
    @ApiModelProperty("总记录数")
    private long totalCount;
    //每页记录数
    @ApiModelProperty("每页记录数")
    private int pageSize;
    //总页数
    @ApiModelProperty("总页数")
    private int totalPage;
    //当前页数
    @ApiModelProperty("当前页数")
    private int currPage;
    //列表数据
    @ApiModelProperty("列表数据")
    private List<T> data;

    /**
     * 分页
     * @param data        列表数据
     * @param totalCount  总记录数
     * @param pageSize    每页记录数
     * @param currPage    当前页数
     */
    public PageUtil(List<T> data, long totalCount, int pageSize, int currPage) {
        this.data = data;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public  static <T> PageUtil<T> of(List<T> data, long totalCount, int pageSize, int currPage){
        return new PageUtil<>(data, totalCount, pageSize, currPage);
    }

}
