package net.renfei.service.start.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.exception.NeedU2FException;
import net.renfei.repository.dao.start.TStartUserMapper;
import net.renfei.repository.dao.start.TStartUserPasswordHistoryMapper;
import net.renfei.repository.dao.start.model.TStartUser;
import net.renfei.repository.dao.start.model.TStartUserExample;
import net.renfei.repository.dao.start.model.TStartUserPasswordHistory;
import net.renfei.sdk.entity.ListData;
import net.renfei.sdk.utils.*;
import net.renfei.security.ConfidentialRankEnum;
import net.renfei.service.BaseService;
import net.renfei.service.start.PermissionService;
import net.renfei.service.start.UserService;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.web.api.start.ao.ResetPasswordAO;
import net.renfei.web.api.start.ao.SignInAO;
import net.renfei.web.api.start.ao.UserAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 用户服务
 *
 * @author renfei
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseService implements UserService, UserDetailsService {
    private final TStartUserMapper userMapper;
    private final PermissionService permissionService;
    private final TStartUserPasswordHistoryMapper passwordHistoryMapper;

    protected UserServiceImpl(SystemConfig systemConfig,
                              TStartUserMapper userMapper,
                              PermissionService permissionService,
                              TStartUserPasswordHistoryMapper passwordHistoryMapper) {
        super(systemConfig);
        this.userMapper = userMapper;
        this.permissionService = permissionService;
        this.passwordHistoryMapper = passwordHistoryMapper;
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
            throw new BusinessException("当前账户已被冻结，请联系管理员解冻");
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
            throw new BusinessException("暂不支持使用验证码登陆");
        } else {
            // 使用密码登陆
            if (!PasswordUtils.verifyPassword(signInAo.getPassword(), userDTO.getPassword())) {
                int trialErrorTimes = this.recordNumberOfPasswordErrorByUuid(userDTO.getUuid());
                throw new BusinessException("用户名或密码错误");
            } else {
                // 清除密码错误次数
                TStartUser user = new TStartUser();
                user.setId(userDTO.getId());
                user.setTrialErrorTimes(0);
                userMapper.updateByPrimaryKeySelective(user);
            }
        }
        // 两步认证
        if (!BeanUtils.isEmpty(userDTO.getTotp())) {
            if (!GoogleAuthenticator.authcode(signInAo.getTOtp(), userDTO.getTotp())) {
                throw new BusinessException("两步认证(U2F)失败，请重试");
            }
            // TODO 记录TOTP为已经使用
        }
        return userDTO;
    }

    @Override
    public int recordNumberOfPasswordErrorByUuid(String uuid) {
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
        return user.getTrialErrorTimes();
    }

    /**
     * 根据查询条件获取用户列表
     *
     * @param user     当前登录用户
     * @param userName 查询用户名
     * @param email    查询用户电邮
     * @param phone    查询用户手机号
     * @param pages    页数
     * @param rows     每页行数
     * @return
     */
    @Override
    public ListData<UserDTO> getUserList(UserDTO user, String userName, String email,
                                         String phone, String pages, String rows) {
        TStartUserExample example = new TStartUserExample();
        TStartUserExample.Criteria criteria = example.createCriteria()
                .andIsDeletedEqualTo(false);
        if (user != null) {
            criteria.andConfidentialRankLessThanOrEqualTo(user.getConfidentialRank().getRank());
        }
        if (!BeanUtils.isEmpty(userName)) {
            criteria.andUserNameLike("%" + userName + "%");
        }
        if (!BeanUtils.isEmpty(email)) {
            criteria.andEmailLike("%" + email + "%");
        }
        if (!BeanUtils.isEmpty(phone)) {
            criteria.andPhoneLike("%" + phone + "%");
        }
        return selectByExample(example, pages, rows);
    }

    /**
     * 添加用户
     *
     * @param user   当前登录用户
     * @param userAo 添加用户请求
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDTO user, UserAO userAo, HttpServletRequest request) {
        TStartUser userDo = new TStartUser();
        if (BeanUtils.isEmpty(userAo.getUserName())) {
            throw new BusinessException("用户名不能为空");
        }
        // 检查重复
        TStartUserExample example = new TStartUserExample();
        example.createCriteria()
                .andIsDeletedEqualTo(false)
                .andUserNameEqualTo(userAo.getUserName());
        if (selectByExample(example, "1", "1").getTotal() > 0) {
            throw new BusinessException("用户名已被占用");
        }
        if (!BeanUtils.isEmpty(userAo.getEmail())) {
            example = new TStartUserExample();
            example.createCriteria()
                    .andIsDeletedEqualTo(false)
                    .andEmailEqualTo(userAo.getEmail());
            if (selectByExample(example, "1", "1").getTotal() > 0) {
                throw new BusinessException("电子邮箱已被占用");
            }
        }
        if (!BeanUtils.isEmpty(userAo.getPhone())) {
            example = new TStartUserExample();
            example.createCriteria()
                    .andIsDeletedEqualTo(false)
                    .andPhoneEqualTo(userAo.getPhone());
            if (selectByExample(example, "1", "1").getTotal() > 0) {
                throw new BusinessException("电话号码已被占用");
            }
        }
        if (userAo.getConfidentialRank() != null) {
            checkMaxConfidentialRank(userAo.getConfidentialRank());
            checkConfidentialRank(user.getConfidentialRank(), userAo.getConfidentialRank());
            userDo.setConfidentialRank(userAo.getConfidentialRank().getRank());
        }
        org.springframework.beans.BeanUtils.copyProperties(userAo, userDo);
        userDo.setCreateTime(new Date());
        userDo.setIsDeleted(false);
        userDo.setUuid(UUID.randomUUID().toString());
        userDo.setRegistrationDate(new Date());
        userDo.setRegistrationIp(IpUtils.getIpAddress(request));
        userDo.setStateCode(0);
        userDo.setConfidentialRank(0);
        userMapper.insertSelective(userDo);
        if (!BeanUtils.isEmpty(userAo.getPassword())) {
            // 记录密码历史记录
            try {
                userDo.setPassword(PasswordUtils.createHash(userAo.getPassword()));
            } catch (PasswordUtils.CannotPerformOperationException e) {
                log.error("CannotPerformOperationException", e);
                throw new RuntimeException(e.getMessage());
            }
            TStartUserPasswordHistory passwordHistory = new TStartUserPasswordHistory();
            passwordHistory.setPassword(userDo.getPassword());
            passwordHistory.setSetTime(new Date());
            passwordHistory.setSetUserId(user.getId());
            passwordHistory.setSetUserName(user.getUsername());
            passwordHistoryMapper.insertSelective(passwordHistory);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDTO user, UserAO userAo) {
        TStartUserExample example = new TStartUserExample();
        example.createCriteria()
                .andIsDeletedEqualTo(false)
                .andIdEqualTo(userAo.getId());
        UserDTO oldUser = ListUtils.getOne(selectByExample(example, "1", "1").getData());
        if (oldUser == null) {
            throw new BusinessException("根据【id】未找到要修改的数据");
        }
        if (oldUser.getEmail().equals(userAo.getEmail()) && !BeanUtils.isEmpty(userAo.getEmail())) {
            example = new TStartUserExample();
            example.createCriteria()
                    .andIsDeletedEqualTo(false)
                    .andEmailEqualTo(userAo.getEmail());
            if (selectByExample(example, "1", "1").getTotal() > 0) {
                throw new BusinessException("电子邮箱已被占用");
            }
            oldUser.setEmail(userAo.getEmail());
        }
        if (oldUser.getPhone().equals(userAo.getPhone()) && !BeanUtils.isEmpty(userAo.getPhone())) {
            example = new TStartUserExample();
            example.createCriteria()
                    .andIsDeletedEqualTo(false)
                    .andPhoneEqualTo(userAo.getPhone());
            if (selectByExample(example, "1", "1").getTotal() > 0) {
                throw new BusinessException("电话号码已被占用");
            }
            oldUser.setPhone(userAo.getPhone());
        }
        TStartUser userDo = new TStartUser();
        if (userAo.getConfidentialRank() != null) {
            checkMaxConfidentialRank(userAo.getConfidentialRank());
            checkConfidentialRank(user.getConfidentialRank(), userAo.getConfidentialRank());
            oldUser.setConfidentialRank(userAo.getConfidentialRank());
            userDo.setConfidentialRank(userAo.getConfidentialRank().getRank());
        }
        org.springframework.beans.BeanUtils.copyProperties(userAo, userDo);
        userMapper.updateByPrimaryKey(userDo);
        if (!BeanUtils.isEmpty(userAo.getPassword())) {
            // 记录密码历史记录
            try {
                userDo.setPassword(PasswordUtils.createHash(userAo.getPassword()));
            } catch (PasswordUtils.CannotPerformOperationException e) {
                log.error("CannotPerformOperationException", e);
                throw new RuntimeException(e.getMessage());
            }
            TStartUserPasswordHistory passwordHistory = new TStartUserPasswordHistory();
            passwordHistory.setPassword(userDo.getPassword());
            passwordHistory.setSetTime(new Date());
            passwordHistory.setSetUserId(user.getId());
            passwordHistory.setSetUserName(user.getUsername());
            passwordHistoryMapper.insertSelective(passwordHistory);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(UserDTO user, ResetPasswordAO resetPassword) {
        if (resetPassword.getOldPassword().equals(resetPassword.getNewPassword())) {
            throw new BusinessException("新旧密码不能一致");
        }
        if (!PasswordUtils.verifyPassword(resetPassword.getOldPassword(), user.getPassword())) {
            throw new BusinessException("旧密码错误");
        }
        TStartUser userDo = new TStartUser();
        userDo.setId(user.getId());
        userDo.setUpdateTime(new Date());
        try {
            userDo.setPassword(PasswordUtils.createHash(resetPassword.getNewPassword()));
        } catch (PasswordUtils.CannotPerformOperationException e) {
            log.error("CannotPerformOperationException", e);
            throw new RuntimeException(e.getMessage());
        }
        userMapper.updateByPrimaryKeySelective(userDo);
        TStartUserPasswordHistory passwordHistory = new TStartUserPasswordHistory();
        passwordHistory.setPassword(userDo.getPassword());
        passwordHistory.setSetTime(new Date());
        passwordHistory.setSetUserId(user.getId());
        passwordHistory.setSetUserName(user.getUsername());
        passwordHistoryMapper.insertSelective(passwordHistory);
    }

    /**
     * 修改指定用户的密码
     *
     * @param signUser      当前登录用户
     * @param userId        修改密码的用户ID
     * @param resetPassword 密码修改请求
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(UserDTO signUser, Long userId, ResetPasswordAO resetPassword) {
        TStartUserExample example = new TStartUserExample();
        example.createCriteria().andIsDeletedEqualTo(false).andIdEqualTo(userId);
        TStartUser user = ListUtils.getOne(userMapper.selectByExample(example));
        if (user == null) {
            throw new BusinessException("根据userId未查询到用户，请查证后重试");
        }
        if (resetPassword.getOldPassword().equals(resetPassword.getNewPassword())) {
            throw new BusinessException("新旧密码不能一致");
        }
        if (!PasswordUtils.verifyPassword(resetPassword.getOldPassword(), user.getPassword())) {
            throw new BusinessException("旧密码错误");
        }
        user.setUpdateTime(new Date());
        try {
            user.setPassword(PasswordUtils.createHash(resetPassword.getNewPassword()));
        } catch (PasswordUtils.CannotPerformOperationException e) {
            log.error("CannotPerformOperationException", e);
            throw new RuntimeException(e.getMessage());
        }
        userMapper.updateByPrimaryKeySelective(user);
        TStartUserPasswordHistory passwordHistory = new TStartUserPasswordHistory();
        passwordHistory.setPassword(user.getPassword());
        passwordHistory.setSetTime(new Date());
        passwordHistory.setSetUserId(signUser.getId());
        passwordHistory.setSetUserName(signUser.getUsername());
        passwordHistoryMapper.insertSelective(passwordHistory);
    }

    /**
     * 为用户定密级
     *
     * @param signUser         当前登录用户
     * @param userId           定密的目标用户
     * @param confidentialRank 密级
     */
    @Override
    public void setUserConfidentialRank(UserDTO signUser, Long userId, ConfidentialRankEnum confidentialRank) {
        TStartUserExample example = new TStartUserExample();
        example.createCriteria().andIsDeletedEqualTo(false).andIdEqualTo(userId);
        TStartUser user = ListUtils.getOne(userMapper.selectByExample(example));
        if (user == null) {
            throw new BusinessException("根据userId未查询到用户，请查证后重试");
        }
        checkMaxConfidentialRank(confidentialRank);
        checkConfidentialRank(signUser.getConfidentialRank(), confidentialRank);
        user.setConfidentialRank(confidentialRank.getRank());
        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }

    private ListData<UserDTO> selectByExample(TStartUserExample example, String pages, String rows) {
        Page<TStartUser> page =
                PageHelper.startPage(NumberUtils.parseInt(pages, 1), NumberUtils.parseInt(rows, 10));
        userMapper.selectByExample(example);
        ListData<UserDTO> postListData = new ListData<>();
        postListData.setTotal(page.getTotal());
        postListData.setShowRows(page.getPageSize());
        postListData.setTotalPages(page.getPages());
        postListData.setCurrentPage(page.getPageNum());
        List<UserDTO> postList = new CopyOnWriteArrayList<>();
        if (page.getResult() != null && !page.getResult().isEmpty()) {
            for (TStartUser user : page.getResult()
            ) {
                UserDTO userDTO = new UserDTO(permissionService);
                org.springframework.beans.BeanUtils.copyProperties(user, userDTO);
                userDTO.setConfidentialRank(ConfidentialRankEnum.parse(user.getConfidentialRank()));
                postList.add(userDTO);
            }
        }
        postListData.setData(postList);
        return postListData;
    }
}
