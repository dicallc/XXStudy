package com.xiaoxin.realm.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class ShopUser extends RealmObject {
  public String name;
  public String paasword;
  public RealmList<Order> mOrders;
}
