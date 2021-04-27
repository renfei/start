package net.renfei.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author renfei
 */
@Slf4j
@Component
public class CronJobs {
    @Scheduled(cron = "0 30 2 * * ? ")
    public void demo() {
        log.info("== demoJob <<<<");
    }
}
