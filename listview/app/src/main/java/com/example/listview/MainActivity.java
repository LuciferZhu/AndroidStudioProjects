package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "listview";
    private ListView lv = null;
    MyAdapter myAdapter = new MyAdapter();
    private final String[] itemName = new String[] {
            "美特伦", "微步", "群索", "百能达", "微掌",
            "多为", "沃特沃德", "酷赛", "康佳", "旗文",
            "腾瑞丰", "阿龙", "平板", "鼎勤", "创元",
            "江元", "隆科", "沸石", "华卓", "TCL",
            "金讯宇", "鸿祥源", "九州华科", "金泰谊", "创维"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // 设置适配器
        lv.setAdapter(myAdapter);
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.listview);
    }

    // 自定义 Adapter: 可以使用在多控件的匹配显示上 自定义一个类
    class MyAdapter extends BaseAdapter {

        // 列表项的总和
        @Override
        public int getCount() {
            Log.d(TAG, "itemName.length = "+itemName.length);
            return itemName.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        // 每加载一个列表项就会调用此方法
        // @convertView: 可复用的列表项（离开屏幕的列表项）
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Log.d(TAG, "occure a new item_ui, position=" + position);

            // 布局填充器
            // 参数1 ：即将填充进来的布局
            if (convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.item_ui, null);

            // 修改条目的名字
            TextView internTV = convertView.findViewById(R.id.text_item);
            internTV.setText(itemName[position].toString());

            // 修改头像

            return convertView;
        }
    }
}
