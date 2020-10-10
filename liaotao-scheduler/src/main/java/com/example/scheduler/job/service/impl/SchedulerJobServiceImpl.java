package com.example.scheduler.job.service.impl;

import com.example.commom.service.impl.BaseServiceImpl;
import com.example.scheduler.job.constant.ScheduleStatus;
import com.example.scheduler.job.entity.SchedulerJob;
import com.example.scheduler.job.mapper.SchedulerJobMapper;
import com.example.scheduler.job.service.SchedulerJobService;
import com.example.scheduler.job.util.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commom.model.PageUtil;
import com.example.commom.model.Query;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.commom.util.UUIDUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * <p>
 * 定时调度表 服务实现类
 * </p>
 *
 * @author liaotao
 * @since 2020-10-09
 */
@Service
public class SchedulerJobServiceImpl extends BaseServiceImpl<SchedulerJobMapper, SchedulerJob> implements SchedulerJobService {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void init(){
        List<SchedulerJob> scheduleJobList = baseMapper.selectList(null);
        for(SchedulerJob scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public PageUtil<SchedulerJob> list(Query query) {
        Page<SchedulerJob> page = new Page<>(query.getPage(), query.getSize());

        QueryWrapper<SchedulerJob> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(query.getDirect())&&
            StringUtils.isNotEmpty(query.getOrder())){
            queryWrapper.orderBy(true,"ASC".equals(query.getDirect().toUpperCase()),query.getOrder());
        }else {
            queryWrapper.orderByDesc("modified_on");
        }

        IPage<SchedulerJob> pageList = page(page, queryWrapper);
        return PageUtil.of(pageList.getRecords(),pageList.getTotal(),pageList.getSize(),pageList.getCurrent());
    }

    @Override
    public SchedulerJob info(String id) {
        return getById(id);
    }

    @Override
    @Transactional
    public Boolean saveEntity(SchedulerJob schedulerJob) {
        schedulerJob.setJobId(UUIDUtil.getUUID());
        boolean result = save(schedulerJob);
        ScheduleUtils.createScheduleJob(scheduler, schedulerJob);
        return result;
    }

    @Override
    @Transactional
    public Boolean updateEntity(SchedulerJob schedulerJob) {
        boolean result = saveOrUpdate(schedulerJob);
        ScheduleUtils.updateScheduleJob(scheduler,schedulerJob);
        return result;
    }

    @Override
    @Transactional
    public Boolean delete(List<String> ids) {
        boolean result = removeByIds(ids);
        ScheduleUtils.deleteScheduleJobs(scheduler,ids);
        return result;
    }

    @Override
    public void run(List<String> jobIds) {
        QueryWrapper<SchedulerJob> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("job_id",jobIds);
        List<SchedulerJob> list = list(queryWrapper);
        for(SchedulerJob schedulerJob : list){
            ScheduleUtils.run(scheduler, schedulerJob);
        }
    }

    @Override
    public void pause(List<String> jobIds) {
        for(String jobId : jobIds){
            ScheduleUtils.pauseJob(scheduler, jobId);
        }
        updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
    }

    @Override
    public void resume(List<String> jobIds) {
        for(String jobId : jobIds){
            ScheduleUtils.resumeJob(scheduler, jobId);
        }
        updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }

    @Override
    public int updateBatch(List<String> jobIds, String state) {
        Map<String, Object> map = new HashMap<>();
        map.put("jobIds", jobIds);
        map.put("state", state);
        return baseMapper.updateBatch(map);
    }
}
