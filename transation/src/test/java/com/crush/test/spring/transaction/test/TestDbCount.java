package com.crush.test.spring.transaction.test;

import com.crush.test.spring.transaction.domain.Account;
import com.crush.test.spring.transaction.service.CountService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

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
 * @date 2019/5/30
 */
@Slf4j
public class TestDbCount extends BaseTest {
    private Account account;
    @Autowired
    private CountService countService;
    private int countTime = 10000;
    private int threadNum = 20;
    private long useTime;

    @Before
    public void init() {
        account = countService.insert();
        useTime = System.currentTimeMillis();
    }

    @After
    public void after() {
        account = countService.get(account);
        useTime = System.currentTimeMillis() - useTime;
        log.info("after {} plus {} times,result amount is {},use time {} ms,isolation level {}",
                account.getId(), countTime, account.getAmount(), useTime, countService
                        .isolationLevel());
    }

    @Test
    public void multiThreadCountWithAtomic() {
        /**
         * ---------------------------
         * set session transaction isolation level repeatable read
         * SELECT @@tx_isolation
         * ---------------------------
         * 前提：数据库为REPEATABLE-READ(mysql默认)
         * 结果：结果10000
         */
        /**
         * ---------------------------
         * set session transaction isolation level read committed
         * ---------------------------
         * 前提：数据库为READ-COMMITTED
         * 结果：结果10000
         */
        /**
         * ---------------------------
         * set session transaction isolation level read uncommitted
         * ---------------------------
         * 前提：数据库为READ-UNCOMMITTED
         * 结果：结果10000
         */
        /**
         * ---------------------------
         * set session transaction isolation level serializable
         * ---------------------------
         * 前提：数据库为SERIALIZABLE
         * 结果：结果10000
         */
        AtomicInteger atomicInteger = new AtomicInteger();
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool
                (threadNum);
        for (int i = 0; i < threadNum; i++) {
            threadPoolExecutor.execute(runnable(atomicInteger));
        }
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()) {

        }
    }

    @Test
    public void multiThreadCountWithForEach() {
        /**
         * ---------------------------
         * set session transaction isolation level repeatable read
         * SELECT @@tx_isolation
         * ---------------------------
         * 前提：数据库为REPEATABLE-READ(mysql默认)
         * 结果：10000
         */
        /**
         * ---------------------------
         * set session transaction isolation level read committed
         * ---------------------------
         * 前提：数据库为READ-COMMITTED
         * 结果：10000
         */
        /**
         * ---------------------------
         * set session transaction isolation level read uncommitted
         * ---------------------------
         * 前提：数据库为READ-UNCOMMITTED
         * 结果：10000
         */
        /**
         * ---------------------------
         * set session transaction isolation level serializable
         * ---------------------------
         * 前提：数据库为SERIALIZABLE
         * 结果：10000
         */
        int numPerThread = countTime / threadNum;
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool
                (threadNum);
        for (int i = 0; i < threadNum; i++) {
            threadPoolExecutor.execute(runnable4ForEach(numPerThread));
        }
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()) {

        }
    }

    @Test
    public void oneThreadCount() {
        AtomicInteger atomicInteger = new AtomicInteger();
        while (plus(atomicInteger)) {

        }
    }

    private boolean plus(AtomicInteger atomicInteger) {
        int count;
        if ((count = atomicInteger.incrementAndGet()) <= countTime) {
            countService.plus(account);
            log.info("{}-plus", count);
            return true;
        } else {
            return false;
        }
    }

    private Runnable runnable(AtomicInteger atomicInteger) {
        return () -> {
            while (plus(atomicInteger)) {

            }
        };
    }

    private Runnable runnable4ForEach(int times) {
        return () -> {
            for (int i = 0; i < times; i++) {
                countService.plus(account);
                log.info("{}-plus", i);
            }
        };
    }

}
