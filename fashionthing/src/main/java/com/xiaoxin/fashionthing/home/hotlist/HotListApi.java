package com.xiaoxin.fashionthing.home.hotlist;

import com.xiaoxin.fashionthing.utils.HttpsResult;
import io.reactivex.Flowable;
import java.util.List;
import retrofit2.http.GET;

public interface HotListApi {
  @GET("taglabang?ver=2&platform=lapinapp_android&imagetype=1&platform=lapinapp_android&channel=kushichang&count=20")
  Flowable<HttpsResult<List<HotThing>>> getHotList();
}