package net.renfei.service.start;

import net.renfei.sdk.entity.ListData;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.web.api.start.ao.SignInAO;

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

    ListData<UserDTO> getUserList(UserDTO user, String userName, String email,
                                  String phone, String pages, String rows);
}
