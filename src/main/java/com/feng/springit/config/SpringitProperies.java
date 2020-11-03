package com.feng.springit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "springit")
public class SpringitProperies {

    /**
     * Welcome message
     */
    public String welcomeMsg = "Hello world";

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setwelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }
}
