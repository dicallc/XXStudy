package com.xiaoxin.fashionthing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.xiaoxin.fashionthing.home.adapter.EditBetterHeaderAdapter;
import com.xiaoxin.fashionthing.home.adapter.HeaderAdapter;
import com.xiaoxin.fashionthing.home.adapter.HotListAdapter;
import com.xiaoxin.fashionthing.home.adapter.LimitedVolumeAdapter;
import com.xiaoxin.fashionthing.home.adapter.TypeAdapter;
import com.xiaoxin.fashionthing.home.editbetter.EditBetter;
import com.xiaoxin.fashionthing.home.editbetter.EditBetterAdapter;
import com.xiaoxin.fashionthing.home.editbetter.EditBetterListModel;
import com.xiaoxin.fashionthing.home.hotlist.HotListModel;
import com.xiaoxin.fashionthing.home.hotlist.HotThing;
import com.xiaoxin.fashionthing.home.limitedvolume.LimitedVolume;
import com.xiaoxin.fashionthing.home.limitedvolume.LimitedVolumeModel;
import com.xiaoxin.fashionthing.utils.http.ProgressSubscriber;
import com.xiaoxin.fashionthing.utils.http.SubscriberOnNextListener;
import java.util.LinkedList;
import java.util.List;

/**
 * 首页
 * 代码风格：关键地方有注释，根据重构所述:以方法名说明意思
 */
public class MainActivity extends AppCompatActivity {

  @Bind(R.id.list) RecyclerView mList;
  private SubscriberOnNextListener<List<HotThing>> getTopMovieOnNext;
  private HotListAdapter mHotListAdapter;
  private LimitedVolumeAdapter mLimitedVolumeAdapter;
  private EditBetterHeaderAdapter mEditBetterHeaderAdapter;
  private EditBetterAdapter mEditBetterAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initView();
    initData();
  }

  private void initData() {
    getTopMovieOnNext = new SubscriberOnNextListener<List<HotThing>>() {
      @Override public void onNext(List<HotThing> mHotThings) {
        mHotListAdapter.setHotListData(mHotThings);
      }
    };
    SubscriberOnNextListener<List<LimitedVolume>> mSubscriberOnNextListener =
        new SubscriberOnNextListener<List<LimitedVolume>>() {
          @Override public void onNext(List<LimitedVolume> mHotThings) {
            mLimitedVolumeAdapter.setLimitedVolumeListData(mHotThings);
          }
        };
    SubscriberOnNextListener<List<EditBetter>> mListener =
        new SubscriberOnNextListener<List<EditBetter>>() {
          @Override public void onNext(List<EditBetter> mEditBetters) {
            mEditBetterAdapter.setList(mEditBetters);
          }
        };
    HotListModel.getHotList(
        new ProgressSubscriber<List<HotThing>>(getTopMovieOnNext, MainActivity.this));
    LimitedVolumeModel.getLimitedVolumeList(
        new ProgressSubscriber<List<LimitedVolume>>(mSubscriberOnNextListener, MainActivity.this));
    EditBetterListModel.getEditBetterList(
        new ProgressSubscriber<List<EditBetter>>(mListener, MainActivity.this));
  }

  private void initView() {
    //绑定VirtualLayoutManager
    VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
    mList.setLayoutManager(layoutManager);
    //设置Adapter列表
    List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
    setHeader(adapters);
    setTypes(adapters);
    setHotLists(adapters);
    setLimitedVolume(adapters);
    setEditBetterHeader(adapters);
    setEditBetterList(adapters);
    bind(layoutManager, adapters);
  }

  private void setEditBetterList(List<DelegateAdapter.Adapter> mAdapters) {
    LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
    linearLayoutHelper.setMarginBottom(10);
    mEditBetterAdapter = new EditBetterAdapter(this, linearLayoutHelper);
    mAdapters.add(mEditBetterAdapter);
  }

  private void setEditBetterHeader(List<DelegateAdapter.Adapter> mAdapters) {
    SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
    singleLayoutHelper.setMarginBottom(10);
    mEditBetterHeaderAdapter = new EditBetterHeaderAdapter(this, singleLayoutHelper, 1);
    mAdapters.add(mEditBetterHeaderAdapter);
  }

  private void setLimitedVolume(List<DelegateAdapter.Adapter> mAdapters) {
    SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
    singleLayoutHelper.setMarginBottom(10);
    mLimitedVolumeAdapter = new LimitedVolumeAdapter(this, singleLayoutHelper, 1);
    mAdapters.add(mLimitedVolumeAdapter);
  }

  private void bind(VirtualLayoutManager mLayoutManager, List<DelegateAdapter.Adapter> mAdapters) {
    //绑定delegateAdapter
    DelegateAdapter delegateAdapter = new DelegateAdapter(mLayoutManager);
    delegateAdapter.setAdapters(mAdapters);
    mList.setAdapter(delegateAdapter);
  }

  private void setHotLists(List<DelegateAdapter.Adapter> mAdapters) {
    //设置通栏布局
    SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
    singleLayoutHelper.setMarginBottom(10);
    mHotListAdapter = new HotListAdapter(this, singleLayoutHelper, 1);
    mAdapters.add(mHotListAdapter);
  }

  private void setTypes(List<DelegateAdapter.Adapter> mAdapters) {
    //设置Grid布局
    GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
    //是否自动扩展
    gridLayoutHelper.setAutoExpand(false);
    gridLayoutHelper.setMarginBottom(10);
    //自定义设置某些位置的Item的占格数
    gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        return 1;
      }
    });
    mAdapters.add(new TypeAdapter(this, gridLayoutHelper));
  }

  private void setHeader(List<DelegateAdapter.Adapter> mAdapters) {
    //设置线性布局
    LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
    linearLayoutHelper.setMarginBottom(10);
    mAdapters.add(new HeaderAdapter(this, linearLayoutHelper, 1));
  }
}
