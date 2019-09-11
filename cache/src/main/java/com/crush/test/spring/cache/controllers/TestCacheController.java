///**
// * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
// */
//package com.crush.test.spring.cache.controllers;
//
//import com.crush.test.spring.cache.domain.TestCacheDomain;
//import com.crush.test.spring.cache.service.CacheService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Random;
//
///**
// *
// * @author crush_lee
// * @version $Id: TestCacheController.java, v 0.1 2019年09月09日 17:14 crush_lee Exp $
// */
//@RestController
//public class TestCacheController {
//    @Autowired
//    private CacheService cacheService;
//    @RequestMapping("add")
//    public TestCacheDomain add(String test){
//        return cacheService.put(test);
//    }
//    @RequestMapping("get")
//    public TestCacheDomain get(String test){
//        return cacheService.cache(test);
//    }
//    @RequestMapping("random")
//    public void random(){
//        cacheService.cache("test"+new Random().nextLong());
//    }
//}