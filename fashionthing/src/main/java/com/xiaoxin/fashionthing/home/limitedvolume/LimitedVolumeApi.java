package com.xiaoxin.fashionthing.home.limitedvolume;

import com.xiaoxin.fashionthing.utils.HttpsResult;
import io.reactivex.Flowable;
import java.util.List;
import retrofit2.http.GET;

public interface LimitedVolumeApi {
  @GET("timecoupon?platform=lapinapp_android&imagetype=1&platform=lapinapp_android&channel=kushichang")
  Flowable<HttpsResult<List<LimitedVolume>>> getLimitedVolumeList();
}