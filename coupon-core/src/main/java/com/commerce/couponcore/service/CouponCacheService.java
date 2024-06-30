package com.commerce.couponcore.service;

import com.commerce.couponcore.model.Coupon;
import com.commerce.couponcore.repository.redis.dto.CouponRedisEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponCacheService {

    private final CouponIssueService couponIssueService;

    @Cacheable(cacheNames = "coupon", key = "#p0")
    public CouponRedisEntity getCouponCache(long couponId) {
        Coupon coupon = couponIssueService.findCoupon(couponId);
        return new CouponRedisEntity(coupon);
    }

    @CachePut(cacheNames = "coupon", key = "#p0")
    public CouponRedisEntity putCouponCache(long couponId) {
        return getCouponCache(couponId);
    }
}
