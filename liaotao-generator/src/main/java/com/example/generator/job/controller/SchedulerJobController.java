package com.example.generator.job.controller;


import com.example.commom.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.generator.job.service.SchedulerJobService;
import com.example.commom.model.R;
import com.example.commom.model.PageUtil;
import com.example.generator.job.entity.SchedulerJob;
import com.example.commom.model.Query;

/**
 * <p>
 * 定时调度表 前端控制器
 * </p>
 *
 * @author liaotao
 * @since 2020-10-01
 */
@RestController
@RequestMapping("/job/schedulerJob")
public class SchedulerJobController extends BaseController {
    @Autowired
    SchedulerJobService schedulerJobService;

    @RequestMapping("list")
    public R<PageUtil<SchedulerJob>> list(Query query) {
        return R.ok(schedulerJobService.list(query));
    }
}
