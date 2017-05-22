package com.xiaoxin.fashionthing.home;

import com.xiaoxin.fashionthing.home.editbetter.EditBetter;
import com.xiaoxin.fashionthing.home.hotlist.HotThing;
import com.xiaoxin.fashionthing.home.limitedvolume.LimitedVolume;
import com.xiaoxin.fashionthing.utils.HttpsResult;
import io.reactivex.Flowable;
import java.util.List;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public interface HomeApi {
  @GET("taglabang?ver=2&platform=lapinapp_android&imagetype=1&platform=lapinapp_android&channel=kushichang&count=20")
  Flowable<HttpsResult<List<HotThing>>> getHotList();

  @GET("timecoupon?platform=lapinapp_android&imagetype=1&platform=lapinapp_android&channel=kushichang")
  Flowable<HttpsResult<List<LimitedVolume>>> getLimitedVolumeList();

  @GET("indexlistsegbyid?count=20&platform=lapinapp_android&channel=yingyongbao&imagetype=1")
  Flowable<HttpsResult<List<EditBetter>>> getEditBetterList();
}
