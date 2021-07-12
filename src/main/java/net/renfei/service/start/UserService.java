package net.renfei.service.start;

import net.renfei.sdk.entity.ListData;
import net.renfei.security.ConfidentialRankEnum;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.web.api.start.ao.ResetPasswordAO;
import net.renfei.web.api.start.ao.SignInAO;
import net.renfei.web.api.start.ao.UserAO;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author renfei
 */
public interface UserService {
    /**
     * 根据用户名获取用户
     *
     * @param userName 用户名
     * @return {@link UserDTO}
     */
    UserDTO getUserByUserName(String userName);

    /**
     * 用户登录
     *
     * @param signInAo {@link SignInAO}
     * @return {@link UserDTO}
     */
    UserDTO signIn(SignInAO signInAo);

    /**
     * 记录密码错误次数
     *
     * @param uuid 用户UUID
     */
    int recordNumberOfPasswordErrorByUuid(String uuid);

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
    ListData<UserDTO> getUserList(UserDTO user, String userName, String email,
                                  String phone, String pages, String rows);

    /**
     * 添加用户
     *
     * @param user    当前登录用户
     * @param userAo  添加用户请求
     * @param request 请求对象
     */
    void addUser(UserDTO user, UserAO userAo, HttpServletRequest request);

    /**
     * 修改用户资料
     *
     * @param user   当前登录用户
     * @param userAo 添加用户请求
     */
    void updateUser(UserDTO user, UserAO userAo);

    /**
     * 修改当前登录用户的密码
     *
     * @param user          当前登录用户
     * @param resetPassword 密码修改请求
     */
    void resetPassword(UserDTO user, ResetPasswordAO resetPassword);

    /**
     * 修改指定用户的密码
     *
     * @param signUser      当前登录用户
     * @param userId        修改密码的用户ID
     * @param resetPassword 密码修改请求
     */
    void resetPassword(UserDTO signUser, Long userId, ResetPasswordAO resetPassword);

    /**
     * 为用户定密级
     *
     * @param signUser         当前登录用户
     * @param userId           定密的目标用户
     * @param confidentialRank 密级
     */
    void setUserConfidentialRank(UserDTO signUser, Long userId, ConfidentialRankEnum confidentialRank);
}
