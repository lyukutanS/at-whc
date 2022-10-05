package ru.softrust.automation.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationOptions {

    @Bean(name = "browser")
    public String browser() {
        return "chrome";
    }

    @Bean(name = "timeout")
    public Integer timeout() {
        return 10;
    }

    @Bean(name = "video")
    public Boolean videoFlag() {
        return false;
    }

}
