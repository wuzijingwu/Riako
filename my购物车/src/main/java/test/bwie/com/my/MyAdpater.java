package test.bwie.com.my;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dell on 2017/9/28.
 */

public class MyAdpater extends BaseExpandableListAdapter {
    private Context context;
    private String[] group;
    private HashMap<Integer, Boolean> groupList;
    private ArrayList<HashMap<Integer, Boolean>> childList;
    private ArrayList<ArrayList<Bean>> dataList;
    private String[][] child;
    private GroupViewHolder holder;


    public MyAdpater(Context context) {
        this.context = context;
    }

    public void initData() {
        child = new String[5][];
        childList = new ArrayList<>();
        dataList = new ArrayList<>();


        group = new String[5];
        groupList = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            group[i] = "商家" + i;
            groupList.put(i, false);

            String[] strings = new String[3];
            HashMap<Integer, Boolean> map = new HashMap<>();
            ArrayList<Bean> been = new ArrayList<>();
            for (int y = 0; y < 3; y++) {
                strings[y] = "商家" + i + "商品" + y;
                map.put(y, false);
                Bean bean = new Bean("100", "1");
                been.add(bean);
            }

            child[i] = strings;
            childList.add(map);
            dataList.add(been);
        }


    }

    @Override
    public int getGroupCount() {
        return group.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return child[i].length;
    }

    @Override
    public Object getGroup(int i) {
        return group[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return child[i1];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = View.inflate(context, R.layout.group_item, null);
            holder = new GroupViewHolder();
            holder.tv = view.findViewById(R.id.group_tv);
            holder.ck = view.findViewById(R.id.group_ck);
            view.setTag(holder);
        } else {
            holder = (GroupViewHolder) view.getTag();
        }
        holder.tv.setText(group[i]);
        holder.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupList.put(i, !groupList.get(i));
                setChildCheckAll();
                String shopPrice = getShopPrice();
                boolean b1 = selectAll();
                adapterData.Data(view, shopPrice, b1);
            }


        });
        holder.ck.setChecked(groupList.get(i));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public class GroupViewHolder {
        TextView tv;
        CheckBox ck;
    }

    //设置二级类列表的选中状态，根据一级类列表的选中状态来改变
    private void setChildCheckAll() {
        for (int i = 0; i < childList.size(); i++) {
            HashMap<Integer, Boolean> integerBooleanHashMap = childList.get(i);
            Set<Map.Entry<Integer, Boolean>> entries = integerBooleanHashMap.entrySet();
            for (Map.Entry<Integer, Boolean> entry : entries) {
                entry.setValue(groupList.get(i));
            }

        }


    }

    public String getShopPrice() {
        int price = 0;
        int number = 0;
        for (int y = 0; y < childList.size(); y++) {
            HashMap<Integer, Boolean> integerBooleanHashMap = childList.get(y);
            Set<Map.Entry<Integer, Boolean>> entries = integerBooleanHashMap.entrySet();
            for (Map.Entry<Integer, Boolean> entry : entries) {
                if (entry.getValue()) {
                    Bean bean = dataList.get(y).get(entry.getKey());
                    price += Integer.parseInt(bean.getPrice()) * Integer.parseInt(bean.getNumber());
                    number += Integer.parseInt(bean.getPrice());
                }
            }

        }
        return price + "," + number;

    }

    public boolean selectAll() {
        boolean isChecked = true;
        for (int p = 0; p < childList.size(); p++) {
            HashMap<Integer, Boolean> integerBooleanHashMap = childList.get(p);
            Set<Map.Entry<Integer, Boolean>> entries = integerBooleanHashMap.entrySet();
            for (Map.Entry<Integer, Boolean> entry : entries) {
                if (!entry.getValue()) {
                    isChecked = false;
                }


            }


        }
        return isChecked;

    }

    private AdapterData adapterData;

    private interface AdapterData {
        void Data(View v, String str, boolean b);

    }

    public void getAdapterData(AdapterData adapterData) {
        this.adapterData = adapterData;


    }


}
