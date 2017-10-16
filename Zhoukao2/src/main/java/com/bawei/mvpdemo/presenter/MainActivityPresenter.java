package com.bawei.mvpdemo.presenter;

import android.text.TextUtils;

import com.bawei.mvpdemo.model.MainActivityModel;
import com.bawei.mvpdemo.view.MainActivityView;

/**
 *         Presenter表示器是 model 和view 通讯的桥梁
 */

public class MainActivityPresenter {


    public MainActivityView mainActivityView;
    public MainActivityModel mainActivityModel;

    public MainActivityPresenter(MainActivityView mainActivityView){

        this.mainActivityView = mainActivityView;
        this.mainActivityModel = new MainActivityModel();

    }


    //用户名和密码为空
    public void down(String username, String password){

        if (TextUtils.isEmpty(username)){

            mainActivityView.usernameEmpty();

            return;

        }
        if (TextUtils.isEmpty(password)){

            mainActivityView.passwordEmpty();

            return;

        }



        mainActivityModel.login(username, password, new MainActivityModel.MainActivityModelListener() {
            @Override
            public void onSuccess() {

                mainActivityView.loginSucuess();

            }

            @Override
            public void onFailed() {

                mainActivityView.loginFailed();

            }
        });




    }


}
