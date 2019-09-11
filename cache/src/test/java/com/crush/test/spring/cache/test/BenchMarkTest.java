/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.cache.test;

import com.crush.test.spring.cache.SpringCacheTestBoostrap;
import com.crush.test.spring.cache.service.CacheService;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author crush_lee
 * @version $Id: BenchMarkTest.java, v 0.1 2019年09月09日 19:46 crush_lee Exp $
 */
@State(Scope.Thread)
public class BenchMarkTest {
    private CacheService cacheService;
    private static final String CACHE_NAME="test1";
    @Setup
    public void init(){
        ApplicationContext applicationContext=SpringApplication.run(SpringCacheTestBoostrap.class);
        cacheService=applicationContext.getBean(CacheService.class);
        cacheService.put(CACHE_NAME);
    }
    @Benchmark
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void put(){
        cacheService.put("test"+new Random().nextLong());
    }
    @Benchmark
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void get(){
        cacheService.cache(CACHE_NAME);
    }
    /**
     * 把byte转换成M
     * @param bytes
     * @return
     */
    public long byteToM(long bytes){
        long kb =  (bytes / 1024 / 1024);
        return kb;
    }
    private void printMemory(){
        Runtime runtime = Runtime.getRuntime();
        System.out.println("free memory:"+byteToM(runtime.freeMemory())+"M");
        System.out.println("total memory:"+byteToM(runtime.totalMemory())+"M");
        System.out.println("max memory:"+byteToM(runtime.maxMemory())+"M");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(BenchMarkTest.class.getSimpleName()+".*")
                .build();

        new Runner(opt).run();
    }
}