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
import com.xiaoxin.fashionthing.home.hotlist.HotListHAdapter;
import com.xiaoxin.fashionthing.home.hotlist.HotThing;
import java.util.ArrayList;
import java.util.List;

public class HotListAdapter extends DelegateAdapter.Adapter<HotListAdapter.MainViewHolder> {
  private Context context;
  private LayoutHelper layoutHelper;
  private RecyclerView.LayoutParams layoutParams;
  private int count = 0;
  private List<HotThing> mHotLists = new ArrayList<>();
  private LinearLayoutManager mLinearLayoutManager;
  private HotListHAdapter mHotListHAdapter;

  public HotListAdapter(Context context, LayoutHelper layoutHelper, int count) {
    this(context, layoutHelper, count,
        new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
  }

  public HotListAdapter(Context context, LayoutHelper layoutHelper, int count,
      @NonNull RecyclerView.LayoutParams layoutParams) {
    this.context = context;
    this.layoutHelper = layoutHelper;
    this.count = count;
    this.layoutParams = layoutParams;
    mLinearLayoutManager = new LinearLayoutManager(context);
    mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    mHotListHAdapter = new HotListHAdapter(mHotLists);
  }

  @Override public LayoutHelper onCreateLayoutHelper() {
    return layoutHelper;
  }

  @Override public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MainViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_hotlists, parent, false));
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

  public void setHotListData(List<HotThing> list) {
    mHotLists.clear();
    mHotListHAdapter.addData(list);
    mHotListHAdapter.notifyDataSetChanged();
  }
}
