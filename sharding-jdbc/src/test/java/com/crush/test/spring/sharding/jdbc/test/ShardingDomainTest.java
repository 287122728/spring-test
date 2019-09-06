/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.sharding.jdbc.test;

import com.alibaba.fastjson.JSON;
import com.crush.test.spring.sharding.jdbc.domain.ShardingDomain;
import com.crush.test.spring.sharding.jdbc.service.ShardingDomainService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import io.shardingsphere.spi.NewInstanceServiceLoader;
import io.shardingsphere.spi.parsing.ParsingHook;

/**
 * @author crush_lee
 * @version $Id: ShardingDomainTest.java, v 0.1 2019年08月07日 17:13 crush_lee Exp $
 */
public class ShardingDomainTest extends BaseTest {
    @Autowired
    private ShardingDomainService shardingDomainService;
    @Before
    public void init(){
        NewInstanceServiceLoader.newServiceInstances(ParsingHook.class);
    }

    @Test
    public void insert() {
        //1、无规则映射，会每个库都进行插入
        for (int i = 0; i < 10; i++) {
            ShardingDomain shardingDomain = new ShardingDomain();
            shardingDomain.setBizId(i);
            shardingDomain.setGroupId(i + 1);
            shardingDomain.setContent("" + System.currentTimeMillis());
            shardingDomainService.insert(shardingDomain);
        }
    }

    @Test
    public void query() {
        //1、无条件查询场景，会每个库都进行查询，然后归并处理
        ShardingDomain shardingDomain = new ShardingDomain();
        //shardingDomain.setBizId(1);
        //shardingDomain.setGroupId(2);
        List<ShardingDomain> shardingDomains = shardingDomainService.query(shardingDomain);
        print(shardingDomains);
    }
    @Test
    public void count(){
        ShardingDomain shardingDomain = new ShardingDomain();
        //1、无条件查询场景，会每个库都进行查询，然后归并处理
        //shardingDomain.setBizId(1);
        //shardingDomain.setGroupId(2);
        int shardingDomains = shardingDomainService.count(shardingDomain);
        System.out.println(JSON.toJSON(shardingDomains));
    }
    @Test
    public void queryPage(){
        ShardingDomain shardingDomain = new ShardingDomain();
        //1、无条件查询场景，会每个库都进行查询，然后归并处理
        //shardingDomain.setBizId(1);
        shardingDomain.setGroupId(2);
        List<ShardingDomain> shardingDomains = shardingDomainService.queryByPage(shardingDomain,0,2);
        print(shardingDomains);
    }
    @Test
    public void queryWithOrderBy(){
        ShardingDomain shardingDomain = new ShardingDomain();
        //1、无条件查询场景，会每个库都进行查询，然后归并order by
        //shardingDomain.setBizId(1);
        //shardingDomain.setGroupId(2);
        List<ShardingDomain> shardingDomains = shardingDomainService.queryWithOrderBy(shardingDomain);
        print(shardingDomains);
    }
    @Test
    public void queryWithGroupBy(){
        ShardingDomain shardingDomain = new ShardingDomain();
        //1、无条件查询场景，会每个库都进行查询，然后归并group by
        //shardingDomain.setBizId(1);
        //shardingDomain.setGroupId(2);
        List<ShardingDomain> shardingDomains = shardingDomainService.queryWithGroupBy(shardingDomain);
        print(shardingDomains);
    }
    @Test
    public void queryWithIn(){
        //1、根据规则到多个库中去查询数据
        List<ShardingDomain> list=new ArrayList<>();
        ShardingDomain shardingDomain = new ShardingDomain();
        //shardingDomain.setBizId(1);
        shardingDomain.setGroupId(3);

        ShardingDomain s1 = new ShardingDomain();
        s1.setGroupId(1);
        list.add(shardingDomain);
        list.add(s1);

        List<ShardingDomain> shardingDomains = shardingDomainService.queryWithIn(list);
        print(shardingDomains);
    }
    private void print(List<ShardingDomain> shardingDomains){
        System.out.println(shardingDomains.size()+"--"+JSON.toJSON(shardingDomains));
    }
}