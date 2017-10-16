package test.bwie.com.wuzijing1011;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import test.bwie.com.wuzijing1011.bean.Gouwu;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private String path = "http://apiv3.yangkeduo.com/v5/newlist?page=1&size=20";
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("是否分享此新闻");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//
                        Intent intent = new Intent(MainActivity.this,TwoActivity.class);
                        startActivity(intent);
                    }
                });
                builder.create().show();



            }
        });
        getDates();


    }

    public void getDates() {
        OkHttp.getAsync(path, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                Gouwu gouwu = gson.fromJson(result, Gouwu.class);
                List<Gouwu.GoodsListBean> goods_list = gouwu.getGoods_list();
                listview.setAdapter(new Mydapter(goods_list));


            }
        });
    }

    public class Mydapter extends BaseAdapter {
        List<Gouwu.GoodsListBean> goods_list;
        private TextView text;
        private ImageView image;
        private final DisplayImageOptions options;

        public Mydapter(List<Gouwu.GoodsListBean> goods_list) {
            this.goods_list = goods_list;
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
        public int getCount() {

            return goods_list.size();
        }

        @Override
        public Object getItem(int position) {

            return goods_list.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = convertView.inflate(MainActivity.this, R.layout.item, null);
                text = convertView.findViewById(R.id.text);
                image = convertView.findViewById(R.id.image);
            }
            text.setText(goods_list.get(position).getShort_name());
            getimage(goods_list.get(position).getImage_url(), image);

            return convertView;
        }

        public void getimage(String path, ImageView imageView) {

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .build();
            ImageLoader.getInstance().displayImage(path, imageView, options);


        }


    }
}
