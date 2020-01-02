/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.feign.caller;

import com.crush.test.spring.feign.config.LocalFeignClientsConfiguration;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author crush_lee
 * @version $Id: FeignCaller.java, v 0.1 2019年11月06日 09:16 crush_lee Exp $
 */
@FeignClient(url = "http://localhost:8899",path = "/test/restemplate",name="simpleController",configuration =
        LocalFeignClientsConfiguration.class)
public interface FeignCaller {
    @RequestMapping(path = "/get",method = RequestMethod.GET)
    String get(@RequestParam("name") String name);
}