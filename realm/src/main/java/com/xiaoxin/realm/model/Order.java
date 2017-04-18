package com.xiaoxin.realm.model;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class Order extends RealmObject {
  public String name;
  public String money;

  public Point shoplocation;
  public Point masslocation;

  @Override public String toString() {
    return "Order{"
        + "name='"
        + name
        + '\''
        + ", money='"
        + money
        + '\''
        + ", shoplocation="
        + shoplocation
        + '}';
  }
}
