/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.sharding.jdbc.mapper;

import com.crush.test.spring.sharding.jdbc.BaseMapper;
import com.crush.test.spring.sharding.jdbc.domain.ShardingDomain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author crush_lee
 * @version $Id: ShardingDomainMapper.java, v 0.1 2019年08月07日 17:05 crush_lee Exp $
 */
@Mapper
public interface ShardingDomainMapper extends BaseMapper<ShardingDomain> {
    List<ShardingDomain> queryWithOrderBy(ShardingDomain shardingDomain);
    List<ShardingDomain> queryWithGroupBy(ShardingDomain shardingDomain);
    List<ShardingDomain> queryWithIn(@Param("shardingDomain")List<ShardingDomain> shardingDomain);
}