package test.bwie.com.wuzijing1013;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private Button tinajia;
    private Button shanchu;
    private Button list1;
    private Button grid1;
    private Button flow;
    private ArrayList<String> strings;
    private Myadapter adapter;
    private int count=0;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        tinajia = (Button) findViewById(R.id.tinajia);
        shanchu = (Button) findViewById(R.id.shanchu);
        list1 = (Button) findViewById(R.id.list1);
        grid1 = (Button) findViewById(R.id.grid1);
        flow = (Button) findViewById(R.id.flow);
        strings = new ArrayList<>();

        for (int i=0;i<20;i++){

            strings.add("count"+i);

        }
        linearLayoutManager = new LinearLayoutManager(this);

        recycler.setLayoutManager(linearLayoutManager);

        gridLayoutManager = new GridLayoutManager(this, 3);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);


        adapter = new Myadapter();
        recycler.setAdapter(adapter);

        tinajia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (strings.size() > 4) {
                    strings.add(4, "我是加的");
                    adapter.notifyItemInserted(4);
//                    adapter.notifyItemRangeChanged(2, 2);//刷新从position以后的2条数据
                }

            }
        });

        shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(strings.size()>6){
                    strings.remove(6);
                    adapter.notifyItemRemoved(6);
                }
            }
        });

        grid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    recycler.setLayoutManager(gridLayoutManager);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycler.setLayoutManager(linearLayoutManager);
            }
        });
        flow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycler.setLayoutManager(staggeredGridLayoutManager);

            }
        });






    }

    class Myadapter extends RecyclerView.Adapter {


        private myViewHolder myviewholder;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
            return new myViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            myviewholder = (myViewHolder) holder;
            myviewholder.text.setText(strings.get(position));


        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        public class myViewHolder extends RecyclerView.ViewHolder {
            private final TextView text;

            public myViewHolder(View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.text);


            }
        }


    }
}
