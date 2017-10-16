package com.bawei.mvpdemo.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *    Model模型层
 */

public class MainActivityModel implements IMainActivityModel {


    public MainActivityModelListener listener ;

    public MainActivityModel(){
    }


    @Override
    public void login(String username, String password, final MainActivityModelListener listener) {


        //网络请求数据库
        String url = "http://qhb.2dyt.com/Bwei/login" ;

        //地址拼接
        StringBuilder builder = new StringBuilder();

        builder.append(url);
        builder.append("?").append("username=").append(username).append("&").append("password=").append(password).append("&")
                .append("postkey=1503d");


        OkHttpClient okHttpClient = new OkHttpClient();

        final Request request = new Request.Builder().url(builder.toString()).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                listener.onFailed();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result =   response.body().string() ;
                System.out.println("result = " + Thread.currentThread().getName());

                listener.onSuccess();

                // 数据持久化

            }
        });


    }


    //为Presenter写一个内部接口通知成功失败
    public interface MainActivityModelListener{

        public void onSuccess();

        public void onFailed();

    }

}
