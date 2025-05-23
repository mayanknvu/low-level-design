package com.mayank.network_cache.cache;

import com.mayank.network_cache.cache.cache_storage.ICacheStorage;
import com.mayank.network_cache.cache.cache_validator.ICachedValueValidator;
import com.mayank.network_cache.cache.eviction_policy.IEvictionPolicy;
import com.mayank.network_cache.cache.exceptions.KeyNotFoundException;
import com.mayank.network_cache.cache.exceptions.StorageFullException;

public class Cache<K, V> {
  ICacheStorage<K, V> cacheStorage;
  IEvictionPolicy<K> evictionPolicy;
  ICachedValueValidator<V> cachedValueValidator;
  int maxSize;

  public Cache(
      ICacheStorage cacheStorage,
      IEvictionPolicy evictionPolicy,
      ICachedValueValidator cachedValueValidator,
      int size) {
    this.cacheStorage = cacheStorage;
    this.evictionPolicy = evictionPolicy;
    this.cachedValueValidator = cachedValueValidator;
    maxSize = size;
  }

  public V get(K key) {
    try {
      V value = cacheStorage.get(key);
      if (cachedValueValidator.validate(value)) {
        evictionPolicy.recordAccess(key);
        return value;
      } else {
        evictionPolicy.remove(key);
        cacheStorage.remove(key);
      }
    } catch (KeyNotFoundException e) {
      // add logger
    }
    return null;
  }

  public void put(K key, V value) {
    try {
      cacheStorage.put(key, value);
      evictionPolicy.recordAccess(key);
    } catch (StorageFullException e) {
      // add loggers. evicting cache to add new
      K evictedKey = evictionPolicy.evict();
      cacheStorage.remove(evictedKey);
      this.put(key, value);
    }
  }
}
