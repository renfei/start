package net.renfei.web.api.quartz.vo;

import lombok.Data;

/**
 * @author renfei
 */
@Data
public class QuartzJobsVO {
    private String jobDetailName;
    private String jobCronExpression;
    private String timeZone;
    private String groupName;
}
