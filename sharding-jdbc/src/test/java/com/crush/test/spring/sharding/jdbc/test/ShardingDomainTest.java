/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.sharding.jdbc.test;

import com.crush.test.spring.sharding.jdbc.domain.ShardingDomain;
import com.crush.test.spring.sharding.jdbc.service.ShardingDomainService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author crush_lee
 * @version $Id: ShardingDomainTest.java, v 0.1 2019年08月07日 17:13 crush_lee Exp $
 */
public class ShardingDomainTest extends BaseTest {
    @Autowired
    private ShardingDomainService shardingDomainService;
    @Test
    public void insert(){
        for(int i=0;i<10;i++){
            ShardingDomain shardingDomain=new ShardingDomain();
            shardingDomain.setBizId(i);
            shardingDomain.setGroupId(i%2);
            shardingDomain.setContent(""+System.currentTimeMillis());
            shardingDomainService.insert(shardingDomain);
        }
    }
    @Test
    public void query(){
        ShardingDomain shardingDomain=new ShardingDomain();
        shardingDomain.setBizId(2);
        shardingDomain.setContent("123");
        shardingDomainService.query(shardingDomain);
    }
}