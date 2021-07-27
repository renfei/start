package net.renfei.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.DateUtils;
import net.renfei.sdk.utils.StringUtils;
import net.renfei.service.start.SecretKeyService;
import net.renfei.service.start.dto.SecretKeyDTO;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * 前端签名验签过滤器
 * 防止篡改、重放攻击
 *
 * @author renfei
 */
@Slf4j
@Component
public class SignAuthFilter implements Filter {
    private final SystemConfig systemConfig;
    private final SecretKeyService secretKeyService;

    public SignAuthFilter(SystemConfig systemConfig,
                          SecretKeyService secretKeyService) {
        this.systemConfig = systemConfig;
        this.secretKeyService = secretKeyService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SignAuthFilter Init.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestUri = request.getRequestURI();
        if (requestUri.startsWith("/api/")) {
            boolean isMatch = false;
            if (!BeanUtils.isEmpty(systemConfig.getSignAuth().getIgnoreSignUri())) {
                Set<String> uriSet = new HashSet<>(systemConfig.getSignAuth().getIgnoreSignUri());
                for (String uri : uriSet
                ) {
                    isMatch = requestUri.contains(uri);
                    if (isMatch) {
                        break;
                    }
                }
            } else {
                isMatch = true;
            }
            if (isMatch) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String aesKeyId = request.getParameter("aesKeyId");
            APIResult apiResult = checkParameter(signature, "signature");
            if (apiResult.getCode() != 200) {
                sendResult(apiResult, servletResponse);
                return;
            }
            apiResult = checkParameter(timestamp, "timestamp");
            if (apiResult.getCode() != 200) {
                sendResult(apiResult, servletResponse);
                return;
            }
            apiResult = checkParameter(nonce, "nonce");
            if (apiResult.getCode() != 200) {
                sendResult(apiResult, servletResponse);
                return;
            }
            apiResult = checkParameter(aesKeyId, "aesKeyId");
            if (apiResult.getCode() != 200) {
                sendResult(apiResult, servletResponse);
                return;
            }
            // 重放攻击检查
            long serverTimestamp = DateUtils.getUnixTimestamp();
            long difference = serverTimestamp - Long.parseLong(timestamp);
            if (Long.parseLong(timestamp) > serverTimestamp ||
                    difference > systemConfig.getSignAuth().getSignTimeout()) {
                sendResult(APIResult.builder()
                        .code(StateCode.Failure)
                        .message("签名验签失败。请求已过期，请重试。")
                        .build(), servletResponse);
                return;
            }
            // 防篡改签名校验
            SecretKeyDTO secretKeyDTO;
            try {
                secretKeyDTO = secretKeyService.getSecretKeyById(aesKeyId);
            } catch (BusinessException businessException) {
                sendResult(APIResult.builder()
                        .code(StateCode.Failure)
                        .message("签名验签失败。" + businessException.getMessage())
                        .build(), servletResponse);
                return;
            }
            String serverSignature = StringUtils.signature(secretKeyDTO.getPublicKey(), timestamp, nonce);
            if (!serverSignature.equals(signature)) {
                sendResult(APIResult.builder()
                        .code(StateCode.Failure)
                        .message("签名验签失败。")
                        .build(), servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("SignAuthFilter Destroy.");
    }

    private APIResult checkParameter(String parameter, String name) {
        if (BeanUtils.isEmpty(parameter)) {
            return APIResult.builder()
                    .code(StateCode.BadRequest)
                    .message("签名验签失败。[" + name + "]参数不能为空")
                    .build();
        }
        if ("timestamp".equals(name)) {
            try {
                Long.parseLong(parameter);
            } catch (NumberFormatException nfe) {
                return APIResult.builder()
                        .code(StateCode.BadRequest)
                        .message("签名验签失败。[timestamp]参数不正确")
                        .build();
            }
        }
        return APIResult.success();
    }

    private void sendResult(APIResult apiResult, ServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.println(JSON.toJSONString(apiResult));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
