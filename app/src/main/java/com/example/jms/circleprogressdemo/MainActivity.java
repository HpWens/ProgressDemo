package com.example.jms.circleprogressdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        mList.add("Bar-circleProgress");
        mList.add("Rise-circleProgress");
        mList.add("Arc-circleProgress");

        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Object getItem(int i) {
                return mList.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view = View.inflate(MainActivity.this, R.layout.item, null);
                TextView tv = (TextView) view.findViewById(R.id.tv);
                tv.setText(mList.get(i));
                return view;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                switch (i) {
                    case 0:
                        intent = new Intent(MainActivity.this, BarActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, RiseActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, ArcActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });

    }
}
