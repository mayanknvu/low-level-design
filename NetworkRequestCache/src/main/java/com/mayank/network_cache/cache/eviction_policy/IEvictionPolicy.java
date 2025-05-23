package com.mayank.network_cache.cache.eviction_policy;

public interface IEvictionPolicy<K> {
  void recordAccess(K key);

  K evict();

  void remove(K key);
}
