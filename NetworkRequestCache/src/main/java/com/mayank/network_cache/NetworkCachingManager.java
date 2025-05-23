package com.mayank.network_cache;

import static java.util.Objects.isNull;

import com.mayank.network_cache.cache.Cache;
import com.mayank.network_cache.cache.cache_storage.HashMapCacheStorage;
import com.mayank.network_cache.cache.eviction_policy.LRUEvictionPolicy;
import com.mayank.network_cache.resource.CachedResource;
import com.mayank.network_cache.resource.Resource;
import com.mayank.network_cache.resource.ResourceMetadata;
import java.time.Instant;

public class NetworkCachingManager {
  Cache<String, CachedResource> networkCache;
  int MAX_SIZE = 5;
  private static final NetworkCachingManager INSTANCE = new NetworkCachingManager();

  private NetworkCachingManager() {};

  public NetworkCachingManager getInstance() {
    return INSTANCE;
  }

  public void initiate(int size) {
    networkCache =
        new Cache<>(
            new HashMapCacheStorage<String, CachedResource>(MAX_SIZE),
            new LRUEvictionPolicy<String>(MAX_SIZE),
            new TimeBasedValidator<CachedResource>(),
            MAX_SIZE);
  }

  public Resource fetchResource(String url) {
    CachedResource cachedR = networkCache.get(url);
    if (isNull(cachedR)) {
      return downloadAndCache(url);
    }
    return new Resource(cachedR.getData(), url);
  }

  public Resource downloadAndCache(String url) {
    // replace this line with download part
    Resource downloadedResource = new Resource(new byte[10], url);

    // Create metadata (e.g., expire after 1 hour)
    ResourceMetadata metadata =
        new ResourceMetadata(
            url, Instant.now().toEpochMilli() + 3600000 // 1 hour expiration
            );

    // Create and cache the resource
    CachedResource cachedResource = new CachedResource(downloadedResource.getData(), metadata);

    // Store in cache
    networkCache.put(url, cachedResource);

    return downloadedResource;
  }
}
