package com.bawei.mvpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

public class ThreeActivity extends Activity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ImageLoader imageLoader;
    private ImageView images;
    private int count=0;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threeactivity);
        EditText edtext= (EditText) findViewById(R.id.edtext);
        images = (ImageView) findViewById(R.id.images);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(linearLayoutManager);
        getDates();

        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (count++){
                    case 0:
                        recyclerView.setLayoutManager(linearLayoutManager);
                        break;

                    case 1:
                        recyclerView.setLayoutManager(gridLayoutManager);
                        count=0;
                        break;

                }
            }
        });



    }

    //        setContentView(R.layout.threeactivity);
//
//



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
               recyclerView.setAdapter(new Mydapter(goods_list));

           }
       });
    }

    public class Mydapter extends RecyclerView.Adapter{
        private MyViewHolder myviewholder;
        List<News.GoodsListBean> goods_list;
        private final DisplayImageOptions options;

        public Mydapter(List<News.GoodsListBean> goods_list) {
            this.goods_list = goods_list;
            imageLoader=ImageLoader.getInstance();
                  File file= new File(Environment.getExternalStorageDirectory(),"Bwei");
                  if(!file.exists())
                      file.mkdirs();

                  ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(ThreeActivity.this)
                          .diskCache(new UnlimitedDiskCache(file))
                          .build();

                  imageLoader.init(configuration);

            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.ic_launcher)
                    .cacheOnDisk(true)
                    .build();

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(ThreeActivity.this).inflate(R.layout.three_item, parent, false);
            return new MyViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            myviewholder = (MyViewHolder) holder;
            myviewholder.text_three.setText(goods_list.get(position).getGoods_name());
            getimage(goods_list.get(position).getImage_url(),myviewholder.image_three);



        }

        @Override
        public int getItemCount() {
            return goods_list.size();
        }
        public class MyViewHolder extends RecyclerView.ViewHolder{


            private final ImageView image_three;
            private final TextView text_three;

            public MyViewHolder(View itemView) {
                super(itemView);
                image_three = (ImageView) itemView.findViewById(R.id.image_three);
                text_three = (TextView) itemView.findViewById(R.id.text_three);
            }
        }


    }

    public void getimage(String path, ImageView imageView) {

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .build();
            ImageLoader.getInstance().displayImage(path, imageView, options);


        }


}
