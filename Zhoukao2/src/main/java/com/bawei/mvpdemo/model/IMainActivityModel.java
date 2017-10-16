package com.bawei.mvpdemo.model;

/**
 * Created by Administrator on 2017/6/13/0013.
 */

public interface IMainActivityModel {

    //做方法的申明

    //登录
    public void login(String username, String password, MainActivityModel.MainActivityModelListener listener);

}
