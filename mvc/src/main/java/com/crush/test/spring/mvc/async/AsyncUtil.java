/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.crush.test.spring.mvc.async;

/**
 * Description TODO
 * @author Apple
 * @version $Id: AsyncUtil.java, v 0.1 
 * @create 2020年03月02日 14:14 crush_lee Exp $
 */
public class AsyncUtil {
    public static final String PRINT_STR="in[{}],current[{}]";
    public static String currentThreadName(){
        return Thread.currentThread().getName();
    }

}