package com.mayank.network_cache.cache.cache_validator;

public interface ICachedValueValidator<V> {
  boolean validate(V value);
}
