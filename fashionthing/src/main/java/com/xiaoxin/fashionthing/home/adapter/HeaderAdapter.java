package com.xiaoxin.fashionthing.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.jude.rollviewpager.RollPagerView;
import com.xiaoxin.fashionthing.R;

public class HeaderAdapter extends DelegateAdapter.Adapter<HeaderAdapter.MainViewHolder> {
  private Context context;
  private LayoutHelper layoutHelper;
  private RecyclerView.LayoutParams layoutParams;
  private int count = 0;

  public HeaderAdapter(Context context, LayoutHelper layoutHelper, int count) {
    this(context, layoutHelper, count,
        new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
  }

  public HeaderAdapter(Context context, LayoutHelper layoutHelper, int count,
      @NonNull RecyclerView.LayoutParams layoutParams) {
    this.context = context;
    this.layoutHelper = layoutHelper;
    this.count = count;
    this.layoutParams = layoutParams;

  }

  @Override public LayoutHelper onCreateLayoutHelper() {
    return layoutHelper;
  }

  @Override public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MainViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_header, parent, false));
  }

  @Override public void onBindViewHolder(MainViewHolder holder, int position) {
    holder.mRollPagerView.setAdapter(new TestNomalAdapter(context));
  }

  @Override public int getItemCount() {
    return count;
  }

  static class MainViewHolder extends RecyclerView.ViewHolder {
    public RollPagerView mRollPagerView;

    public MainViewHolder(View itemView) {
      super(itemView);
      mRollPagerView = (RollPagerView) itemView.findViewById(R.id.roll_view);
    }
  }
}
