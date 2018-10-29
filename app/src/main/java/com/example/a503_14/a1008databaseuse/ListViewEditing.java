package com.example.a503_14.a1008databaseuse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewEditing extends AppCompatActivity {
    //ListView 출력을 위한 변수
    private List<String> list;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_editing);

        //ListView 출력
        list=new ArrayList<>();
        list.add("Encapsulation(캡슐화)");
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, list);
        listView=(ListView)findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        Button insert=(Button)findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText editText=(EditText)findViewById(R.id.word);
                //입력한 내용의 좌우공백을 제거하고 가져오기
                String word=editText.getText().toString().trim();
                if(word.length()==0){
                    Toast.makeText(ListViewEditing.this, "삽입할 단어를 입력하세요!", Toast.LENGTH_LONG).show();
                    return;
                }
                list.add(word);
                //리스트 뷰 다시 출력
                adapter.notifyDataSetChanged();
                editText.setText("");
                Toast.makeText(ListViewEditing.this, "삽입 성공", Toast.LENGTH_LONG).show();
            }
        });

        Button delete=(Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*
                //선택된 행 번호 가져오기
                int pos = listView.getCheckedItemPosition();
                //선택된 행 번호가 List 안의 행 번호인지 확인해서 아니면 리턴
                if (pos < 0 || pos >= list.size()) {
                    Toast.makeText(ListViewEditing.this, "선택하고 삭제를 누르세요!", Toast.LENGTH_LONG).show();
                    return;
                }
                //데이터 삭제
                list.remove(pos);
                */

                //선택 여부를 배열로 받아오기
                SparseBooleanArray sb=listView.getCheckedItemPositions();
                //뒤에서부터 삭제를 수행해야 합니다.(앞에서부터 순서대로 수행하면 인덱스가 하나씩 앞으로 가야하니까)
                //삭제할 때는 뒤에서부터! 기억하기
                /*
                for(int i=listView.getCount()-1;i>=0;i=i-1){
                    if(sb.get(i)){
                        list.remove(i);
                    }
                }*/
                //위 코드와 동일
                for(int i=0; i<listView.getCount();i=i+1){
                    if(sb.get(listView.getCount()-(i+1))){
                        list.remove(listView.getCount()-(i+1));
                    }
                }

                //선택된 것 해제
                listView.clearChoices();
                //어댑터와 연결된 원본 데이터가 변경되면, notifyDataSetChanged 메서드를 호출하여 원본이 변경되었다고 어댑터뷰에 알려주어 화면이 다시 그려지도록 해야gka
                adapter.notifyDataSetChanged();
                Toast.makeText(ListViewEditing.this, "삭제 성공", Toast.LENGTH_LONG).show();

            }
        });

    }
}
