package com.xiaoxin.fashionthing.home.editbetter;

import com.xiaoxin.fashionthing.utils.HttpsResult;
import io.reactivex.Flowable;
import java.util.List;
import retrofit2.http.GET;

public interface EditBetterApi {
  @GET("indexlistsegbyid?count=20&platform=lapinapp_android&channel=yingyongbao&imagetype=1")
  Flowable<HttpsResult<List<EditBetter>>> getEditBetterList();
}