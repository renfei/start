package net.renfei.service.start.dto;

import lombok.Data;
import net.renfei.service.start.PermissionService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author renfei
 */
@Data
public class UserDTO implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;
    private final PermissionService permissionService;
    private Long id;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;
    private String uuid;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private Date registrationDate;
    private String totp;
    private String registrationIp;
    private Integer trialErrorTimes;
    private Date lockTime;
    private Integer stateCode;
    private String lastName;
    private String firstName;
    private Date lastLogin;

    public UserDTO(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissionService.getAuthoritiesByUser(this);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (lockTime == null) {
            return true;
        }
        return new Date().after(lockTime);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (stateCode == null) {
            return true;
        }
        return stateCode > 0;
    }
}
