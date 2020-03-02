/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.crush.test.spring.mvc.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import static com.crush.test.spring.mvc.async.AsyncUtil.PRINT_STR;
import static com.crush.test.spring.mvc.async.AsyncUtil.currentThreadName;

/**
 * Description TODO
 * @author Apple
 * @version $Id: ServiceC.java, v 0.1 
 * @create 2020年03月02日 14:01 crush_lee Exp $
 */
@Service
@Slf4j
public class ServiceC {
    @Async
    public void a2b2c(String threadName){
        print(threadName);
    }
    private void print(String threadName){
        log.info(PRINT_STR,threadName,currentThreadName());
    }
}