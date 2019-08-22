/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.sharding.jdbc.service;

import com.crush.test.spring.sharding.jdbc.domain.ShardingDomain;
import com.crush.test.spring.sharding.jdbc.mapper.ShardingDomainMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author crush_lee
 * @version $Id: ShardingDomainService.java, v 0.1 2019年08月07日 17:09 crush_lee Exp $
 */
@Service
public class ShardingDomainService {
    @Autowired
    private ShardingDomainMapper shardingDomainMapper;
    public void insert(ShardingDomain shardingDomain){
        shardingDomainMapper.insert(shardingDomain);
    }
    public List<ShardingDomain> query(ShardingDomain shardingDomain){
        return shardingDomainMapper.select(shardingDomain);
    }
}