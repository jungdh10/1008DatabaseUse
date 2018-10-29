package com.example.a503_14.a1008databaseuse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAdapterUse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter_use);

        //SimpleAdapter를 이용해서 출력할 데이터 생성
        //List<Map<String, Object>>구조
        List<Map<String, String>> list=new ArrayList<>();
        Map<String, String> map=new HashMap<>();
        map.put("name", "해리포터");
        map.put("alias", "해리");
        list.add(map);

        map=new HashMap<>();
        map.put("name", "론위즐리");
        map.put("alias", "론");
        list.add(map);

        map=new HashMap<>();
        map.put("name", "말포이");
        map.put("alias", "말");
        list.add(map);

        //데이터 출력을 위한 Adapter 생성
        //첫 번째는 Context, 두 번째는 데이터, 세 번째는 셀의 출력 모양, 네 번째는 출력할 데이터의 키 배열, 다섯 번째는 출력할 셀 안의 id
        SimpleAdapter simpleAdapter=new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,
                new String[]{"name", "alias"}, new int[]{android.R.id.text1, android.R.id.text2});

        //ListView에 adpater를 연결해서 출력
        ListView listView =(ListView)findViewById(R.id.listView);
        listView.setAdapter(simpleAdapter);

    }
}
