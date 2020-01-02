/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.spring.test.kafka;

import org.springframework.stereotype.Service;

/**
 * Description TODO
 * @author Apple
 * @version $Id: KafkaListener.java, v 0.1 
 * @create 2019年12月31日 17:15 crush_lee Exp $
 */
@Service
public class KafkaListener {
    @org.springframework.kafka.annotation.KafkaListener(topics = "test-kafka")
    public void listen(String topic){

    }
}