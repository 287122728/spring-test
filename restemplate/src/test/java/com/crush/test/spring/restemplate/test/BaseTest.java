package com.crush.test.spring.restemplate.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

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
 * @date 2019/4/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  //RANDOM_PORT 启动Tomcat
public class BaseTest {
    @LocalServerPort
    protected int port;
    protected String baseUrl(){
        return "http://localhost:"+port;
    }
    @Autowired
    protected TestRestTemplate testRestTemplate;
}
