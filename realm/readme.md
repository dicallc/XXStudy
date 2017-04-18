<h3>android不在坑系列一：数据库框架的选择</h3>
<h3>前言：</h3>


> 之前用过的litepal数据库框架，郭大婶的维护的，还记得以前拿着帮同学做过几个毕业设计呢，没办法团支书当的苦逼了，同学一餐饭，喝顿酒，我心一软就帮着做毕设。觉得挺好用的，好吧回到正题

### 第一坑：效率问题  

----------

去年公司准备做IM，ok，我负责封装数据库，当时就叫人去学习学习litepal，后面遇到了无数的坑，那个时候开发周期短，没调研，直接开干了，后面被测试发现效率十分低下....后面经过调研也发现....当然1.4版本发布后痛点不痛了。


### 第二坑：多对一的建立及使用坑

----------


多对一，一对一，多对多，这么专业的名字，前端肯定不知道吧，事实上也没啥的，但是litepal建立方式也太隐晦了吧....

##### 1.多对一 ： ####

文章有很多评论 **文章**即是一，**评论**即是多

##### 2.一对一 ####

评论有用户把  **评论**即是一，**用户**也是一

#####3.多对多

热点新闻有很多深圳本地新闻，深圳本地新闻也有热点新闻，双方都在对方表里有多条，即**热点新闻**是多，**深圳本地新闻**也是多



### 第三坑：框架不成熟 ####

----------

多对一的一里面如有修改一里面的一对一属性会被清空，或者我都不知道什么时候save，什么时候update，后面验证了无数的问题，经过很委婉的解决，或者自己写数据库语句终于跳出坑了


### 第四坑：数据库在测试中，十分不好调试 ###

----------


有时候出现bug了，要经过一系列的动作，才能把数据库放到电脑中查看，后面使用了Facebook的**stetho**，简直好评，可以动态看到数据的变化

推荐文章[借助Stetho在Chrome上调试Android网络&数据库](http://www.jianshu.com/p/03da9f91f41f)


## 脱坑之旅：Relam ##

以上litepal的坑呢，在这里都不会发生，其实我本来想用greendao的，看了下文档被吓住了，看了下Relam，被吸引了，有**中文文档**，bat都在用他，没错，**BAT**！！！开始更换框架

也还好我们数据库的使用，再封装了一层，不然现在还在四处找使用数据库的地方，不好维护啊

当然我还是仔细的调研了下，在litepal上发生的事(坑),会不会再次发生。

简单说一下使用吧。

表结构：

----------

经纬度： Point.class

      public String la;
      public String lo;


订单类：Order.class

      public String name;
      public String money;
    
      public Point shoplocation;
      public Point masslocation;

Order和Point是一对一关系

ShopUser.class

      public String name;
      public String paasword;
      public RealmList<Order> mOrders;

ShopUser和Order是一对多的关系


#### 零：配置Relam ###

	  project文件下build.gradle 添加:     
	  classpath "io.realm:realm-gradle-plugin:3.1.1"

	  app文件下的build.gradle 添加：
	  apply plugin: 'realm-android'

      App类中初始化： Realm.init(this);
      获取Relam单例：  realm = Realm.getDefaultInstance();
 

#### 一：存储 ###

       realm.executeTransaction(new Realm.Transaction() {
	      @Override public void execute(Realm realm) {
	    // Add a person
		    Order mOrder = realm.createObject(Order.class);
		    mOrder.name = "第一单";
		    mOrder.money = "13";
	      }
	    });


#### 二：修改 ###

		   mOrder = realm.where(Order.class).findFirst();
	        realm.executeTransaction(new Realm.Transaction() {
	          @Override public void execute(Realm realm) {
	            mOrder.name = "修改以后的订单";
	            mOrder.money = "15";
	          }
        });

#### 三：多对一关系添加 ###

**先说一下，如果变动了数据库表结构，得清空数据，不然一运行就会报错**

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


#### 四：多对一修改一 ###

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

#### 五：删除 ###

 	    	realm.delete(Point.class);
		    realm.delete(Order.class);
		    realm.delete(ShopUser.class);


#### 六：查询 ###

      mOrder = realm.where(Order.class).findFirst();


最后Relam搭配stetho使用的使用这种姿势：

app文件下的build.gradle添加

      compile 'com.facebook.stetho:stetho:1.4.2'
      compile 'com.uphyca:stetho_realm:2.0.0'

同时App类中：

      Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                .build());

#### 最后该注意的： ###

1.RealmObject自带线程保护功能，只能在创建它的线程中访问，在子线程中不能访问。
2.直接修改或删除query得到的数据，必须在transaction中完成...
3.如果Realm关闭，所有查询得到的RealmObject都不能使用了。

### 结语：完美脱坑 期待你发现更多的坑 ###

[Relam官网](https://realm.io/cn/docs/java/latest/)

[代码地址](https://github.com/dicallc/XXStudy/tree/master/realm)