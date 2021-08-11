package net.renfei.service.quartz;

import net.renfei.web.api.quartz.vo.QuartzJobsVO;

import java.util.List;

/**
 * @author renfei
 */
public interface QuartzService {
    List<QuartzJobsVO> getAllJobs();

    void addJob(String jobName, String jobGroupName, String triggerName,
                String triggerGroupName, Class jobClass, String cron);

    void modifyJobTime(String jobName, String jobGroupName,
                       String triggerName, String triggerGroupName, String cron);

    void removeJob(String jobName, String jobGroupName,
                   String triggerName, String triggerGroupName);

    void startJobs();

    void shutdownJobs();
}
