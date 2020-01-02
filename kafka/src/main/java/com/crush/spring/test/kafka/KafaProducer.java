/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.spring.test.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * Description TODO
 * @author Apple
 * @version $Id: KafaProducer.java, v 0.1 
 * @create 2019年12月31日 14:51 crush_lee Exp $
 */
@Service
@Slf4j
public class KafaProducer<K,V> {
    @Resource
    public KafkaTemplate<String,String> kafkaTemplate;
    @PostConstruct
    public void init(){
        kafkaTemplate.setProducerListener(listener);
    }
    public void send(String topic,String msg){
        kafkaTemplate.send(topic,msg);
    }

    private ProducerListener listener = new ProducerListener<K, V>() {
        @Override
        public void onSuccess(String topic, Integer partition, K key, V value, RecordMetadata recordMetadata) {
            log.info("topic:{},key:{},msg:{} sent", topic, key, value);
        }

        @Override
        public void onError(String topic, Integer partition, K key, V value, Exception exception) {
            log.info("topic:{},key:{},msg:{} send fail", topic, key, value);
            log.error("",exception);
        }

        @Override
        public boolean isInterestedInSuccess() {
            //返回false 成功不会打印日志
            return true;
        }
    };
}