package com.crush.test.spring.transaction.mapper;

import com.crush.test.spring.transaction.BaseMapper;
import com.crush.test.spring.transaction.domain.Account;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
@Mapper
public interface CountMapper extends BaseMapper<Account> {
    @Update("update transaction_account set amount=amount+1 where id=#{id}")
    void plus(Account account);
    @Select("SELECT @@tx_isolation")
    String isolationLevel();
}
