package com.jcokee.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server.config")
public class TestConfig {

    private String testone;
    private String testtwo;

    public String getTestone() {
        return testone;
    }

    public void setTestone(String testone) {
        this.testone = testone;
    }

    public String getTesttwo() {
        return testtwo;
    }

    public void setTesttwo(String testtwo) {
        this.testtwo = testtwo;
    }
}
