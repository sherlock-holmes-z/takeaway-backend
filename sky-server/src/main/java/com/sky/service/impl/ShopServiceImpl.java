package com.sky.service.impl;

import com.sky.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * ShopServiceImpl
 *
 * @author Chocolate
 * @since 2024/6/30 17:11
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private static final String SHOP_STATUS = "SHOP_STATUS";

    @Override
    public void updateStatus(Integer status) {
        redisTemplate.opsForValue().set(SHOP_STATUS, status);
    }

    public Integer getStatus() {
        return (Integer) redisTemplate.opsForValue().get(SHOP_STATUS);
    }
}
