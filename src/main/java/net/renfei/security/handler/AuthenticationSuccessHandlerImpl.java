package net.renfei.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证成功处理器
 *
 * @author renfei
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    /**
     * 认证成功处理器
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        APIResult apiResult = APIResult.builder()
                .code(StateCode.OK)
                .message("登陆成功！")
                .build();
        out.write(new ObjectMapper().writeValueAsString(apiResult));
        out.flush();
        out.close();
    }
}
