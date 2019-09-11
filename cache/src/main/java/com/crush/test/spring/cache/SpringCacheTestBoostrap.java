/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *
 * @author crush_lee
 * @version $Id: SpringCacheTestBoostrap.java, v 0.1 2019年09月06日 20:04 crush_lee Exp $
 */
@SpringBootApplication
@EnableCaching
public class SpringCacheTestBoostrap {
    public static void main(String...argc){
        SpringApplication.run(SpringCacheTestBoostrap.class);
    }
}
