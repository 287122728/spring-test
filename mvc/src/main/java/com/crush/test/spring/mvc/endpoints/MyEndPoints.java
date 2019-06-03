package com.crush.test.spring.mvc.endpoints;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

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
 * @date 2019/5/31
 */
@Component
public class MyEndPoints implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().withDetail("test","111").build();
    }
}
