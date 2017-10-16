package test.bwie.com.recyclerviewswiperefreshlayout;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageLoader imageLoader;
    private List<News.美女Bean> list;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String path = "http://c.3g.163.com/recommend/getChanListNews?channel=T1456112189138&size=20&devId=1uuFYbybIU2oqSRGyFrjCw%3D%3D&lat=%2F%2FOm%2B%2F8ScD%2B9fX1D8bxYWg%3D%3D&lon=LY2l8sFCNzaGzqWEPPgmUw%3D%3D&version=9.0&net=wifi&ts=1464769308&sign=bOVsnQQ6gJamli6%2BfINh6fC%2Fi9ydsM5XXPKOGRto5G948ErR02zJ6%2FKXOnxX046I&encryption=1&canal=meizu_store2014_news&mac=sSduRYcChdp%2BBL1a9Xa%2F9TC0ruPUyXM4Jwce4E9oM30%3D";
    private LinearLayoutManager linearLayoutManager;
    private Myadapter myadapter;
    private int p = 0;
    private String path2 = "&passport=";

//    &passport=

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        getDaes();
//下拉加载
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == list.size() - 1) {
                    p++;
                    getDaes();
                    myadapter.notifyDataSetChanged();
                }

            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                p++;
                getDaes();
                myadapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    public void getDaes() {
        OkHttp.getAsync(path + path2 + p, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                News news = gson.fromJson(result, News.class);
                list = news.get美女();
                myadapter = new Myadapter(list);
                recyclerView.setAdapter(myadapter);

            }
        });


    }

    class Myadapter extends RecyclerView.Adapter {

        List<News.美女Bean> list;
        private final DisplayImageOptions options;
        private myviewHolder myviewHolder;

        public Myadapter(List<News.美女Bean> list) {
            this.list = list;
            imageLoader = ImageLoader.getInstance();
            File file = new File(Environment.getExternalStorageDirectory(), "Bwei");
            if (!file.exists())
                file.mkdirs();

            ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(MainActivity.this)
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
            View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
            return new myviewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            myviewHolder = (myviewHolder) holder;
            myviewHolder.text.setText(list.get(position).getTitle());
            getimage(list.get(position).getImg(), myviewHolder.image);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class myviewHolder extends RecyclerView.ViewHolder {


            TextView text;
            ImageView image;

            public myviewHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.text);
                image = (ImageView) itemView.findViewById(R.id.image);

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
