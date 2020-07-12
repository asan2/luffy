package com.jcokee;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@RefreshScope
public class LuffyApplication {

  public static void main(String[] args) {
    SpringApplication.run(LuffyApplication.class, args);
  }

  @Configuration
  public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
      this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
      successHandler.setTargetUrlParameter("redirectTo");
      successHandler.setDefaultTargetUrl(adminContextPath + "/");
      http.authorizeRequests()
          .antMatchers(adminContextPath + "/assets/**").permitAll()
          .antMatchers(adminContextPath + "/login").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
          .logout().logoutUrl(adminContextPath + "/logout").and()
          .httpBasic().and()//Enables HTTP-Basic support. This is needed for the Spring Boot Admin Client to register.
          .csrf()
          .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//	Enables CSRF-Protection using Cookies
          .ignoringAntMatchers(
              adminContextPath + "/instances",//	Disables CRSF-Protection the endpoint the Spring Boot Admin Client uses to register.
              adminContextPath + "/actuator/**"//Disables CRSF-Protection for the actuator endpoints.
          );
    }
  }

}
