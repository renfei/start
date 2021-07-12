package net.renfei.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author renfei
 */
@Configuration
public class DruidConfig {
    @Value("${spring.datasource.druid.login.username}")
    private String loginUsername;
    @Value("${spring.datasource.druid.login.password}")
    private String loginPassword;
    @Value("${spring.datasource.druid.allow}")
    private String allow;
    @Value("${spring.datasource.druid.deny}")
    private String deny;

    @Bean
    public ServletRegistrationBean druidServlet() {// 主要实现web监控的配置处理
        //表示进行druid监控的配置处理操作
        // 登录URL http://localhost:port/druid/login.html
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        //白名单
        servletRegistrationBean.addInitParameter("allow", allow);
        //黑名单(共同存在时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", deny);
        //用户名
        System.out.println("loginPassword:" + loginPassword);
        System.out.println("loginUsername:" + loginUsername);
        servletRegistrationBean.addInitParameter("loginUsername", loginUsername);
        // 密码
        servletRegistrationBean.addInitParameter("loginPassword", loginPassword);
        // 是否可以重置数据源
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;

    }

    /**
     * 监控
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        //所有请求进行监控处理
        filterRegistrationBean.addUrlPatterns("/druid/*");
        //排除
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
        filterRegistrationBean.setName("druidFilter");
        return filterRegistrationBean;
    }
}
