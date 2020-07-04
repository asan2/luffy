package com.jcokee.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server.config.redis-timeout")
public class RedisTimeoutConfig {

   private long userinfoHour;

    public long getUserinfoHour() {
        return userinfoHour;
    }

    public void setUserinfoHour(long userinfoHour) {
        this.userinfoHour = userinfoHour;
    }
}
