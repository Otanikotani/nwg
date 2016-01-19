package com.dev.configs;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;


@Configuration
@ComponentScan("com.dev")
@EnableWebMvc
public class DispatcherConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = Logger.getLogger(DispatcherConfig.class);

    @PostConstruct
    public void init() {
        logger.info("DispatcherConfig init");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/").setCachePeriod(31556926);
    }

}
