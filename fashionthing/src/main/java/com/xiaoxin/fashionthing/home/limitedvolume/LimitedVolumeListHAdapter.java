package com.xiaoxin.fashionthing.home.limitedvolume;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.fashionthing.R;
import com.xiaoxin.fashionthing.utils.Constant;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class LimitedVolumeListHAdapter extends BaseQuickAdapter<LimitedVolume, BaseViewHolder> {
  public LimitedVolumeListHAdapter(List<LimitedVolume> data) {
    super(R.layout.item_hotlists_h_item, data);
  }

  @Override protected void convert(BaseViewHolder helper, LimitedVolume item) {
    ImageView mImageView = helper.getView(R.id.item_img);
    Glide.with(mContext).load(Constant.IMAGE_HEAD_URL + item.Picture).into(mImageView);
    helper.setText(R.id.item_title,item.ProductName);
    helper.setText(R.id.item_type,item.PromotionInfoPrice);
  }
}
