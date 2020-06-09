package com.github.iceant.ueditor.spring.boot.starter;

import com.github.iceant.ueditor.spring.boot.starter.services.StorageService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Servlet;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurerAdapter.class })
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class,	ValidationAutoConfiguration.class })
@EnableConfigurationProperties(UEditorProperties.class)
@ComponentScan
public class UEditorAutoConfiguration {
    private final UEditorProperties properties;

    public UEditorAutoConfiguration(UEditorProperties properties) {
        this.properties = properties;
    }

    @Bean(name = {"ueditorStorageService"})
    @ConditionalOnMissingBean(name = {"ueditorStorageServie"})
    public StorageService storageService(){
        return new StorageService(properties);
    }
}
