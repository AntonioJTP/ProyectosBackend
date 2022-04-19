package com.backend.BS41;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:myConfiguration.properties")
@Getter
public class Configuration {
    @Value("${value1}")
    private String value1;

    @Value("${value2}")
    private String value2;

}
