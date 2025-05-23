package com.mayank.network_cache.cache.cache_storage;

public interface ICacheStorage<K, V> {
  void put(K key, V value);

  V get(K key);

  void remove(K key);

  void clear();

  int size();
}
