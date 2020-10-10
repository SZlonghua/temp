package com.example.scheduler.job.mapper;

import com.example.scheduler.job.entity.SchedulerJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 定时调度表 Mapper 接口
 * </p>
 *
 * @author liaotao
 * @since 2020-10-09
 */
public interface SchedulerJobMapper extends BaseMapper<SchedulerJob> {
    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}
