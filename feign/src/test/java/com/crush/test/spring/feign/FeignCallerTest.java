/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.feign;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author crush_lee
 * @version $Id: FeignCallerTest.java, v 0.1 2019年11月06日 09:21 crush_lee Exp $
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes =SpringFeignTestBootstrap.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FeignCallerTest {
    @Autowired
    private FeignCaller feignCaller;
    @Test
    public void test(){
        feignCaller.get("11");
    }
}