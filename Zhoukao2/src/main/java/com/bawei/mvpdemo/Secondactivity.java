package com.bawei.mvpdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.mvpdemo.bean.News;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Request;

/**
 * Created by dell on 2017/10/14.
 */

public class Secondactivity extends Activity {

    private Button tuichu;
    private Button sousuo;
    private ImageView touxiang;
    private TextView yonghu;
    private TextView nicheng;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);
        yonghu = (TextView) findViewById(R.id.yonghu);
        touxiang = (ImageView) findViewById(R.id.touxiang);
        nicheng = (TextView) findViewById(R.id.nicheng);
        tuichu = (Button) findViewById(R.id.tuichu);
        sousuo = (Button) findViewById(R.id.sousuo);
        imageLoader=ImageLoader.getInstance();
              File file= new File(Environment.getExternalStorageDirectory(),"Bwei");
              if(!file.exists())
                  file.mkdirs();

              ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                      .diskCache(new UnlimitedDiskCache(file))
                      .build();

              imageLoader.init(configuration);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheOnDisk(true)
                .build();

        getDates();

        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Secondactivity.this,ThreeActivity.class);
                startActivity(intent);
            }
        });









    }

    public void getDates() {
        OkHttp.getAsync("http://apiv3.yangkeduo.com/v5/newlist?page=6", new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                News news = gson.fromJson(result, News.class);
                List<News.GoodsListBean> goods_list = news.getGoods_list();
                getimage(goods_list.get(0).getImage_url(),touxiang);
                yonghu.setText(goods_list.get(0).getGoods_name());
                nicheng.setText(goods_list.get(1).getShort_name());


            }
        });

    }

    public void getimage(String path, ImageView imageView) {

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .build();
            ImageLoader.getInstance().displayImage(path, imageView, options);


        }


}
