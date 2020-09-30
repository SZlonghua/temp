package com.example.scheduler.test;

import com.example.scheduler.job.HelloJob;
import com.example.scheduler.job.SimpleJob;
import com.example.scheduler.job.SimpleJob2;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class HelloJobTest {

    @Test
    void helloJobTest() throws SchedulerException {

        //创建一个scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().put("skey", "svalue");

        //创建一个Trigger
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .usingJobData("t1", "tv1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
                        .repeatForever()).build();
        trigger.getJobDataMap().put("t2", "tv2");

        //创建一个job
        JobDetail job = newJob(HelloJob.class)
                .usingJobData("j1", "jv1")
                .withIdentity("myjob", "mygroup").build();
        job.getJobDataMap().put("j2", "jv2");

        //注册trigger并启动scheduler
        scheduler.scheduleJob(job,trigger);
        scheduler.start();


        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    void helloJobTest1() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = newJob(SimpleJob.class)
                .withIdentity("job1", "group1")
                .build();

        CronTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule("0/20 * * * * ?"))
                .build();

        sched.scheduleJob(job, trigger);
        sched.start();

        JobDetail job2 = newJob(SimpleJob2.class)
                .withIdentity("job2", "group1")
                .build();

        CronTrigger trigger2 = newTrigger()
                .withIdentity("trigger2", "group1")
                .withSchedule(cronSchedule("0/10 * * * * ?"))
                .build();
        sched.scheduleJob(job2,trigger2);


        try {
            Thread.sleep(100000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
