package com.mayank.network_cache.cache.exceptions;

public class StorageFullException extends RuntimeException {
  public StorageFullException(String message) {
    super(message);
  }
}
