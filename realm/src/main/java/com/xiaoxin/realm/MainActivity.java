package com.xiaoxin.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.xiaoxin.realm.model.Order;
import com.xiaoxin.realm.model.Point;
import com.xiaoxin.realm.model.ShopUser;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.button) Button mButton;
  @BindView(R.id.button2) Button mButton2;
  @BindView(R.id.button3) Button mButton3;
  @BindView(R.id.button5) Button mButton5;
  @BindView(R.id.query) Button mQuery;
  private Realm realm;
  private Order mOrder;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    realm = Realm.getDefaultInstance();
  }

  @OnClick({ R.id.button, R.id.button2, R.id.button3, R.id.button5, R.id.query ,R.id.clear})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.button:
        realm.executeTransaction(new Realm.Transaction() {
          @Override public void execute(Realm realm) {
            // Add a person
            Order mOrder = realm.createObject(Order.class);
            mOrder.name = "第一单";
            mOrder.money = "13";
          }
        });
        break;
      case R.id.button2:
        mOrder = realm.where(Order.class).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
          @Override public void execute(Realm realm) {
            mOrder.name = "修改以后的订单";
            mOrder.money = "15";
          }
        });

        break;
      case R.id.button3:

        realm.executeTransaction(new Realm.Transaction() {

          @Override public void execute(Realm realm) {
            ShopUser mShopUser = realm.createObject(ShopUser.class);
            mShopUser.name = "tom";
            mShopUser.paasword = "123";
            for (int i = 0; i < 2; i++) {
              Order mOrder = realm.createObject(Order.class);
              Point mPoint = realm.createObject(Point.class);
              mPoint.la="132";
              mPoint.lo="122";
              Point masspoint = realm.createObject(Point.class);
              masspoint.la="132";
              masspoint.lo="122";
              mOrder.shoplocation=mPoint;
              mOrder.masslocation=masspoint;
              mOrder.name = "订单" + i;
              mOrder.money = i + "元";
              mShopUser.mOrders.add(mOrder);
            }
          }
        });
        break;
      case R.id.button5:
        realm.executeTransactionAsync(new Realm.Transaction() {

          @Override public void execute(Realm realm) {
            RealmResults<ShopUser> results = realm.where(ShopUser.class).equalTo("mOrders.name", "订单0").findAll();
            if (0<results.size()){
              ShopUser mShopUser = results.get(0);
              Order mOrder = mShopUser.mOrders.get(0);
              mOrder.name="修改以后的订单";
              Point mShoplocation = mOrder.shoplocation;
              mShoplocation.la="修改以后的经度";
              mShoplocation.lo="修改以后的维度";
            }else{
              Toast.makeText(MainActivity.this,"查询失败", Toast.LENGTH_SHORT).show();

            }
          }
        });


        break;
      case R.id.clear:
        realm.delete(Point.class);
        realm.delete(Order.class);
        realm.delete(ShopUser.class);
        break;
      case R.id.query:
        mOrder = realm.where(Order.class).findFirst();
        Toast.makeText(MainActivity.this, mOrder.toString(), Toast.LENGTH_SHORT).show();
        break;
    }
  }
}
