package test.bwie.com.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ExpandableListView) findViewById(R.id.listview);
        MyAdpater myAdpater = new MyAdpater(this);
        listview.setAdapter(myAdpater);

    }
}
