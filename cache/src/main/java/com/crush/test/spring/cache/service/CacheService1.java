/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.cache.service;

import com.crush.test.spring.cache.domain.TestCacheDomain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author crush_lee
 * @version $Id: CacheService1.java, v 0.1 2019年09月10日 13:24 crush_lee Exp $
 */
@Service
@CacheConfig(cacheNames = "user1")
public class CacheService1 {
    @Cacheable(key = "#name")
    public TestCacheDomain cache(String  name){
        System.out.println(String.format("get from db %s",name));
        TestCacheDomain testCacheDomain=new TestCacheDomain();
        testCacheDomain.setName(name);
        testCacheDomain.setDesc(System.nanoTime()+"");
        return testCacheDomain;
    }
    @CachePut(key="#name")
    public TestCacheDomain put(String  name){
        TestCacheDomain testCacheDomain=new TestCacheDomain();
        testCacheDomain.setName(name);
        testCacheDomain.setDesc(System.nanoTime()+"");
        return testCacheDomain;
    }
    @CacheEvict(key = "#name")
    public void clear(String name){

    }
}