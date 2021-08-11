package net.renfei.web.api.quartz;

import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.quartz.QuartzService;
import net.renfei.web.BaseController;
import net.renfei.web.api.quartz.ao.QuartzAO;
import net.renfei.web.api.quartz.vo.QuartzJobsVO;
import org.quartz.Job;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Quartz的调度器 包含了任务的增删改查
 *
 * @author renfei
 */
@RestController
@RequestMapping("/api/quartz")
public class QuartzController extends BaseController {
    private final QuartzService quartzService;

    protected QuartzController(SystemConfig systemConfig,
                               QuartzService quartzService) {
        super(systemConfig);
        this.quartzService = quartzService;
    }

    @GetMapping("job")
    public APIResult<List<QuartzJobsVO>> getAllJob() {
        return new APIResult<>(quartzService.getAllJobs());
    }

    @PostMapping("job")
    public APIResult addJob(@RequestBody QuartzAO quartzAO) {
        if (BeanUtils.isEmpty(quartzAO.getCron())) {
            throw new BusinessException("Cron表达式不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getJobClass())) {
            throw new BusinessException("JobClass不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getJobName())) {
            throw new BusinessException("JobName不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getJobGroupName())) {
            throw new BusinessException("JobGroupName不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getTriggerName())) {
            throw new BusinessException("TriggerName不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getTriggerGroupName())) {
            throw new BusinessException("TriggerGroupName不能为空");
        }
        Class<?> clazz;
        try {
            clazz = Class.forName(quartzAO.getJobClass());
        } catch (ClassNotFoundException e) {
            throw new BusinessException("JobClass[" + quartzAO.getJobClass() + "]不存在。");
        }
        if (!clazz.isAssignableFrom(Job.class)) {
            throw new BusinessException("JobClass[" + quartzAO.getJobClass() + "]未实现[org.quartz.Job]接口，不可用于定时任务。");
        }
        quartzService.addJob(quartzAO.getJobName(), quartzAO.getJobGroupName(),
                quartzAO.getTriggerName(), quartzAO.getTriggerGroupName(), clazz, quartzAO.getCron());
        return APIResult.success();
    }

    @PutMapping("job")
    public APIResult modifyJobTime(@RequestBody QuartzAO quartzAO) {
        if (BeanUtils.isEmpty(quartzAO.getCron())) {
            throw new BusinessException("Cron表达式不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getJobName())) {
            throw new BusinessException("JobName不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getJobGroupName())) {
            throw new BusinessException("JobGroupName不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getTriggerName())) {
            throw new BusinessException("TriggerName不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getTriggerGroupName())) {
            throw new BusinessException("TriggerGroupName不能为空");
        }
        quartzService.modifyJobTime(quartzAO.getJobName(), quartzAO.getJobGroupName(),
                quartzAO.getTriggerName(), quartzAO.getTriggerGroupName(), quartzAO.getCron());
        return APIResult.success();
    }

    @DeleteMapping("job")
    public APIResult removeJob(@RequestBody QuartzAO quartzAO) {
        if (BeanUtils.isEmpty(quartzAO.getJobName())) {
            throw new BusinessException("JobName不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getJobGroupName())) {
            throw new BusinessException("JobGroupName不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getTriggerName())) {
            throw new BusinessException("TriggerName不能为空");
        }
        if (BeanUtils.isEmpty(quartzAO.getTriggerGroupName())) {
            throw new BusinessException("TriggerGroupName不能为空");
        }
        quartzService.removeJob(quartzAO.getJobName(), quartzAO.getJobGroupName(),
                quartzAO.getTriggerName(), quartzAO.getTriggerGroupName());
        return APIResult.success();
    }
}
