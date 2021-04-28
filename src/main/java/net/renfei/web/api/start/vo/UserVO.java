package net.renfei.web.api.start.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author renfei
 */
@Data
public class UserVO {
    private Date createTime;
    private String uuid;
    private String userName;
    private String email;
    private String phone;
    private Date registrationDate;
    private String registrationIp;
    private String lastName;
    private String firstName;
    private Date lastLogin;
}
