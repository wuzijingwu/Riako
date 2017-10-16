package test.bwie.com.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import test.bwie.com.myview.view.OnUnlockListener;
import test.bwie.com.myview.view.SdeLock;

public class MainActivity extends AppCompatActivity {
    private SdeLock slideLock;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slideLock = (SdeLock) findViewById(R.id.slideLock);
        imageView = (ImageView) findViewById(R.id.imageView);
        slideLock.setOnUnlockListener(new OnUnlockListener() {
            @Override
            public void setUnlock(boolean unlock) {
                if (unlock) {
                    slideLock.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                }


            }
        });


    }
}
