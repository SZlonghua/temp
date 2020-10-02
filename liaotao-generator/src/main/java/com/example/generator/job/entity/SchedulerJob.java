package com.example.generator.job.entity;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.example.commom.entity.BaseEntity;
/**
 * <p>
 * 定时调度表
 * </p>
 *
 * @author liaotao
 * @since 2020-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SchedulerJob对象", description="定时调度表")
public class SchedulerJob extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键")
    private String jobId;
    @ApiModelProperty(value = "任务名")
    private String taskName;
    @ApiModelProperty(value = "任务分组")
    private String taskGroup;
    @ApiModelProperty(value = "执行类")
    private String executeClass;
    @ApiModelProperty(value = "cron表达式")
    private String cron;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "状态")
    private String state;


}
