package com.xiaoxin.realm.model;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class Point extends RealmObject {
  public String la;
  public String lo;

  @Override public String toString() {
    return "Point{" + "la='" + la + '\'' + ", lo='" + lo + '\'' + '}';
  }
}
