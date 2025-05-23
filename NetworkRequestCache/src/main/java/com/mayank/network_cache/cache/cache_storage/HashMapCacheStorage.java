package com.mayank.network_cache.cache.cache_storage;

import com.mayank.network_cache.cache.exceptions.KeyNotFoundException;
import com.mayank.network_cache.cache.exceptions.StorageFullException;
import java.util.HashMap;
import java.util.Map;

public class HashMapCacheStorage<K, V> implements ICacheStorage<K, V> {

  Map<K, V> map;
  int capacity;

  public HashMapCacheStorage(int capacity) {
    map = new HashMap<>();
    this.capacity = capacity;
  }

  @Override
  public void put(K key, V value) throws StorageFullException {
    if (storageFull()) {
      throw new StorageFullException("Cache storage capacity reached!");
    }
    map.put(key, value);
  }

  @Override
  public V get(K key) throws KeyNotFoundException {
    if (map.containsKey(key)) {
      return map.get(key);
    } else {
      throw new KeyNotFoundException("Key doesn't exist in cache!");
    }
  }

  @Override
  public void remove(K key) {
    if (map.containsKey(key)) {
      map.remove(key);
    } else {
      throw new KeyNotFoundException("Key doesn't exist in cache!");
    }
  }

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public int size() {
    return map.size();
  }

  private boolean storageFull() {
    return map.size() == capacity;
  }
}
