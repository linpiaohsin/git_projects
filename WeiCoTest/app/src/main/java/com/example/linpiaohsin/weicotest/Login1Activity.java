package com.example.linpiaohsin.weicotest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class Login1Activity extends Activity {
    /** Called when the activity is first created. */
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText editText_uid;
    EditText editText_pwd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editText_pwd= (EditText) findViewById(R.id.etPwd);
        editText_uid= (EditText) findViewById(R.id.etUid);
        checkBox= (CheckBox) findViewById(R.id.is_checked);
        sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        if (!checkBox.isChecked()&&sharedPreferences.getString("userName","")!="")
        {
            checkBox.setChecked(true);
        }
        editText_uid.setText(sharedPreferences.getString("userName",""));
        editText_pwd.setText(sharedPreferences.getString("password",""));
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userName = editText_uid.getText().toString();
                String password = editText_pwd.getText().toString();
                editor=sharedPreferences.edit();
                editor.putString("userName", userName);
                editor.putString("password", password);

                editor.commit();
                startActivity(new Intent(Login1Activity.this,MainActivity.class));
            }
        });









    }
}