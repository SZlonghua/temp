package com.example.scheduler.job.task;

import com.example.commom.util.JsonUtils;
import com.example.scheduler.job.entity.SchedulerJob;
import com.example.scheduler.job.util.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class ScheduleJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String jsonJob = context.getMergedJobDataMap().getString(ScheduleUtils.JOB_PARAM_KEY);
        SchedulerJob scheduleJob = JsonUtils.toBean(jsonJob, SchedulerJob.class);
        log.info("================:"+jsonJob);
    }
}
