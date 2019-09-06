/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.sharding.jdbc.spi;

import io.shardingsphere.spi.parsing.ParsingHook;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author crush_lee
 * @version $Id: ParsingSPITest.java, v 0.1 2019年08月28日 13:45 crush_lee Exp $
 */
@Slf4j
public class ParsingSPITest implements ParsingHook {
    @Override
    public void start(String sql) {
        log.info("sql:{}",sql);
    }

    @Override
    public void finishSuccess() {

    }

    @Override
    public void finishFailure(Exception cause) {
        log.error("error:{}",cause.getCause());
    }
}