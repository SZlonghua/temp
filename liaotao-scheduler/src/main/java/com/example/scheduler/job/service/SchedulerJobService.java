package com.example.scheduler.job.service;

import com.example.scheduler.job.entity.SchedulerJob;
import com.example.commom.service.BaseService;
import com.example.commom.model.PageUtil;
import com.example.commom.model.Query;
import java.util.List;
/**
 * <p>
 * 定时调度表 服务类
 * </p>
 *
 * @author liaotao
 * @since 2020-10-09
 */
public interface SchedulerJobService extends BaseService<SchedulerJob> {
    /**
    * 分页查询
    * @param query
    *          查询条件
    * @return  分页信息
    */
    PageUtil<SchedulerJob> list(Query query);

    /**
    * 详情
    * @param id
    *          主键
    * @return  详情
    */
    SchedulerJob info(String id);

    /**
    * 保存
    * @param schedulerJob
    *          实体
    * @return  是否成功
    */
    Boolean saveEntity(SchedulerJob schedulerJob);

    /**
    * 更新
    * @param schedulerJob
    *          实体
    * @return  是否成功
    */
    Boolean updateEntity(SchedulerJob schedulerJob);

    /**
    * 删除
    * @param ids
    *          主键
    * @return  是否成功
    */
    Boolean delete(List<String> ids);

    /**
     * 立即执行
     */
    void run(List<String> jobIds);

    /**
     * 暂停运行
     */
    void pause(List<String> jobIds);

    /**
     * 恢复运行
     */
    void resume(List<String> jobIds);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(List<String> jobIds, String state);
}
