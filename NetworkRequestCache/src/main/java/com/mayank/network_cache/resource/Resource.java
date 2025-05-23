package com.mayank.network_cache.resource;

public class Resource {
  private final byte[] data;
  private final String url;

  public Resource(byte[] data, String url) {
    this.data = data;
    this.url = url;
  }

  public byte[] getData() {
    return data;
  }

  public String getUrl() {
    return url;
  }
}
