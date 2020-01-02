/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.feign;

import com.crush.test.spring.feign.caller.FeignCaller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 由于test只会加载test 目录下的configuration，会忽略HttpClientConfiguration
 * @author crush_lee
 * @version $Id: FeignCallerTest.java, v 0.1 2019年11月06日 09:21 crush_lee Exp $
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class FeignCallerTest {
    @Autowired
    private FeignCaller feignCaller;
    @Test
    public void test(){
        int count = 10;
        for(int i=0;i<count;i++){
            final String name="thread-"+i;
            Thread thread= new Thread(() -> {
                int j=10;
                while (j>0){
                    feignCaller.get(name);
                    j--;
                }
            },name);
            thread.run();
        }

    }
}