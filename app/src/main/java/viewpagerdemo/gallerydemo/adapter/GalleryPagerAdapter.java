package viewpagerdemo.gallerydemo.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import viewpagerdemo.gallerydemo.R;
import viewpagerdemo.gallerydemo.biz.Bean;

/**
 * Created by Administrator on 2017/8/25.
 */

public class GalleryPagerAdapter extends PagerAdapter {

    private List<Bean> mData;
    private Activity mCxt;

    public GalleryPagerAdapter(List<Bean> mData, Activity mCxt) {
        this.mData = mData;
        this.mCxt = mCxt;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mCxt).
                inflate(R.layout.viewpager_item, null);

        TextView tv_title = view.findViewById(R.id.tv_title);
        LinearLayout ll_title = view.findViewById(R.id.ll_title);
        tv_title.setText(mData.get(position).getTitle());
        ll_title.setBackgroundColor(mData.get(position).getColor());

        container.addView(view);
        return view;
    }
}
