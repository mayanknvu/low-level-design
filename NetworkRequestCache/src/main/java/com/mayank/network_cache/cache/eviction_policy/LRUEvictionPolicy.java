package com.mayank.network_cache.cache.eviction_policy;

import com.mayank.network_cache.cache.exceptions.CacheStateException;
import com.mayank.network_cache.cache.exceptions.KeyNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements IEvictionPolicy<K> {

  Map<K, Boolean> eMap;
  int capacity;

  public LRUEvictionPolicy(int capacity) {
    this.capacity = capacity;
    eMap =
        new LinkedHashMap<>(capacity, 0.75f, true) {
          @Override
          protected boolean removeEldestEntry(Map.Entry<K, Boolean> eldest) {
            return size() > capacity;
          }
        };
  }

  @Override
  public void recordAccess(K key) {
    eMap.put(key, Boolean.TRUE);
  }

  @Override
  public K evict() throws CacheStateException {
    if (eMap.isEmpty()) {
      throw new CacheStateException("Cache is Empty!");
    }
    K eldest = eMap.keySet().iterator().next();
    eMap.remove(eldest);
    return eldest;
  }

  @Override
  public void remove(K key) {
    if (eMap.containsKey(key)) {
      eMap.remove(key);
    } else {
      throw new KeyNotFoundException("Key doesn't exists in cache!");
    }
  }
}
