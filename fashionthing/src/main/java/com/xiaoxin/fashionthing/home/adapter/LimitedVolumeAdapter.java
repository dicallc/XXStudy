package com.xiaoxin.fashionthing.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.xiaoxin.fashionthing.R;
import com.xiaoxin.fashionthing.home.limitedvolume.LimitedVolume;
import com.xiaoxin.fashionthing.home.limitedvolume.LimitedVolumeListHAdapter;
import java.util.ArrayList;
import java.util.List;

public class LimitedVolumeAdapter
    extends DelegateAdapter.Adapter<LimitedVolumeAdapter.MainViewHolder> {
  private Context context;
  private LayoutHelper layoutHelper;
  private RecyclerView.LayoutParams layoutParams;
  private int count = 0;
  private List<LimitedVolume> mLimitedVolumes = new ArrayList<>();
  private LinearLayoutManager mLinearLayoutManager;
  private LimitedVolumeListHAdapter mHotListHAdapter;

  public LimitedVolumeAdapter(Context context, LayoutHelper layoutHelper, int count) {
    this(context, layoutHelper, count,
        new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
  }

  public LimitedVolumeAdapter(Context context, LayoutHelper layoutHelper, int count,
      @NonNull RecyclerView.LayoutParams layoutParams) {
    this.context = context;
    this.layoutHelper = layoutHelper;
    this.count = count;
    this.layoutParams = layoutParams;
    mLinearLayoutManager = new LinearLayoutManager(context);
    mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    mHotListHAdapter = new LimitedVolumeListHAdapter(mLimitedVolumes);
  }

  @Override public LayoutHelper onCreateLayoutHelper() {
    return layoutHelper;
  }

  @Override public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MainViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_timelimites, parent, false));
  }

  @Override public void onBindViewHolder(MainViewHolder holder, int position) {
    holder.mRecyclerView.setLayoutManager(mLinearLayoutManager);
    holder.mRecyclerView.setAdapter(mHotListHAdapter);
  }

  @Override public int getItemCount() {
    return count;
  }

  static class MainViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView mRecyclerView;

    public MainViewHolder(View itemView) {
      super(itemView);
      mRecyclerView = (RecyclerView) itemView.findViewById(R.id.horizontal_list);
    }
  }

  public void setLimitedVolumeListData(List<LimitedVolume> list) {
    mLimitedVolumes.clear();
    mHotListHAdapter.addData(list);
    mHotListHAdapter.notifyDataSetChanged();
  }
}
