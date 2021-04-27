package net.renfei.service.start.impl;

import net.renfei.config.RenFeiConfig;
import net.renfei.repository.dao.start.TStartOperationLogMapper;
import net.renfei.repository.dao.start.model.TStartOperationLogWithBLOBs;
import net.renfei.service.BaseService;
import net.renfei.service.start.OperationLogService;
import net.renfei.service.start.dto.OperationLogDTO;
import net.renfei.service.start.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static net.renfei.web.BaseController.SESSION_KEY;

/**
 * @author renfei
 */
@Service
public class OperationLogServiceForDataBaseImpl extends BaseService implements OperationLogService {
    private final TStartOperationLogMapper operationLogMapper;

    protected OperationLogServiceForDataBaseImpl(RenFeiConfig renFeiConfig,
                                                 TStartOperationLogMapper operationLogMapper) {
        super(renFeiConfig);
        this.operationLogMapper = operationLogMapper;
    }


    @Override
    public void log(OperationLogDTO operationLog) {
        if (operationLog != null) {
            operationLog.setId(null);
            TStartOperationLogWithBLOBs operationLogDo = new TStartOperationLogWithBLOBs();
            BeanUtils.copyProperties(operationLog, operationLogDo);
            operationLogMapper.insertSelective(operationLogDo);
        }
    }

    @Override
    public void log(OperationLogDTO operationLog, ServletRequestAttributes servletRequestAttributes) {
        if (operationLog != null) {
            operationLog.setId(null);
            TStartOperationLogWithBLOBs operationLogDo = new TStartOperationLogWithBLOBs();
            BeanUtils.copyProperties(operationLog, operationLogDo);
            HttpServletRequest request = servletRequestAttributes.getRequest();
            UserDTO user = null;
            if ("SESSION".equals(renFeiConfig.getAuthMode())) {
                Object session = request.getSession().getAttribute(SESSION_KEY);
                if (session instanceof UserDTO) {
                    user = (UserDTO) session;
                }
            } else {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.getPrincipal() instanceof UserDTO) {
                    user = (UserDTO) authentication.getPrincipal();
                }
            }
            if (user != null) {
                operationLogDo.setOperUserUuid(user.getUuid());
                operationLogDo.setOperUserName(user.getUsername());
            }
            operationLogMapper.insertSelective(operationLogDo);
        }
    }
}
