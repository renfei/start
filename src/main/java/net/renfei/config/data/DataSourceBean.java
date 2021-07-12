package net.renfei.config.data;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数据源配置
 *
 * @author renfei
 */
@Configuration
@MapperScan("net.renfei.repository")
public class DataSourceBean {
    /**
     * 主数据源
     *
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSourcePrimary() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 其他数据源
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.discuz")
    public DataSource dataSourceDiscuz() {
        return DruidDataSourceBuilder.create().build();
    }
}
