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
 * @version $Id: ServiceA.java, v 0.1 
 * @create 2020年03月02日 14:00 crush_lee Exp $
 */
@Service
@Slf4j
public class ServiceA {
    @Autowired
    private ServiceB serviceB;
    public void a2a(String threadName){
        print(threadName);
        aMethod(threadName);
    }
    public void a2b(String threadName){
        print(threadName);
        serviceB.a2b(currentThreadName());
    }
    public void a2b2b(String threadName){
        print(threadName);
        serviceB.a2b2b(currentThreadName());
    }
    @Async
    public void a2b2c(String threadName){
        print(threadName);
        serviceB.a2b2c(currentThreadName());
    }
    public void a2bs2bs(String threadName){
        print(threadName);
        serviceB.a2bs2bs(currentThreadName());
    }
    @Async
    public void aMethod(String threadName){
        print(threadName);
    }
    private void print(String threadName){
        log.info(PRINT_STR,threadName,currentThreadName());
    }
}