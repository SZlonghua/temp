CREATE TABLE `scheduler_job` (
  `job_id` varchar(64) NOT NULL COMMENT '主键',
  `task_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  `task_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `execute_class` varchar(255) DEFAULT NULL COMMENT '执行类',
  `cron` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `state` varchar(8) DEFAULT NULL COMMENT '状态',
  `create_on` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modified_on` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modified_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时调度表';
