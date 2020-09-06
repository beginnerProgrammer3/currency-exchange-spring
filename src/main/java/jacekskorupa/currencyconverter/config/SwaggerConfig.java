package jacekskorupa.currencyconverter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket swaggerIndexApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Index")
                .select()
                .apis(RequestHandlerSelectors.basePackage("jacekskorupa.currencyconverter.controllers"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Index Api").build());
    }

    @Bean
    public Docket swaggerCurrencyExchangeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("CurrencyExchange")
                .select()
                .apis(RequestHandlerSelectors.basePackage("jacekskorupa.currencyconverter.controllers"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Currency Exchange Api").build());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
