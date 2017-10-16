package test.bwie.com.riako;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.limxing.xlistview.view.XListView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView xlist;
    private ImageLoader imageLoader;
    private int p = 0;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xlist = (XListView) findViewById(R.id.xlist);
        xlist.setPullLoadEnable(true);
        xlist.setXListViewListener(this);
        getDates("http://c.3g.163.com/recommend/getChanListNews?channel=T1456112189138&size=20&devId=1uuFYbybIU2oqSRGyFrjCw%3D%3D&lat=%2F%2FOm%2B%2F8ScD%2B9fX1D8bxYWg%3D%3D&lon=LY2l8sFCNzaGzqWEPPgmUw%3D%3D&version=9.0&net=wifi&ts=1464769308&sign=bOVsnQQ6gJamli6%2BfINh6fC%2Fi9ydsM5XXPKOGRto5G948ErR02zJ6%2FKXOnxX046I&encryption=1&canal=meizu_store2014_news&mac=sSduRYcChdp%2BBL1a9Xa%2F9TC0ruPUyXM4Jwce4E9oM30%3D", p + "");
//&passport=

    }

    public void getDates(String path, String passport) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    ReadInfo readInfo = gson.fromJson(s, ReadInfo.class);
                    List<ReadInfo.美女Bean> beanList = readInfo.get美女();
                    xlist.setAdapter(new Myadapte(beanList));
                }

            }

            @Override
            protected String doInBackground(String... strings) {

                try {
                    String path = strings[0];
                    String passport = strings[1];
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(200);
                    urlConnection.setReadTimeout(200);
                    int code = urlConnection.getResponseCode();
                    if (code == 200) {
                        InputStream is = urlConnection.getInputStream();
                        String json = StreamInfo.read(is);
                        return json;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }
        }.execute(path, passport);


    }

    @Override
    public void onRefresh() {
        p++;
        getDates("http://c.3g.163.com/recommend/getChanListNews?channel=T1456112189138&size=20&devId=1uuFYbybIU2oqSRGyFrjCw%3D%3D&lat=%2F%2FOm%2B%2F8ScD%2B9fX1D8bxYWg%3D%3D&lon=LY2l8sFCNzaGzqWEPPgmUw%3D%3D&version=9.0&net=wifi&ts=1464769308&sign=bOVsnQQ6gJamli6%2BfINh6fC%2Fi9ydsM5XXPKOGRto5G948ErR02zJ6%2FKXOnxX046I&encryption=1&canal=meizu_store2014_news&mac=sSduRYcChdp%2BBL1a9Xa%2F9TC0ruPUyXM4Jwce4E9oM30%3D", p + "");
        flag = true;
        xlist.stopRefresh(true);


    }

    @Override
    public void onLoadMore() {
        p++;
        getDates("http://c.3g.163.com/recommend/getChanListNews?channel=T1456112189138&size=20&devId=1uuFYbybIU2oqSRGyFrjCw%3D%3D&lat=%2F%2FOm%2B%2F8ScD%2B9fX1D8bxYWg%3D%3D&lon=LY2l8sFCNzaGzqWEPPgmUw%3D%3D&version=9.0&net=wifi&ts=1464769308&sign=bOVsnQQ6gJamli6%2BfINh6fC%2Fi9ydsM5XXPKOGRto5G948ErR02zJ6%2FKXOnxX046I&encryption=1&canal=meizu_store2014_news&mac=sSduRYcChdp%2BBL1a9Xa%2F9TC0ruPUyXM4Jwce4E9oM30%3D", p + "");
        flag = false;
        xlist.stopLoadMore();
    }

    class Myadapte extends BaseAdapter {
        List<ReadInfo.美女Bean> beanList;
        private ImageView imag;
        private TextView text;
        private final DisplayImageOptions options;

        public Myadapte(List<ReadInfo.美女Bean> beanList) {
            this.beanList = beanList;
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

        public void loddasmore(List<ReadInfo.美女Bean> beanLists, boolean flag) {
            for (ReadInfo.美女Bean bean : beanLists) {
                if (flag) {
                    beanList.add(0, bean);

                } else {
                    beanList.add(bean);

                }


            }


        }


        @Override
        public int getCount() {

            return beanList.size();
        }

        @Override
        public Object getItem(int position) {

            return beanList.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = convertView.inflate(MainActivity.this, R.layout.item, null);
                imag = convertView.findViewById(R.id.imag);
                text = convertView.findViewById(R.id.text);
                text.setText(beanList.get(position).getTitle());
                getimage(beanList.get(position).getImg(), imag);
            }
            return convertView;
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
