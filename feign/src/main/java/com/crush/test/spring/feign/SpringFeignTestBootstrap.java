/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 *
 * @author crush_lee
 * @version $Id: SpringFeignTestBootstrap.java, v 0.1 2019年11月06日 09:22 crush_lee Exp $
 */
@SpringBootApplication(scanBasePackages = "com.crush.test.spring")
@EnableFeignClients(basePackages = "com.crush.test.spring")
public class SpringFeignTestBootstrap {
    public static void main(String...argc) {
        SpringApplication.run(SpringFeignTestBootstrap.class);
    }
}