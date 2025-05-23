package com.mayank.network_cache.resource;

import lombok.Data;

@Data
public class CachedResource {
  private final byte[] data;
  private final ResourceMetadata metadata;

  public CachedResource(byte[] data, ResourceMetadata metadata) {
    this.data = data;
    this.metadata = metadata;
  }
}
