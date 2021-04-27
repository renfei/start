package net.renfei.security.filter;

import net.renfei.config.RenFeiConfig;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.start.UserService;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.util.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static net.renfei.web.BaseController.SESSION_KEY;

/**
 * @author renfei
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final RenFeiConfig renFeiConfig;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public JwtTokenFilter(RenFeiConfig renFeiConfig,
                          JwtTokenUtil jwtTokenUtil,
                          UserService userService) {
        this.renFeiConfig = renFeiConfig;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        final String auth = request.getHeader("Authorization");
        UserDetails userDetails = null;
        if ("SESSION".equals(renFeiConfig.getAuthMode())) {
            HttpSession session = request.getSession();
            Object sessionObject = session.getAttribute(SESSION_KEY);
            if (sessionObject instanceof UserDTO) {
                userDetails = (UserDTO) sessionObject;
            } else {
                chain.doFilter(request, response);
                return;
            }
        } else {
            if (BeanUtils.isEmpty(auth) || !auth.startsWith("Bearer ")) {
                chain.doFilter(request, response);
                return;
            }
            final String token = auth.split(" ")[1].trim();
            if (!jwtTokenUtil.validate(token)) {
                chain.doFilter(request, response);
                return;
            }
            userDetails = userService.getUserByUserName(jwtTokenUtil.getUsername(token));
        }
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ? null : userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}
