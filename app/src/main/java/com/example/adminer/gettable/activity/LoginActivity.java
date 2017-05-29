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
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminer.gettable.R;
import com.example.adminer.gettable.dao.User;
import com.example.adminer.gettable.view.GifView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {


    private Button btn_login;
    private Button btn_loading;

    TextView textView;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            User user = (User) msg.obj;
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                Log.d("FUCK33","CHENGGONG");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        viewBackground();

        btn_loading = (Button) findViewById(R.id.bt_loading);
        textView = (TextView) findViewById(R.id.tv_login);


        btn_loading.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                EditText ed_user;
                EditText ed_pass;

                ed_user = (EditText) findViewById(R.id.et_user);
                ed_pass = (EditText) findViewById(R.id.et_password);

                final String name = ed_user.getText().toString();
                final String pass = ed_pass.getText().toString();



                new Thread() {
                    @Override
                    public void run() {
                        String path = "http://125.217.32.224:8080/PhotoRecognition/login?username=" + name + "&password=" + pass;
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder().url(path).build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            parseJSONWithGSON(responseData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }.start();

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    public void parseJSONWithGSON(String jsondata) {
        if(jsondata.equals("null")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                }
            });
        }
        else
        {
            User user;
            Gson gson = new Gson();
            user = gson.fromJson(jsondata, new TypeToken<User>() {
            }.getType());
            Message msg = handler.obtainMessage();
            msg.obj = user;
            handler.sendMessage(msg);
        }
    }

    private GifView gif1;
    private GifView gif2;
    public void viewBackground(){
        gif1 = (GifView) findViewById(R.id.gif1);
        gif1.setMovieResource(R.raw.test2);
    }
}
