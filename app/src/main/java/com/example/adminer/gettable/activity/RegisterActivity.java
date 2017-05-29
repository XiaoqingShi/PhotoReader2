package com.example.adminer.gettable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.adminer.gettable.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import htmlviewer.tools.Tools;

/**
 * Created by adminer on 2017/4/15.
 */

public class RegisterActivity extends AppCompatActivity {
    private Button register;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
        }
    };
    private String sex;
    private EditText et_pass;
    private EditText et_name;
    private EditText et_number;
    private EditText et_confirm_pass;
    private EditText et_email;
    private String number;
    private String confirm_pass;
    private String pass;
    private String name;
    private String email;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        register = (Button) findViewById(R.id.btn_register);

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RadioButton man = (RadioButton) findViewById(R.id.rb_man);
                RadioButton woman = (RadioButton) findViewById(R.id.rb_woman);
                sex = "none";
                if (woman.isChecked()) {
                    sex = "woman";
                }
                if (man.isChecked()) {
                    sex = "man";
                }
                et_name = (EditText) findViewById(R.id.et_name);
                et_pass = (EditText) findViewById(R.id.et_pass);
                et_number = (EditText) findViewById(R.id.et_call_number);
                et_email= (EditText) findViewById(R.id.et_email);
                et_confirm_pass = (EditText) findViewById(R.id.et_confirm_pass);
                name = et_name.getText().toString();
                pass = et_pass.getText().toString();
                email=et_email.getText().toString();
                confirm_pass = et_confirm_pass.getText().toString();
                number = et_number.getText().toString();
                //birthday
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        String path = "http://125.217.32.224:8080/PhotoRecognition/register?username=" + name + "&password=" + pass+"&sex="+sex+"&email="+ email+"&phonenumber="+number;
                        try {
                            URL url = new URL(path);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.setReadTimeout(8000);
                            conn.setConnectTimeout(8000);

                            if (conn.getResponseCode() == 200) {
                                InputStream is = conn.getInputStream();
                                String text = Tools.getTextFromStream(is);
                                Message msg = handler.obtainMessage();
                                msg.obj = text;
                                handler.sendMessage(msg);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                };
                Log.d("TAG1",name);
                Log.d("TAG1",pass);
                Log.d("TAG1",sex);
                Log.d("TAG1",number);
                Log.d("TAG",email);
                if(name.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                }
                else if(pass.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                }
                else if(pass.length()<6)
                {
                    Toast.makeText(RegisterActivity.this, "密码长度应多于6位", Toast.LENGTH_LONG).show();
                }
                else if(pass.length()>16)
                {
                    Toast.makeText(RegisterActivity.this, "密码长度应小于16位", Toast.LENGTH_LONG).show();
                }
                else if (!confirm_pass.equals(pass)) {
                    Toast.makeText(RegisterActivity.this, "密码和确认密码必须一致", Toast.LENGTH_LONG).show();
                } else if (sex.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "请选择性别", Toast.LENGTH_LONG).show();
                }
                else if(number.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "电话号码不能为空", Toast.LENGTH_LONG).show();
                }
                else if(email.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "邮箱不能为空", Toast.LENGTH_LONG).show();
                }
                else
                {
                    thread.start();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }



}
