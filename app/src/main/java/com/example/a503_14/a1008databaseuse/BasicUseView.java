package com.example.a503_14.a1008databaseuse;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BasicUseView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_use_view);

        //출력할 데이터 배열이나 List 생성
        String [] harrypotter ={"해리포터", "론위즐리", "헤르미온느", "도비", "네빌", "말포이"};

        //위의 데이터를 가지고 Adapter 생성
        //첫 번째는 Context, 두 번째는 출력할 셀의 모양: 제공되는 모양 사용, 세 번째는 출력할 데이터
        //List 이용
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, harrypotter);
        //ListView에 위의 Adapter연결
        ListView listView =(ListView)findViewById(R.id.listView);

        //배열 이용
        ArrayAdapter adaper1 =ArrayAdapter.createFromResource(this, R.array.harrypotter, android.R.layout.simple_list_item_1);

        listView.setAdapter(adapter);

        //ListView의 경계선을 만드는 코드를 추가
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setDivider(new ColorDrawable(Color.CYAN));
        listView.setDividerHeight(3);

        //ListView에서 아이템을 선택했을 때 처리하는 코드를 추가
        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            //첫 번째 매개변수는 이벤트가 발생한 객체, 두 번째 매개변수는 항목 뷰 -선택할 항목의 뷰
            //세 번째 매개변수는 클릭한 항목 뷰의 인덱스, 네 번째 매개변수는 항목 뷰의 아이디
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BasicUseView.this, (position+1)+"번째 선택", Toast.LENGTH_LONG).show();
            }
        });

    }
}
