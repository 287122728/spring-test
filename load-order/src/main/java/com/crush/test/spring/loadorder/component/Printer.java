/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.crush.test.spring.loadorder.component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description TODO
 * @author Apple
 * @version $Id: Printer.java, v 0.1 
 * @create 2020年02月27日 14:14 crush_lee Exp $
 */
public class Printer {
    static final List<String> list=new ArrayList<>();
    public synchronized static void add(String s){
        list.add(s);
        System.out.println(String.format("add[%s]",s));
    }
    public static void print(){
        for (String e:list){
            System.out.println(e);
        }
    }
}