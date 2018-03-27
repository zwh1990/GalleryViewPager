package viewpagerdemo.gallerydemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import viewpagerdemo.gallerydemo.adapter.GalleryPagerAdapter;
import viewpagerdemo.gallerydemo.biz.Bean;
import viewpagerdemo.gallerydemo.gallery.GalleryPageTransform;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Bean> mDatas;

    private GalleryPagerAdapter adapter;

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
        adapter = new GalleryPagerAdapter(getDatas(),this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    public List getDatas() {

        int [] arr = {R.color.grey,R.color.blue,R.color.black,
                R.color.grey,R.color.blue,R.color.black};

        for (int i = 0; i <6; i++) {

            mDatas.add(new Bean("第" + i +"条数据",arr[i]));
        }
        return mDatas;
    }
}
