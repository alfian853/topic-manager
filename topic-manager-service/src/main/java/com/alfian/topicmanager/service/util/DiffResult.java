package com.alfian.topicmanager.service.util;

public class DiffResult<T> {

  private T item;

  /**
   * true = is firstlist item
   * false = is secondlist item
   * */
  private boolean isFirstListItem;

  public DiffResult() {
  }

  public DiffResult(T item, boolean isFirstListItem) {
    this.item = item;
    this.isFirstListItem = isFirstListItem;
  }

  public T getItem() {
    return item;
  }

  public void setItem(T item) {
    this.item = item;
  }

  public boolean isFirstListItem() {
    return isFirstListItem;
  }

  public void setFirstListItem(boolean firstListItem) {
    isFirstListItem = firstListItem;
  }
}
