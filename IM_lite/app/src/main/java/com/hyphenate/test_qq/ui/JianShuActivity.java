package com.hyphenate.test_qq.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hyphenate.test_qq.R;
import com.hyphenate.test_qq.adapter.News;
import com.hyphenate.test_qq.adapter.NewsAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linpiaohsin on 2017/12/7.
 */

public class JianShuActivity extends Activity {
    private List<News> newsList;
    private NewsAdapter adapter;
    private Handler handler;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianshu);
        newsList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.news_lv);
        getNews();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){
                    adapter = new NewsAdapter(JianShuActivity.this,newsList);
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            News news = newsList.get(position);
                            Intent intent = new Intent(JianShuActivity.this,NewsDisplayActvivity.class);
                            intent.putExtra("news_url",news.getNewsUrl());
                            startActivity(intent);
                        }
                    });
                }
            }
        };

    }



    private void getNews(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //jsoup获取简书最新文章
                    Document doc = Jsoup.connect("http://www.jianshu.com/" ).get();
                    Elements titleLinks = doc.select("li.have-img");    //解析来获取每条简书标题
                    // Elements descLinks = doc.select("div.list-content");
                    Elements timeLinks = doc.select("a.nickname");   //解析来获取每条简书的时间与来源
//                    Log.e("title",Integer.toString(titleLinks.size()));
                    for(int j = 0;j < titleLinks.size();j++){
                        String title = titleLinks.get(j).select("a.title").text();
                        String uri = "http://www.jianshu.com"+titleLinks.get(j).select("a").attr("href");
                        Log.e("tip",uri);
                        //   String desc = descLinks.get(j).select("span").text();
                        String time = timeLinks.get(j).select("a.nickname").select("a").text();
                        News news = new News(title,uri,null,time);
                        newsList.add(news);
                    }

                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}


