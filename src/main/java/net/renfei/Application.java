package net.renfei;

import net.renfei.config.RenFeiBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * 程序启动入口
 *
 * @author renfei
 */
@EnableAsync
@EnableCaching
@EnableOpenApi
@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties
public class Application {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        SpringApplication build = builder.build();
        build.setBanner(new RenFeiBanner());
        build.run(args);
    }
}
