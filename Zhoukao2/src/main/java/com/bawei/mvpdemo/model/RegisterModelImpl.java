package com.bawei.mvpdemo.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterModelImpl implements IRegisterModel {




    @Override
    public void register(String username, String password,final RegisterModelImplListener listener) {


        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url("http://qhb.2dyt.com/Bwei/register?phone=18519594677&password=123456&postkey=1503d").build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                listener.onFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                listener.onSuccess(response.body().string());


            }
        });




    }






    public interface RegisterModelImplListener {

        public void onSuccess(String result);

        public void onFailed();

    }

}