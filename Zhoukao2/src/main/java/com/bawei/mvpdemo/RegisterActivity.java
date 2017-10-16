package com.bawei.mvpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.mvpdemo.presenter.RegisterPresenter;
import com.bawei.mvpdemo.view.IRegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends Activity implements IRegisterView{

    @BindView(R.id.register_username)
    EditText register_username;

    @BindView(R.id.register_password)
    EditText register_password;

    @BindView(R.id.register_button)
    Button register_button;

    RegisterPresenter presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        ButterKnife.bind(this);

        presenter = new RegisterPresenter(this);


    }


    //按钮点击监听
    @OnClick(R.id.register_button)
    public void downButton(View view) {


        presenter.register(register_username.getText().toString().trim(),register_password.getText().toString().trim());


    }


    @Override
    public void usernameEmpty() {

        Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void passwordEmpty() {

        Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(String result) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });
        finish();

    }

    @Override
    public void onFailed() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        });
        finish();

    }
}
