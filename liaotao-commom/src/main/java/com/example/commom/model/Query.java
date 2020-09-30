package com.example.commom.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(description = "查询参数")
public class Query implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页码,默认第一页
    @ApiModelProperty(value = "当前页码,默认第一页", required = true,example = "1")
    private int page=1;
    //每页条数，默认每页10条
    @ApiModelProperty(value = "每页条数，默认每页10条", required = true,example= "10")
    private int size=10;
    //排序方向
    @ApiModelProperty("排序方向")
    private String direct;
    //排序字段
    @ApiModelProperty("排序字段")
    private String order;
    //搜索内容
    @ApiModelProperty("搜索内容")
    private String search;
    //开始时间
    @ApiModelProperty(value="开始时间",example = "2018-11-11 12:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    //结束时间
    @ApiModelProperty(value="结束时间",example = "2018-11-11 12:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
