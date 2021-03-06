package com.example.scheduler.job.controller;


import com.example.scheduler.job.service.SchedulerJobService;
import com.example.commom.model.R;
import com.example.commom.model.PageUtil;
import com.example.scheduler.job.entity.SchedulerJob;
import com.example.commom.model.Query;
import com.example.commom.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import com.example.commom.valid.AddGroup;
import com.example.commom.valid.UpdateGroup;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 定时调度表 前端控制器
 * </p>
 *
 * @author liaotao
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/job/schedulerJob")
@Api(tags = {"定时调度表"})
@Validated
public class SchedulerJobController extends BaseController {
    @Autowired
    SchedulerJobService schedulerJobService;

    @GetMapping("list")
    @ApiOperation(value = "列表")
    public R<PageUtil<SchedulerJob>> list(@Validated Query query) {
        return R.ok(schedulerJobService.list(query));
    }

    @GetMapping("info/{id}")
    @ApiOperation(value = "详情")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "id",value = "主键",required = true,dataType = "String")
    })
    public R<SchedulerJob> info(@NotEmpty(message = "主键不能为空") @PathVariable(value = "id") String id) {
        return R.ok(schedulerJobService.info(id));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存")
    public R<Boolean> save(@Validated({AddGroup.class}) @RequestBody SchedulerJob schedulerJob){
        return R.ok(schedulerJobService.saveEntity(schedulerJob));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新")
    public R<Boolean> update(@Validated({UpdateGroup.class}) @RequestBody SchedulerJob schedulerJob){
        return R.ok(schedulerJobService.updateEntity(schedulerJob));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ids",value = "主键",required = true,dataType = "String",allowMultiple = true)
    })
    public R<Boolean> delete(@NotEmpty(message = "主键不能为空") @RequestBody List<String> ids){
        return R.ok(schedulerJobService.delete(ids));
    }


    /**
     * 立即执行任务
     */
    @PostMapping("/run")
    @ApiOperation(value = "立即执行任务")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "jobIds",value = "主键",required = true,dataType = "String",allowMultiple = true)
    })
    public R run(@NotEmpty(message = "主键不能为空") @RequestBody List<String> jobIds){
        schedulerJobService.run(jobIds);
        return R.ok();
    }

    /**
     * 暂停定时任务
     */
    @PostMapping("/pause")
    @ApiOperation(value = "暂停定时任务")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "jobIds",value = "主键",required = true,dataType = "String",allowMultiple = true)
    })
    public R pause(@NotEmpty(message = "主键不能为空") @RequestBody List<String> jobIds){
        schedulerJobService.pause(jobIds);
        return R.ok();
    }

    /**
     * 恢复定时任务
     */
    @PostMapping("/resume")
    @ApiOperation(value = "恢复定时任务")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "jobIds",value = "主键",required = true,dataType = "String",allowMultiple = true)
    })
    public R resume(@NotEmpty(message = "主键不能为空") @RequestBody List<String> jobIds){
        schedulerJobService.resume(jobIds);
        return R.ok();
    }
}
