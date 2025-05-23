package com.mayank.network_cache.resource;

import java.time.Instant;
import lombok.Data;

@Data
public class ResourceMetadata {
  private final String url;
  private final long downloadTimestamp;
  private final long expirationTime;

  public ResourceMetadata(String url, long expirationTime) {
    this.url = url;
    this.downloadTimestamp = Instant.now().toEpochMilli();
    this.expirationTime = expirationTime;
  }

  public boolean isExpired() {
    return Instant.now().toEpochMilli() > expirationTime;
  }

  public String getUrl() {
    return url;
  }
}
