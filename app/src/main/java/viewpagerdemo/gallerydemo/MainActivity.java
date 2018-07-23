package viewpagerdemo.gallerydemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import viewpagerdemo.gallerydemo.adapter.GalleryPagerAdapter;
import viewpagerdemo.gallerydemo.biz.Bean;
import viewpagerdemo.gallerydemo.gallery.GalleryPageTransform;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private ViewPager viewPager;
    private List<Bean> mDatas;

    private GalleryPagerAdapter adapter;

    private int page = 1;
    private int currentPostion;

    //viewpager是否处于惯性滑动
    private boolean mIsScrolled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        viewPager.setPageMargin((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, metrics));
        viewPager.setPageTransformer(true, new GalleryPageTransform());
        mDatas = new ArrayList<>();
        adapter = new GalleryPagerAdapter(getDatas(), this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPostion = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {

                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mIsScrolled = false;
                        break;

                    case ViewPager.SCROLL_STATE_SETTLING:
                        mIsScrolled = true;
                        break;

                    case ViewPager.SCROLL_STATE_IDLE:

                        if (!mIsScrolled) {
                            //TODO 改变数据源
                            if(currentPostion == 0){
                                Log.w(TAG, "刷新数据--->");
                                page = 1;
                            }else{
                                Log.w(TAG, "加载数据--->");
                                page ++;
                            }
                            getDatas();
                            adapter.notifyDataSetChanged();

                        }
                        mIsScrolled = true;
                        break;

                }


            }
        });
    }

    public List getDatas() {

        int[] arr = {R.color.grey, R.color.blue, R.color.black,
                R.color.grey, R.color.blue, R.color.black};


        for (int i = mDatas.size(); i < 6 * page; i++) {

            mDatas.add(new Bean("第" + i + "条数据", arr[i % 6]));
        }
        return mDatas;
    }
}
