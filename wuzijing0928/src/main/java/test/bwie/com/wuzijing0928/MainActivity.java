package test.bwie.com.wuzijing0928;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "0123456789";
                StringBuilder sb = new StringBuilder(4);
                for (int i = 0; i < 4; i++) {
                    char ch = str.charAt(new Random().nextInt(str.length()));
                    sb.append(ch);
                }
                sb.toString();
                textView.setText(sb.toString());
            }
        });

    }
}
