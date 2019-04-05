package com.crush.test.spring.restemplate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
 * @date 2019/4/3
 */
@RestController
@RequestMapping("/test/restemplate")
public class SimpleController {
    @RequestMapping("/get")
    public String get(String name){
        return name;
    }
    @RequestMapping("/get/json/result")
    public PostFormDomain getJsonResult(PostFormDomain postFormDomain){
        return postFormDomain;
    }
    @RequestMapping(value = "/post/form",method = RequestMethod.POST)
    public PostFormDomain postForm(PostFormDomain postFormDomain){
        return postFormDomain;
    }
    @RequestMapping(value = "/post/form/base64",method = RequestMethod.POST)
    public PostFormDomain postFormBase64(PostFormDomain postFormDomain){
        return postFormDomain;
    }
}
