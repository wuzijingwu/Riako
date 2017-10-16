package com.bawei.mvpdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.mvpdemo.presenter.MainActivityPresenter;
import com.bawei.mvpdemo.view.MainActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*
       View视图层
 */

public class MainActivity extends Activity implements MainActivityView {


    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.but)
    Button but;

    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        mainActivityPresenter = new MainActivityPresenter(this);


    }


    //按钮点击监听
    @OnClick(R.id.button)
    public void downButton(View view) {


        mainActivityPresenter.down(username.getText().toString(), password.getText().toString());


    }


    @OnClick(R.id.but)
    public void jumpButton(View view) {

        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);


    }


    @Override
    public void usernameEmpty() {

        Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void passwordEmpty() {

        Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loginSucuess() {

        //在子线程中执行
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Secondactivity.class);
                startActivity(intent);


            }
        });


    }

    @Override
    public void loginFailed() {


        //在子线程中执行
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
