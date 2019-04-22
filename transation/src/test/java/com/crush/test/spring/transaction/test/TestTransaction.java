package com.crush.test.spring.transaction.test;

import com.crush.test.spring.transaction.service.ProxyService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class TestTransaction extends  BaseTest{
    @Autowired
    private ProxyService proxyService;
    @Before
    public void printCountBefor(){
        log.info("--before-- "+proxyService.info());
    }
    @After
    public void printCountAfter(){
        log.info("--after-- "+proxyService.info());
    }
    @Test
    public void testRollbackWithException(){
        //前提条件：外层事物不允许异常
        //结果；存在异常同时回滚
        proxyService.testRollBackWithException();
    }
    @Test(expected = RuntimeException.class)
    public void notRollBack(){
        //前提条件：外层事物允许异常
        //结果；存在异常不回滚
        proxyService.notRollBack();
    }
    @Test(expected = RuntimeException.class)
    public void testWithTwoTransaction(){
        //前提条件：外层事物不允许异常，内部事物默认配置
        //结果：全部回滚
        proxyService.testTwoTransaction();
    }
    @Test(expected = RuntimeException.class)
    public void testTwoTransactionWithOutNotRollBack(){
        //前提条件：外层事物允许异常，内部事物默认配置
        //结果：应用外层事物策略，均不回滚
        //分析：
        proxyService.testTwoTransactionWithOutNotRollBack();
    }
    @Test(expected = RuntimeException.class)
    public void testTwoTransactionWithOutNotRollBackAndInnerRollBack(){
        //前提条件：外层事物允许异常，内部允许异常
        //结果：内部不回滚，外部回滚
        //分析：？
        proxyService.testTwoTransactionWithOutNotRollBackAndInnerRollBack();
    }

}
