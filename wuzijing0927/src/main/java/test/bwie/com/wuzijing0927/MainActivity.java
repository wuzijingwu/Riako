package test.bwie.com.wuzijing0927;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private TextView text;
    private StringBuffer buffer;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView) findViewById(R.id.webview);
        text = (TextView) findViewById(R.id.text);

        tv = (TextView) findViewById(R.id.logo);

        buffer = new StringBuffer("http://www.baidu八维教育.com");


        buffer.delete(buffer.indexOf("八"), buffer.lastIndexOf("."));

        SpannableString span = new SpannableString(buffer.toString());
        span.setSpan(new UnderlineSpan(),0,span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(span);


        SpannableString s = new SpannableString("八维—的—教育");
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0,0,50,50);
        s.setSpan(new ImageSpan(drawable),3,4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(s);
    }

    public void dianji(View view){
        String s = new String(buffer);
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.delete(13, 20);
//        System.out.println(stringBuffer);


        webview.loadUrl(s);


    }



}
