package net.renfei.web.api.quartz.ao;

import lombok.Data;

/**
 * @author renfei
 */
@Data
public class QuartzAO {
    String jobName;
    String jobGroupName;
    String triggerName;
    String triggerGroupName;
    String jobClass;
    String cron;
}
