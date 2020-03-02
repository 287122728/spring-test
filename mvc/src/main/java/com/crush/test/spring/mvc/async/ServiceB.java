/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.crush.test.spring.mvc.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import static com.crush.test.spring.mvc.async.AsyncUtil.PRINT_STR;
import static com.crush.test.spring.mvc.async.AsyncUtil.currentThreadName;

/**
 * Description TODO
 * @author Apple
 * @version $Id: ServiceB.java, v 0.1 
 * @create 2020年03月02日 14:00 crush_lee Exp $
 */
@Service
@Slf4j
public class ServiceB {
    @Autowired
    private ServiceC serviceC;
    @Async
    public void a2b(String threadName){
        print(threadName);
    }
    public void a2b2b(String threadName){
        print(threadName);
        bMethod(currentThreadName());
    }
    @Async
    public void a2bs2bs(String threadName){
        print(threadName);
        bMethod(currentThreadName());
    }
    @Async
    public void a2b2c(String threadName){
        print(threadName);
        serviceC.a2b2c(currentThreadName());
    }
    @Async
    public void bMethod(String threadName){
        print(threadName);
    }

    private void print(String threadName){
        log.info(PRINT_STR,threadName,currentThreadName());
    }
}