package net.renfei.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Swagger3Config implements WebMvcConfigurer {
    private final SystemConfig systemConfig;

    public Swagger3Config(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .globalRequestParameters(getGlobalRequestParameters())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.renfei.web.api"))
                .paths(PathSelectors.regex("/api/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("RenFei.Net", "https://www.renfei.net/", "i@renfei.net");
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("API Document")
                .termsOfServiceUrl("https://www.renfei.net/")
                .contact(contact)
                .version(systemConfig.getVersion())
                .build();
    }

    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> requestParameters = new ArrayList<>();
        RequestParameterBuilder requestParameterBuilder = new RequestParameterBuilder();
        requestParameters.add(requestParameterBuilder
                .name("signature")
                .description("加密签名")
                .in(ParameterType.QUERY)
                .required(true)
                .build()
        );
        requestParameters.add(requestParameterBuilder
                .name("timestamp")
                .description("时间戳")
                .in(ParameterType.QUERY)
                .required(true)
                .build()
        );
        requestParameters.add(requestParameterBuilder
                .name("nonce")
                .description("随机数")
                .in(ParameterType.QUERY)
                .required(true)
                .build()
        );
        requestParameters.add(requestParameterBuilder
                .name("aesKeyId")
                .description("秘钥ID")
                .in(ParameterType.QUERY)
                .required(true)
                .build()
        );
        return requestParameters;
    }
}
