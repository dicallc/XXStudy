package com.xiaoxin.fashionthing.home.limitedvolume;

import com.xiaoxin.fashionthing.utils.http.HttpMethods;
import com.xiaoxin.fashionthing.utils.http.HttpResultFunc;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class LimitedVolumeModel {
  public static void getLimitedVolumeList(ResourceSubscriber<List<LimitedVolume>> subscriber) {
    LimitedVolumeApi mHotListApi =
        (LimitedVolumeApi) HttpMethods.getInstance().setServise(LimitedVolumeApi.class);
    mHotListApi.getLimitedVolumeList()
        .map(new HttpResultFunc<List<LimitedVolume>>())
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber);
  }
}
