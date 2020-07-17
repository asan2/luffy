package com.jcokee;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@RefreshScope
@MapperScan("com.jcokee.mapper")
public class LuffyApplication {

  public static void main(String[] args) {
    SpringApplication.run(LuffyApplication.class, args);
  }

  @Configuration
  public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
  {
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
      http.csrf().ignoringAntMatchers("/druid/*");
    }
  }
}
