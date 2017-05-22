package com.xiaoxin.fashionthing.utils.http;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class HttpMethods {
  //public static final String BASE_URL = "https://api.douban.com/v2/movie/";
  public static final String BASE_URL = "http://api.lapin365.com/callback/";
  private static final int DEFAULT_TIMEOUT = 5;

  private Retrofit retrofit;

  //在访问HttpMethods时创建单例
  private static class SingletonHolder {
    private static final HttpMethods INSTANCE = new HttpMethods();
  }

  //获取单例
  public static HttpMethods getInstance() {
    return SingletonHolder.INSTANCE;
  }

  //构造方法私有
  private HttpMethods() {
    //手动创建一个OkHttpClient并设置超时时间
    OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

    retrofit = new Retrofit.Builder().client(httpClientBuilder.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build();
    //movieService = retrofit.create(MovieService.class);
  }

  public Object setServise(Class mClass) {
    return retrofit.create(mClass);
  }

}
