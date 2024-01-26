package com.assessment.LibraryManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.stats.CacheStats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@EnableScheduling
public class CacheMonitoringService {

	private static final Logger log = LoggerFactory.getLogger(CacheMonitoringService.class);

	@Autowired
	private CacheManager cacheManager;

	@Scheduled(fixedRate = 10000) // every 10 seconds
	public void logCacheStatistics() {
		CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache("booksCache");
		if (caffeineCache != null) {
			com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
			CacheStats stats = nativeCache.stats();
			log.info("Cache Stats - Hits: {}, Misses: {}, Evictions: {}", stats.hitCount(), stats.missCount(),
					stats.evictionCount());
		}
	}
}
