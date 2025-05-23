package com.mayank.network_cache;

import com.mayank.network_cache.cache.cache_validator.ICachedValueValidator;
import com.mayank.network_cache.resource.CachedResource;

public class TimeBasedValidator<V> implements ICachedValueValidator<CachedResource> {
  @Override
  public boolean validate(CachedResource resource) {
    return resource != null && !resource.getMetadata().isExpired();
  }
}
