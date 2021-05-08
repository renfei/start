package net.renfei.service.start.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author renfei
 */
@Data
public class OperationLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Date operDate;

    private String operUserUuid;

    private String operUserName;

    private String operType;

    private String operModel;

    private String operIp;

    private String className;

    private String methodName;

    private String operDescribe;

    private String params;

    private String returning;
}
