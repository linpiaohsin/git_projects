package com.hyphenate.test_qq.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.hyphenate.test_qq.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linpiaohsin on 2017/11/15.
 */

public class ViewPagerActivity extends Activity {
    ViewPager viewPager;
    List<ImageView> listView;
    int[] images=new int[]{R.drawable.pic1,R.drawable.pic2,R.drawable.pic3};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.view_pager_activity);

        viewPager= (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Boolean misScrolled=false;
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        misScrolled = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        misScrolled = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !misScrolled) {
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(ViewPagerActivity.this, AnimationActivity.class));

                                }
                            },1*1000);

                        }
                        misScrolled = true;
                        break;
                }
            }
        });
        listView=new ArrayList<>();
        init();
        PagerAdapter pagerAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return listView.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(listView.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(listView.get(position));
                return listView.get(position);
            }
        };
        viewPager.setAdapter(pagerAdapter);

    }

    private void init() {
        for (int image:images){
            ImageView imageView=new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(image);
            listView.add(imageView);
        }
    }

}
