/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.sharding.jdbc.service;

import com.crush.test.spring.sharding.jdbc.domain.ShardingDomain;
import com.crush.test.spring.sharding.jdbc.mapper.ShardingDomainMapper;

import org.apache.ibatis.session.RowBounds;
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
    public int count(ShardingDomain shardingDomain){
        return shardingDomainMapper.selectCount(shardingDomain);
    }
    public List<ShardingDomain> queryByPage(ShardingDomain shardingDomain,int start,int limit){
        RowBounds rowBounds=new RowBounds(start,limit);
        return shardingDomainMapper.selectByRowBounds(shardingDomain,rowBounds);
    }
    public List<ShardingDomain> queryWithOrderBy(ShardingDomain shardingDomain){
        return shardingDomainMapper.queryWithOrderBy(shardingDomain);
    }
    public List<ShardingDomain> queryWithGroupBy(ShardingDomain shardingDomain){
        return shardingDomainMapper.queryWithGroupBy(shardingDomain);
    }
    public List<ShardingDomain> queryWithIn(List<ShardingDomain> shardingDomain){
        return shardingDomainMapper.queryWithIn(shardingDomain);
    }
}