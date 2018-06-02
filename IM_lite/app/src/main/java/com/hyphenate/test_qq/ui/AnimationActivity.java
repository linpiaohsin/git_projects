package com.hyphenate.test_qq.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.test_qq.R;

/**
 * Created by linpiaohsin on 2017/11/17.
 */

public class AnimationActivity extends Activity {
    TextView textView;
    ImageView imageView1;
    ImageView imageView2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.animation_layout);

        textView= (TextView) findViewById(R.id.text_animation);
        imageView1= (ImageView) findViewById(R.id.imageView1);
        imageView2= (ImageView) findViewById(R.id.imageView2);
        animationLeft();
        animationRight();
        animationText();

    }
    public void animationLeft(){
        AnimationSet animationSet=new AnimationSet(true);
        TranslateAnimation translateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,
                -1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
        translateAnimation.setDuration(3*1000);
        translateAnimation.setStartOffset(8*100);
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);
        imageView1.startAnimation(animationSet);
    }
    public void animationRight(){
        AnimationSet animationSet=new AnimationSet(true);
        TranslateAnimation translateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,
                +1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
        translateAnimation.setDuration(3*1000);
        translateAnimation.setStartOffset(8*100);
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);
        imageView2.startAnimation(animationSet);
    }
    public void animationText(){
        AnimationSet animationSet=new AnimationSet(true);
        ScaleAnimation scaleAnimation=new ScaleAnimation(1f,3f,1f,3f,Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(1*1000);
        AlphaAnimation alphaAnimation=new AlphaAnimation(1,0.00001f);
        alphaAnimation.setDuration(2*1000);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);
        textView.startAnimation(animationSet);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AnimationActivity.this,MainActivity.class));
            }
        },4500);
    }
}
