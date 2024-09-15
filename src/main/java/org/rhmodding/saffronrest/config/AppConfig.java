package org.rhmodding.saffronrest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@PropertySource(value="file:./saffron.properties")
public class AppConfig {

    @Value("${saffron.filepath}")
    private String filepath;

    @Value("${saffron.picpath}")
    private String picpath;

}
