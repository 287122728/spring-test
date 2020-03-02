/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.spring.test.kafka.test;

import com.crush.spring.test.kafka.MyKafaProducer;

import org.junit.Test;

import javax.annotation.Resource;

;

/**
 * Description TODO
 * @author Apple
 * @version $Id: KafkaProducerTest.java, v 0.1 
 * @create 2019年12月31日 17:17 crush_lee Exp $
 */

public class KafkaProducerTest extends BaseTest{
    @Resource
    private MyKafaProducer<String,String> kafkaProducer;
    @Test
    public void listenWithError() throws InterruptedException {
        kafkaProducer.send("test-kafka","test");
        //TimeUnit.SECONDS.sleep(1000L);
    }
}