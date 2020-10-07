package com.example.generator.job.controller;


import com.example.generator.job.service.SchedulerJobService;
import com.example.commom.model.R;
import com.example.commom.model.PageUtil;
import com.example.generator.job.entity.SchedulerJob;
import com.example.commom.model.Query;
import com.example.commom.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * <p>
 * 定时调度表 前端控制器
 * </p>
 *
 * @author liaotao
 * @since 2020-10-07
 */
@RestController
@RequestMapping("/job/schedulerJob")
@Api(tags = {"定时调度表"})
public class SchedulerJobController extends BaseController {
    @Autowired
    SchedulerJobService schedulerJobService;

    @GetMapping("list")
    @ApiOperation(value = "列表")
    public R<PageUtil<SchedulerJob>> list(Query query) {
        return R.ok(schedulerJobService.list(query));
    }

    @GetMapping("info/{id}")
    @ApiOperation(value = "详情")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "id",value = "主键",required = true,dataType = "String")
    })
    public R<SchedulerJob> info(@PathVariable(value = "id") String id) {
        return R.ok(schedulerJobService.info(id));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存")
    public R<Boolean> save(@RequestBody SchedulerJob schedulerJob){
        return R.ok(schedulerJobService.saveEntity(schedulerJob));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新")
    public R<Boolean> update(@RequestBody SchedulerJob schedulerJob){
        return R.ok(schedulerJobService.updateEntity(schedulerJob));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ids",value = "主键",required = true,dataType = "String",allowMultiple = true)
    })
    public R<Boolean> delete(@RequestBody List<String> ids){
        return R.ok(schedulerJobService.delete(ids));
    }
}
