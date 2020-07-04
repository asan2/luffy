package com.jcokee.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server.config.custom")
public class CustomConfig {

  private String firstConfig;

  public String getFirstConfig() {
    return firstConfig;
  }

  public void setFirstConfig(String firstConfig) {
    this.firstConfig = firstConfig;
  }
}
