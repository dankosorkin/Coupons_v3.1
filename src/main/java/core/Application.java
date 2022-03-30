package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import core.filters.LoginFilter;
import core.sessions.SessionContext;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public FilterRegistrationBean<LoginFilter> filterRegistrationBean(SessionContext context) {
		FilterRegistrationBean<LoginFilter> filterRegistration = new FilterRegistrationBean<>();
		LoginFilter loginFilter = new LoginFilter(context);
		filterRegistration.setFilter(loginFilter);
		filterRegistration.addUrlPatterns("/admin/*", "/company/*", "/customer/*");
		return filterRegistration;
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("core.controllers")).paths(PathSelectors.ant("/**")).build();
	}

}
