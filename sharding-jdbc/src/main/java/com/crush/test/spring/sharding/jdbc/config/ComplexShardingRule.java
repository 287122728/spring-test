/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.sharding.jdbc.config;

import com.alibaba.druid.support.json.JSONUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import io.shardingsphere.api.algorithm.sharding.ListShardingValue;
import io.shardingsphere.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author crush_lee
 * @version $Id: ComplexShardingRule.java, v 0.1 2019年08月23日 14:51 crush_lee Exp $
 */
@Slf4j
public class ComplexShardingRule implements ComplexKeysShardingAlgorithm {
    public ComplexShardingRule(){}
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        log.info("availableTargetNames:[{}],shardingValues:[{}]", JSONUtils.toJSONString(availableTargetNames),JSONUtils.toJSONString(shardingValues));
        Collection<Integer> bizIds=getShardingValue(shardingValues,"biz_id");
        Collection<Integer> groupIds=getShardingValue(shardingValues,"group_id");
        Collection<String> list=new ArrayList<>();
        for (Integer bizId : bizIds) {
            list.add("ds"+bizId%2);
        }
        for (Integer groupId : groupIds) {
            list.add("ds"+groupId);
        }
        return list;
    }
    private Collection<Integer> getShardingValue(Collection<ShardingValue> shardingValues, final String key) {
        Collection<Integer> valueSet = new ArrayList<>();
        Iterator<ShardingValue> iterator = shardingValues.iterator();
        while (iterator.hasNext()) {
            ShardingValue next = iterator.next();
            if (next instanceof ListShardingValue) {
                ListShardingValue value = (ListShardingValue) next;
                /**例如：根据user_id + order_id 双分片键来进行分表*/
                if (value.getColumnName().equals(key)) {
                    return value.getValues();
                }
            }
        }
        return valueSet;
    }
}