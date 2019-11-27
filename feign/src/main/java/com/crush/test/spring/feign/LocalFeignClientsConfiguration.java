package com.crush.test.spring.feign;

import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;

@Configuration
public class LocalFeignClientsConfiguration extends FeignClientsConfiguration {
    private int connectionTimeOut=1000;
    private int readTimeOut=1000;
    /**
     * feign日志级别
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * feign超时设置
     *
     * @return
     */
    @Bean
    Request.Options feignOptions() {
        return new Request.Options(connectionTimeOut, readTimeOut);
    }
}
