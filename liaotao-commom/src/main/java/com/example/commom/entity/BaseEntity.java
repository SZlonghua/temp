package com.example.commom.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableField(value = "create_on",fill = FieldFill.INSERT)
    private Date createOn;
    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private String createBy;
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "modified_on",fill = FieldFill.INSERT_UPDATE)
    private Date modifiedOn;
    @ApiModelProperty(value = "修改人")
    @TableField(value = "modified_by",fill = FieldFill.INSERT_UPDATE)
    private String modifiedBy;
}
