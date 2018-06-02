package com.example.linpiaohsin.weicotest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.linpiaohsin.weicotest.database.DailogDao;
import com.example.linpiaohsin.weicotest.entity.ShowDialogFragment;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    final static int MENU_ID1 = 1;

    final static int MENU_ID2 = 2;
    int positon;
//    List<DialogWrite> dialog_write;
    ListView listView;
//    ListAdpter listAdpter;
    DailogDao dailogDao;
    Cursor cursor;

    SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("按menu键弹出添加菜单");
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.lv);
//        listAdpter=new ListAdpter();
        //TODO bundle传过来的对象
//        if(getIntent().getExtras()!=null) {
//            Bundle bundle = getIntent().getExtras();
//            int icon = bundle.getInt("icon");
//            String title = bundle.getString("title");
//            String content = bundle.getString("content");
//            dialog_write = new ArrayList<>();
//            dialog_write.add(new DialogWrite(icon, title, content));
//            listView.setAdapter(listAdpter);
//        }
        dailogDao=new DailogDao(this);
        cursor =dailogDao.search();
        if (cursor!=null) {
            String[] from={"title","mod","content","time"};
            int[] to={R.id.tv_title,R.id.iv,R.id.tv_content,R.id.tv_time};
            simpleCursorAdapter=new SimpleCursorAdapter(this,R.layout.list_view_item,cursor,from,to);
            listView.setAdapter(simpleCursorAdapter);
            setListViewHeightBasedOnChildren(listView);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,MENU_ID1,0,"添加").setIcon(R.mipmap.ic_launcher);
        menu.add(0,MENU_ID2,0,"删除").setIcon(R.mipmap.ic_launcher_round);

//       new MenuInflater(this).inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ID1:
//                Toast.makeText(this,"这里是添加",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(this,Second.class);
                startActivity(intent);
                break;
            case MENU_ID2:
                ShowDialogFragment showDialogFragment=new ShowDialogFragment();
                showDialogFragment.show(getFragmentManager(),"delete_Dialog");
                break;
        }
        return true;
    }
    //TODO scrollView嵌套listview
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
    //反射实现图标（toolbar）
    @SuppressLint("RestrictedApi")
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {

                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }


}
