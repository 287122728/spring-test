/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.crush.test.spring.mvc.async.test;

import com.crush.test.spring.mvc.async.ServiceA;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import static com.crush.test.spring.mvc.async.AsyncUtil.currentThreadName;

/**
 * Description TODO
 * @author Apple
 * @version $Id: AsyncTest.java, v 0.1 
 * @create 2020年03月02日 15:16 crush_lee Exp $
 */
public class AsyncTest extends BaseTest {
    @Autowired
    private ServiceA serviceA;
    @Test
    /**
     * b异步，c异步。
     * 最终b、c都是异步执行
     */
    public void testA2b2c(){
        serviceA.a2b2c(currentThreadName());
        sleep();

    }

    /**
     * b异步
     * 最终b异步执行
     */
    @Test
    public void testA2b(){
        serviceA.a2b(currentThreadName());
    }

    /**
     * b同步，b调用b方法异步
     * ！！！最终异步不生效！！！
     */
    @Test
    public void testA2b2b(){
        serviceA.a2b2b(currentThreadName());
    }

    /**
     * b异步，b调用b方法异步
     * 最终b两个方法在同一个异步线程执行
     */
    @Test
    public void testA2bs2bs(){
        serviceA.a2bs2bs(currentThreadName());
    }
    private void sleep(){
        try {
            TimeUnit.MILLISECONDS.sleep(3);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}