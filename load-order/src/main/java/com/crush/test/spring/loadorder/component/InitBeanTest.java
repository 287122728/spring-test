/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.crush.test.spring.loadorder.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Description TODO
 * @author Apple
 * @version $Id: InitBeanTest.java, v 0.1 
 * @create 2020年02月27日 14:22 crush_lee Exp $
 */
@Service
public class InitBeanTest implements InitializingBean, ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
    @PostConstruct
    public void init(){
        Printer.add("init PostConstruct");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        Printer.add("init afterPropertiesSet");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Printer.add("init onApplicationEvent");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Printer.add("init setApplicationContext");
    }
}