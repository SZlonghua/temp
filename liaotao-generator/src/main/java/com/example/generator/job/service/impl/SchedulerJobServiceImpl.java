package com.example.generator.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commom.model.PageUtil;
import com.example.commom.model.Query;
import com.example.commom.service.impl.BaseServiceImpl;
import com.example.generator.job.entity.SchedulerJob;
import com.example.generator.job.mapper.SchedulerJobMapper;
import com.example.generator.job.service.SchedulerJobService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时调度表 服务实现类
 * </p>
 *
 * @author liaotao
 * @since 2020-09-30
 */
@Service
public class SchedulerJobServiceImpl extends BaseServiceImpl<SchedulerJobMapper, SchedulerJob> implements SchedulerJobService {

    public PageUtil<SchedulerJob> list(Query query){
        Page<SchedulerJob> page = new Page<>(query.getPage(), query.getSize());

        QueryWrapper<SchedulerJob> queryWrapper = new QueryWrapper<>();


        IPage<SchedulerJob> page1 = page(page, queryWrapper);

        return null;
    }

}
