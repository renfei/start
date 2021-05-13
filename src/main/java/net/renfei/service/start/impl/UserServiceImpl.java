package net.renfei.service.start.impl;

import com.aliyun.oss.ServiceException;
import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.exception.NeedU2FException;
import net.renfei.repository.dao.start.TStartUserMapper;
import net.renfei.repository.dao.start.model.TStartUser;
import net.renfei.repository.dao.start.model.TStartUserExample;
import net.renfei.sdk.utils.*;
import net.renfei.security.ConfidentialRankEnum;
import net.renfei.service.BaseService;
import net.renfei.service.start.PermissionService;
import net.renfei.service.start.UserService;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.web.api.start.ao.SignInAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户服务
 *
 * @author renfei
 */
@Service
public class UserServiceImpl extends BaseService implements UserService, UserDetailsService {
    private final TStartUserMapper userMapper;
    private final PermissionService permissionService;

    protected UserServiceImpl(SystemConfig systemConfig,
                              TStartUserMapper userMapper,
                              PermissionService permissionService) {
        super(systemConfig);
        this.userMapper = userMapper;
        this.permissionService = permissionService;
    }


    @Override
    public UserDTO getUserByUserName(String userName) {
        if (BeanUtils.isEmpty(userName)) {
            return null;
        }
        TStartUserExample example = new TStartUserExample();
        TStartUserExample.Criteria criteria = example.createCriteria()
                .andIsDeletedEqualTo(false);
        if (StringUtils.isChinaPhone(userName)) {
            // 手机号登陆
            criteria.andPhoneEqualTo(userName);
        } else if (StringUtils.isEmail(userName)) {
            // 邮箱登陆
            criteria.andEmailEqualTo(userName);
        } else {
            // 用户名登陆
            criteria.andUserNameEqualTo(userName);
        }
        TStartUser user = ListUtils.getOne(userMapper.selectByExample(example));
        UserDTO userDTO = new UserDTO(permissionService);
        org.springframework.beans.BeanUtils.copyProperties(user, userDTO);
        userDTO.setConfidentialRank(ConfidentialRankEnum.parse(user.getConfidentialRank()));
        return userDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO user = this.getUserByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("Username Not Found");
        }
        return user;
    }

    @Override
    public UserDTO signIn(SignInAO signInAo) {
        UserDTO userDTO = this.getUserByUserName(signInAo.getUserName());
        if (userDTO == null) {
            throw new BusinessException("用户未注册，请先注册");
        }
        if (userDTO.getStateCode() < 0) {
            throw new BusinessException("当前账户已被冻结，请联系我们解冻");
        }
        if (userDTO.getStateCode() < 1) {
            // TODO 发送激活邮件
            throw new BusinessException("当前账户邮箱未激活，我们已经为您发送了一封激活邮件");
        } else if (StringUtils.isChinaPhone(signInAo.getUserName())
                && userDTO.getStateCode() < 2) {
            throw new BusinessException("当前账户手机号码未通过验证，不能使用手机号码登录");
        }
        if (userDTO.getLockTime() != null) {
            // 判断锁定时间
            if (new Date().before(userDTO.getLockTime())) {
                String lockDate = DateUtils.getDate(userDTO.getLockTime(), "yyyy-MM-dd hh:mm:ss");
                throw new BusinessException("当前账户已被锁定至[" + lockDate + "]，请稍后再试");
            }
        }
        if (!BeanUtils.isEmpty(userDTO.getTotp()) && BeanUtils.isEmpty(signInAo.getTOtp())) {
            throw new NeedU2FException("当前账户开启了两步认证(U2F)，需要提供两步认证码");
        }
        if (signInAo.getUseVerCode()) {
            // TODO 使用验证码验证
        } else {
            // 使用密码登陆
            if (!PasswordUtils.verifyPassword(signInAo.getPassword(), userDTO.getPassword())) {
                this.recordNumberOfPasswordErrorByUuid(userDTO.getUuid());
                throw new ServiceException("用户名或密码错误");
            }
        }
        // 两步认证
        if (!BeanUtils.isEmpty(userDTO.getTotp())) {
            if (!GoogleAuthenticator.authcode(signInAo.getTOtp(), userDTO.getTotp())) {
                throw new ServiceException("两步认证(U2F)失败，请重试");
            }
            // TODO 记录TOTP为已经使用
        }
        return userDTO;
    }

    @Override
    public void recordNumberOfPasswordErrorByUuid(String uuid) {
        TStartUserExample example = new TStartUserExample();
        TStartUserExample.Criteria criteria = example.createCriteria()
                .andIsDeletedEqualTo(false)
                .andUuidEqualTo(uuid);
        TStartUser user = ListUtils.getOne(userMapper.selectByExample(example));
        // 记录错误，如果错误超过3次，锁定时间为 (N-3)*10分钟
        user.setTrialErrorTimes(user.getTrialErrorTimes() + 1);
        if (user.getTrialErrorTimes() > 3) {
            // 锁定时间
            user.setLockTime(DateUtils.nextMinutes((user.getTrialErrorTimes() - 3) * 10));
        }
        userMapper.updateByPrimaryKeySelective(user);
    }
}
