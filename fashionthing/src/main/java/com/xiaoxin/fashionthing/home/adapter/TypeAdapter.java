package com.xiaoxin.fashionthing.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.xiaoxin.fashionthing.R;

public class TypeAdapter extends DelegateAdapter.Adapter<TypeAdapter.MainViewHolder> {
  private Context context;
  private LayoutHelper layoutHelper;
  private RecyclerView.LayoutParams layoutParams;
  private int count = 0;
  private String[] txts = {
      "辣榜", "百元卷", "一元包邮", "白菜价", "福包", "淘抢购", "我的设备", "精选推送",
  };
  private int[] imgs = {
      R.mipmap.main_cat_entry_1, R.mipmap.main_cat_entry_2, R.mipmap.main_cat_entry_3,
      R.mipmap.main_cat_entry_4, R.mipmap.main_cat_entry_5, R.mipmap.main_cat_entry_6,
      R.mipmap.main_cat_entry_7, R.mipmap.main_cat_entry_8,
  };

  public TypeAdapter(Context context, LayoutHelper layoutHelper) {
    this(context, layoutHelper, 8,
        new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
  }

  public TypeAdapter(Context context, LayoutHelper layoutHelper, int count,
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
        LayoutInflater.from(context).inflate(R.layout.item_types, parent, false));
  }

  @Override public void onBindViewHolder(MainViewHolder holder, int position) {
    //holder.mRollPagerView.setAdapter(new TestNomalAdapter(context));
    holder.mImageView.setImageResource(imgs[position]);
    holder.mTextView.setText(txts[position]);
  }

  @Override public int getItemCount() {
    return count;
  }

  static class MainViewHolder extends RecyclerView.ViewHolder {
    public ImageView mImageView;
    public TextView mTextView;

    public MainViewHolder(View itemView) {
      super(itemView);
      mImageView = (ImageView) itemView.findViewById(R.id.item_img);
      mTextView = (TextView) itemView.findViewById(R.id.item_txt);
    }
  }
}
