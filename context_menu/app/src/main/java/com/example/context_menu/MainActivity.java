package com.example.context_menu;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    String[] phone = new String[] {
            "美特伦", "微步", "群索", "百能达", "微掌",
            "多为", "沃特沃德", "酷赛", "康佳", "旗文"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, phone);
        setListAdapter(adapter);
    }
}
