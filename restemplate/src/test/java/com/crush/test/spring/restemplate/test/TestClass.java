/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.restemplate.test;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * Description 一个测试类
 * @author crush_lee
 * @version $Id: TestClass.java, v 0.1 
 * @create 2019年11月08日 11:03 crush_lee Exp $
 */
public class TestClass {
    /**
     * 一个参数
     */
    private String param;

    /**
     * 一个测试方法
     * @param param1 第一个参数
     * @param param2 第二个参数
     * @return 返回信息描述
     */
    public TestDomain getDomain(String param1,String param2){
        return null;
    }

    /**
     * 请求第三放接口，wiki地址：https://xxx.xx
     * @param param1 一个参数
     * @return 返回参数描述
     */
    public String requestThird(String param1){
        return null;
    }

    /**
     * controller接口，对外xx组开放api
     * @param testDomain 参数描述
     * @return 返回参数描述
     */
    @RequestMapping(path = "/test",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "接口描述", notes = "xx组接口")
    public TestDomain controllerMethod(@RequestBody TestDomain testDomain){
        return null;
    }
}

/**
 * 测试类的内部类
 */
@Data
class TestDomain{

}