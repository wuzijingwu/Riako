package com.bawei.mvpdemo.presenter;

import android.text.TextUtils;

import com.bawei.mvpdemo.model.RegisterModelImpl;
import com.bawei.mvpdemo.view.IRegisterView;

public class RegisterPresenter {


    public IRegisterView iRegisterView;
    public RegisterModelImpl registerModel ;

    public RegisterPresenter(IRegisterView registerView){
        this.iRegisterView = registerView;
        this.registerModel = new RegisterModelImpl();

    }


    public void register(String username,String password){

        if(TextUtils.isEmpty(username)){

            iRegisterView.usernameEmpty();
            return;
        }

        if(TextUtils.isEmpty(password)){

            iRegisterView.passwordEmpty();
            return;
        }


        registerModel.register(username, password, new RegisterModelImpl.RegisterModelImplListener() {
            @Override
            public void onSuccess(String result) {

                System.out.println("result = " + result);
                if(iRegisterView != null){
                    iRegisterView.onSuccess(result);
                }
            }

            @Override
            public void onFailed() {

                if(iRegisterView != null){
                    iRegisterView.onFailed();
                }
            }
        });



    }

}