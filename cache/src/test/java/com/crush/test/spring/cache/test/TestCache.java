/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.cache.test;

import com.crush.test.spring.cache.domain.TestCacheDomain;
import com.crush.test.spring.cache.service.CacheService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author crush_lee
 * @version $Id: TestCache.java, v 0.1 2019年09月06日 20:20 crush_lee Exp $
 */
public class TestCache extends BaseTest {
    @Autowired
    private CacheService cacheService;
    @Test
    public void testReadFromCache(){
        TestCacheDomain testCacheDomain=cacheService.cache("test");
        TestCacheDomain testCacheDomain1=cacheService.cache("test");
        Assert.assertEquals(testCacheDomain,testCacheDomain1);
    }
    @Test
    public void testClearCache(){
        TestCacheDomain testCacheDomain=cacheService.cache("test");
        cacheService.clear("test");
        TestCacheDomain testCacheDomain1=cacheService.cache("test");
        Assert.assertNotEquals(testCacheDomain,testCacheDomain1);
    }
    @Test
    public void putAndReadFromCache(){
        TestCacheDomain testCacheDomain=cacheService.put("test");
        TestCacheDomain testCacheDomain1=cacheService.cache("test");
        Assert.assertEquals(testCacheDomain,testCacheDomain1);
    }
    @Test
    public void testExpireWith20Seconds() throws InterruptedException {
        TestCacheDomain testCacheDomain=cacheService.put("test");
        TimeUnit.SECONDS.sleep(20);
        TestCacheDomain testCacheDomain1=cacheService.cache("test");
        Assert.assertNotEquals(testCacheDomain,testCacheDomain1);
    }
    @Test
    public void testCacheSize(){
        Map<String,TestCacheDomain> m1=new HashMap<>();
        Map<String,TestCacheDomain> m2=new HashMap<>();
        TestCacheDomain tmp=null;
        int size=11;
        for(int i=0;i<size;i++){
            String key="test"+i;
            tmp=cacheService.put(key);
            m1.put(key,tmp);
        }
        for(int i=0;i<size;i++){
            String key="test"+i;
            tmp=cacheService.cache(key);
            m2.put(key,tmp);
        }
        int count=0;
        for (Map.Entry<String, TestCacheDomain> entry : m2.entrySet()) {
            String k = entry.getKey();
            TestCacheDomain v = entry.getValue();
            if (v.equals(m1.get(k))) {
                count++;
            }
        }
        //如果cache配置的size大于等于，count与size相等；如果小于，count的数值具有随机性
        Assert.assertEquals(size,count);
    }
    @Test
    public void testMemorySize(){
        System.out.println(new Random().nextLong());
        System.out.println(new Random().nextLong());
    }
}