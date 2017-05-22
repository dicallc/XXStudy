package com.xiaoxin.fashionthing.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.xiaoxin.fashionthing.utils.Constant;

public class TestNomalAdapter extends StaticPagerAdapter {
  private  Context mContext;
  private String[] imgs = {
      "/gitem/2017/05/19/112335_100.jpg@s_2,w_1020,h_510%7Cc_1,x_134,y_134,w_752,h_376",
      "/gitem/2017/05/18/164057_552.jpg@s_2,w_1020,h_510%7Cc_1,x_134,y_134,w_752,h_376",
      "/2017/04/01/20170401_032459_494.jpg@s_2,w_1020,h_510%7Cc_1,x_134,y_134,w_752,h_376",
      "/gitem/2017/05/18/145426_416.jpg@s_2,w_1020,h_510%7Cc_1,x_134,y_134,w_752,h_376"
  };

  public TestNomalAdapter(Context mContext) {
    this.mContext=mContext;
  }

  @Override public View getView(ViewGroup container, int position) {
    ImageView view = new ImageView(container.getContext());
    view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT));
    view.setScaleType(ImageView.ScaleType.CENTER_CROP);
    Glide.with(mContext).load(Constant.IMAGE_HEAD_URL+imgs[position]).into(view);
    return view;
  }

  @Override public int getCount() {
    return imgs.length;
  }
}