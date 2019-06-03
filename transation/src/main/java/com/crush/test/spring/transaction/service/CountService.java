package com.crush.test.spring.transaction.service;

import com.crush.test.spring.transaction.domain.Account;
import com.crush.test.spring.transaction.mapper.CountMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Title: TODO
 * </p>
 * <p>
 * Description: TODO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: 客如云
 * </p>
 *
 * @author crush_lee
 * @date 2019/5/30
 */
@Service
public class CountService {
    @Autowired
    private CountMapper countMapper;
    public Account insert(){
        Account account=new Account();
        account.setAmount(0L);
        countMapper.insert(account);
        return account;
    }
    public void plus(Account account){
        countMapper.plus(account);
    }
    public Account get(Account account){
        return countMapper.selectByPrimaryKey(account);
    }
    public String isolationLevel(){
        return countMapper.isolationLevel();
    }

}
