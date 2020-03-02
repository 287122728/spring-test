/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.spring.test.kafka;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Description TODO
 * @author Apple
 * @version $Id: KafkaListener.java, v 0.1 
 * @create 2019年12月31日 17:15 crush_lee Exp $
 */
@Service
@Slf4j
public class MyKafkaListener {
    @org.springframework.kafka.annotation.KafkaListener(topics = "test-kafka")
    public void listen(String topic, Acknowledgment ack){
        log.info("receive msg:["+topic+"]");
        if(topic.contains("test")){
            ack.acknowledge();
        }else{
            throw new RuntimeException("test exception");
        }
    }
}