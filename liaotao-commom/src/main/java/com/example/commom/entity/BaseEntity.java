package com.example.commom.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BaseEntity对象", description="")
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "创建时间")
    private Date createOn;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "修改时间")
    private Date modifiedOn;
    @ApiModelProperty(value = "修改人")
    private String modifiedBy;
}
