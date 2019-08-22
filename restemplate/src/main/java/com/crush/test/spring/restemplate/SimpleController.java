package com.crush.test.spring.restemplate;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
    @RequestMapping(value = "/post/json",method = RequestMethod.POST)
    public PostFormDomain postJson(@RequestBody  PostFormDomain postFormDomain){
        return postFormDomain;
    }
    @RequestMapping(value = "/get/json",method = RequestMethod.GET)
    public PostFormDomain getJson(PostFormDomain postFormDomain){
        return postFormDomain;
    }
    @RequestMapping(value = "/post/xml/resp/xml",method = RequestMethod.POST,produces = "application/xml")
    public PostFormDomain postXml(@RequestBody  PostFormDomain postFormDomain){
        return postFormDomain;
    }
    @RequestMapping(value = "/post/xml/resp/json",method = RequestMethod.POST)
    public PostFormDomain postXmlAndRespJson(@RequestBody  PostFormDomain postFormDomain){
        return postFormDomain;
    }
    @RequestMapping(value = "/post/xml/str",method = RequestMethod.POST,consumes = "application/xml",produces = "application/xml")
    public String postXmlStr(@RequestBody String xml) throws IOException {
        return xml;
    }
}
