package com.front.end.pk.encrypt.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * Basename of Locale application_en.properties is 'application'
     * Following bean configure this basename
     * @return
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {

        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("application");  // message file application_en.properties
        return resourceBundleMessageSource;
    }

    /**
     *  Mapper from
     *  Entity to Dto , Dto to Entity
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
