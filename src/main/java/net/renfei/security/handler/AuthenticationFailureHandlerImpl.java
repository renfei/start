package net.renfei.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证失败处理器
 *
 * @author renfei
 */
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException exception) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        APIResult apiResult;
        if (exception instanceof BadCredentialsException ||
                exception instanceof UsernameNotFoundException) {
            apiResult = APIResult.builder().code(StateCode.Unauthorized)
                    .message("账户名或者密码输入错误!").build();
        } else if (exception instanceof LockedException) {
            apiResult = APIResult.builder().code(StateCode.Unauthorized)
                    .message("账户被锁定，请联系管理员!").build();
        } else if (exception instanceof CredentialsExpiredException) {
            apiResult = APIResult.builder().code(StateCode.Unauthorized)
                    .message("密码过期，请联系管理员!").build();
        } else if (exception instanceof AccountExpiredException) {
            apiResult = APIResult.builder().code(StateCode.Unauthorized)
                    .message("账户过期，请联系管理员!").build();
        } else if (exception instanceof DisabledException) {
            apiResult = APIResult.builder().code(StateCode.Unauthorized)
                    .message("账户被禁用，请联系管理员!").build();
        } else {
            apiResult = APIResult.builder().code(StateCode.Unauthorized)
                    .message("登录失败!").build();
        }
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(new ObjectMapper().writeValueAsString(apiResult));
        out.flush();
        out.close();
    }
}
