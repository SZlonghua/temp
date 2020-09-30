package com.example.generator.job.controller;


import com.example.commom.model.PageUtil;
import com.example.commom.model.Query;
import com.example.commom.model.R;
import com.example.generator.job.entity.SchedulerJob;
import com.example.generator.job.service.SchedulerJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 定时调度表 前端控制器
 * </p>
 *
 * @author liaotao
 * @since 2020-09-30
 */
@RestController
@RequestMapping("/job/schedulerJob")
public class SchedulerJobController {
    @Autowired
    SchedulerJobService schedulerJobService;



    @RequestMapping("/list")
    public R<PageUtil<SchedulerJob>> list(Query query){
        System.out.println("ssssss");
        System.out.println("aaaaaaaaaaa");
        return R.ok(schedulerJobService.list(query));
    }

}
