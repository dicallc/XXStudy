package com.xiaoxin.fashionthing.home.editbetter;

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
import com.bumptech.glide.Glide;
import com.xiaoxin.fashionthing.R;
import com.xiaoxin.fashionthing.utils.Constant;
import java.util.ArrayList;
import java.util.List;

public class EditBetterAdapter extends DelegateAdapter.Adapter<EditBetterAdapter.MainViewHolder> {
  private Context context;
  private LayoutHelper layoutHelper;
  private RecyclerView.LayoutParams layoutParams;

  public void setList(List<EditBetter> mList) {
    this.mList = mList;
    notifyDataSetChanged();
  }

  private List<EditBetter> mList = new ArrayList<>();

  public EditBetterAdapter(Context context, LayoutHelper layoutHelper) {
    this(context, layoutHelper,
        new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
  }

  public EditBetterAdapter(Context context, LayoutHelper layoutHelper,
      @NonNull RecyclerView.LayoutParams layoutParams) {
    this.context = context;
    this.layoutHelper = layoutHelper;
    this.layoutParams = layoutParams;
  }

  @Override public LayoutHelper onCreateLayoutHelper() {
    return layoutHelper;
  }

  @Override public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MainViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_edit_better, parent, false));
  }

  @Override public void onBindViewHolder(MainViewHolder holder, int position) {
    EditBetter mEditBetter = mList.get(position);
    Glide.with(context).load(Constant.IMAGE_HEAD_URL+mEditBetter.Picture).into( holder.item_icon);
    holder.item_name.setText(mEditBetter.ProductName);
    holder.item_zhekou.setText(mEditBetter.PromotionInfoPrice);
    holder.item_sailnum.setText("月销"+mEditBetter.SalesVolume);
    holder.item_price.setText(mEditBetter.RealPrice);
    holder.item_juannum.setText(mEditBetter.QuanInfo);
    holder.item_source_type.setText(mEditBetter.OriginStoreName);
  }

  @Override public int getItemCount() {
    return mList.size();
  }

  static class MainViewHolder extends RecyclerView.ViewHolder {
    public ImageView item_icon;
    public TextView item_source_type;
    public TextView item_name;
    public TextView item_zhekou;
    public TextView item_sailnum;
    public TextView item_price;
    public TextView item_juannum;
    public MainViewHolder(View rootView) {
      super(rootView);
      this.item_icon = (ImageView) rootView.findViewById(R.id.item_icon);
      this.item_source_type = (TextView) rootView.findViewById(R.id.item_source_type);
      this.item_name = (TextView) rootView.findViewById(R.id.item_name);
      this.item_zhekou = (TextView) rootView.findViewById(R.id.item_zhekou);
      this.item_sailnum = (TextView) rootView.findViewById(R.id.item_sailnum);
      this.item_price = (TextView) rootView.findViewById(R.id.item_price);
      this.item_juannum = (TextView) rootView.findViewById(R.id.item_juannum);
    }
  }

}
