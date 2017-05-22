# rxjava2+retrofit2+阿里的vlayout实现电商的首页

> 走着前辈们的路，简单的封装了下，当然更多的是去用RXjava2的实现网络请求



- 1.已经将Progress加载框的封装在onstart方法中，自动加载，自动消失
- 2.使用map操作符操作Api(接口数据)，直接返回用户所想要的数据


### 一、写这个demo的原因？ ###

#### ①对于rxjava的使用及学习，以及看看引入这么多库，看看到底能到达多少方法数 ####

而且对于学过js的人，应该很熟悉rxjava，因为js中有rxjs！，简简单单的看了下文档，使用了几个方法和操作符，证明确实是一样的，看来rxjava的发明者应该想把链式思想推广到很多语言中呐
最后看了下方法数
随随意意超越20000+，当然v7包就18000罗，紧紧是为了实现一个联网，我觉得这样做实在很**愚蠢**，当然看清楚为了实现联网，不排除其他用法。


#### ②阿里的开源的vlayout有多牛逼? ####
预览图如下:

<Img src="https://raw.githubusercontent.com/dicallc/XXStudy/master/file/GIF.gif"></Img>

[下载apk体验](https://github.com/dicallc/XXStudy/blob/master/file/fashionthing-debug.apk)

亲测无卡顿...对于在电商项目中，最怕就是嵌套+嵌套，幸好这次阿里开源不是KPI项目

项目中使用到vLayout的：

- 通栏布局SingleLayoutHelper
- Grid布局GridLayoutHelper
- 线性布局LinearLayoutHelper

### 2.可使用 mergeDelayError合并多个请求 ###

[参考merge操作符](http://www.jianshu.com/p/9496bf8851d3)

       HomeApi mHomeApi =
	    (HomeApi) HttpMethods.getInstance().setServise(HomeApi.class);
	    Flowable.mergeDelayError(mHomeApi.getEditBetterList(),mHomeApi.getHotList())
		    .subscribeOn(Schedulers.io())
		    .unsubscribeOn(Schedulers.io())
		    .observeOn(AndroidSchedulers.mainThread())
		    .subscribe(new ResourceSubscriber<HttpsResult<? extends List<? extends Object>>>() {
			      @Override public void onNext(HttpsResult<? extends List<? extends Object>> mHttpsResult) {
			    
			      }
			    
			      @Override public void onError(Throwable t) {
			    
			      }
			    
			      @Override public void onComplete() {
			    
			      }
    });

### 3.接口来自辣品，如有侵权，请联系我 ###

### 4.github地址 ###
[源码地址](https://github.com/dicallc/XXStudy/tree/master/fashionthing)

最后如果代码能帮助到你，移动鼠标轻轻的点一下star呗~