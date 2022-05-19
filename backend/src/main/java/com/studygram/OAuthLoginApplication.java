package com.studygram;

import com.studygram.config.AppProperties;
import com.studygram.config.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        CorsProperties.class,
        AppProperties.class
})
public class OAuthLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthLoginApplication.class, args);
    }

}
