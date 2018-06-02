package com.example.linpiaohsin.weicotest;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.linpiaohsin.weicotest.database.DailogDao;
import com.example.linpiaohsin.weicotest.entity.Dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by linpiaohsin on 2017/10/24.
 */

public class Second extends Activity {
    List<Pic> dataList;
    EditText editText_title;
    EditText editText_content;
    DailogDao dailogDao;
    Dialog dialog;
    TextView textView_time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dailogDao=new DailogDao(this);
        setContentView(R.layout.second);

        findViewById(R.id.btn_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Second.this,MainActivity.class);
                startActivity(intent);

            }
        });
        editText_content= (EditText) findViewById(R.id.content);
        editText_title= (EditText) findViewById(R.id.title);


        final Spinner spinner= (Spinner) findViewById(R.id.spinner);
        dataList = new ArrayList<>();
        dataList.add(new Pic(R.drawable.pic1));
        dataList.add(new Pic(R.drawable.pic2));
        dataList.add(new Pic(R.drawable.pic3));
        dataList.add(new Pic(R.drawable.pic4));
        dataList.add(new Pic(R.drawable.pic5));
        dataList.add(new Pic(R.drawable.pic6));
        dataList.add(new Pic(R.drawable.pic7));
        dataList.add(new Pic(R.drawable.pic8));


        final int[] icon_id = new int[1];
        final SpinnerAdapter myAdapter=new SpinnerAdapter();
        spinner.setAdapter(myAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                icon_id[0] =dataList.get(position).id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        final String time=simpleDateFormat.format(new Date());
        Log.e("tip",time);
        textView_time= (TextView) findViewById(R.id.time_set);
        textView_time.setText(time);
        findViewById(R.id.btn_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=editText_title.getText().toString();
                String content=editText_content.getText().toString();
                Intent intent=new Intent();
                intent.setClass(Second.this,MainActivity.class);
//                int icon_id=R.drawable.pic1;
//                Log.e("tip",  spinner.getSelectedItem().toString());
//                int icon_id=spinner.getSelectedItem();
                //TODO bundle实现跳转绑定
//                Bundle bundle=new Bundle();
//                bundle.putString("title",title);
//                bundle.putString("content",content);
//                bundle.putInt("icon",icon_id[0]);
//                intent.putExtras(bundle);

                dialog=new Dialog(icon_id[0],title,content);

                dialog.setTime(time);
                Log.e("tip",dialog.getTime()+"zzz");
                dailogDao.insert(dialog);
                startActivity(intent);

            }
        });


    }
    class Pic{
        int id;
        Pic(int id){
            this.id=id;
        }
    }
    class SpinnerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View picView= LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item,null);
            ImageView imageView= (ImageView) picView.findViewById(R.id.iv);
            imageView.setImageResource(dataList.get(position).id);
            return picView;
        }
    }

}
