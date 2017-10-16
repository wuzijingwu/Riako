package com.bawei.mvpdemo.model;

public interface IRegisterModel {


    public void register(String username,String password,RegisterModelImpl.RegisterModelImplListener listener);

}