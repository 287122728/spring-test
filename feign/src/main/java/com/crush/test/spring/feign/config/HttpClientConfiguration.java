/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.feign.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description TODO
 * @author Apple
 * @version $Id: HttpClientConfiguration.java, v 0.1 
 * @create 2019年11月27日 14:28 crush_lee Exp $
 */
@Configuration
@TestConfiguration
public class HttpClientConfiguration {
    private static final int MAX_CONN_TOTAL=5;
    private static final int MAX_CONN_PER_ROUTE=2;
    //@Configuration
    //@ConditionalOnClass(ApacheHttpClient.class)
    //@ConditionalOnProperty(value = "feign.httpclient.enabled", matchIfMissing = true)
    //protected static class HttpClientConfig {
        @Bean
        public HttpClient httpClient() {
            return HttpClientBuilder
                    .create()
                    .setMaxConnTotal(MAX_CONN_TOTAL)
                    .setMaxConnPerRoute(MAX_CONN_PER_ROUTE)
                    .build();
        }
    //}
}