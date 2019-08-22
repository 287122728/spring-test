package com.crush.test.spring.sharding.jdbc;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * Title: TODO
 * </p>
 * <p>
 * Description: TODO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: 客如云
 * </p>
 *
 * @author crush_lee
 * @date 2019/4/11
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class Bootstrap {
    public static void main(String...argc){
        SpringApplication.run(Bootstrap.class);
    }
}
