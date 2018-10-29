package com.example.a503_14.a1008databaseuse;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //layout에서 필요한 데이터 찾아오기
        Button insert =(Button)findViewById(R.id.insert);
        Button select=(Button)findViewById(R.id.select);
        Button update=(Button)findViewById(R.id.update);
        Button delete=(Button)findViewById(R.id.delete);

        final EditText name=(EditText)findViewById(R.id.name);
        final EditText price=(EditText)findViewById(R.id.price);
        final TextView id=(TextView)findViewById(R.id._id);

        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String inputname =name.getText().toString();
                String inputprice=price.getText().toString();
                //데이터베이스 연결
                DBOpenHelper dbHelper= new DBOpenHelper(MainActivity.this);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.execSQL("insert into product(name, price)"+" values(\'"+ inputname + "\'," + inputprice +");");
                dbHelper.close();
                id.setText("삽입성공");
                name.setText("");
                price.setText("");

            }
        });

        select.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String inputname =name.getText().toString();
                if(inputname.trim().length()==0){
                    Toast.makeText(MainActivity.this, "이름을 입력하세요", Toast.LENGTH_LONG).show();
                    return;
                }
                DBOpenHelper dbOpenHelper=new DBOpenHelper(MainActivity.this);
                SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
                //select명령어를 사용해서 쿼리를 실행하려면 rawQuery, select가 아니면 execSQL사용
                //쿼리의 결과는 cursor 객체로 반환됨
                //cursor는 행(row)을 참조하기 때문에 cursor의 위치를 바꿔주는 메소드들 사용 move.ToNext사용(cursor를 다음 행으로 이동)
                Cursor cursor=db.rawQuery("select * from product where name='" + inputname +"';", null);
                if(cursor.moveToNext()){
                    //Cursor가 참조하고 있는 DB 테이블의 행(Row) 데이터를 얻어오기
                    id.setText(cursor.getInt(0)+ "");
                    name.setText(cursor.getString(1));
                    price.setText(cursor.getInt(2)+"");
                }else{
                    id.setText("조회된 데이터가 없습니다.");
                }
                dbOpenHelper.close();
            }
        });

        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String inputname=name.getText().toString();
                String inputprice=price.getText().toString();
                DBOpenHelper dbOpenHelper=new DBOpenHelper(MainActivity.this);
                SQLiteDatabase db=dbOpenHelper.getWritableDatabase();

                //execSQL 사용했을 경우
                db.execSQL("update product set price="+ inputprice +" where name='"+inputname+"';");

                //ContentValues 사용했을 경우
                //ContentValues values=new ContentValues();
                //values.put("price", Integer.parseInt(inputprice));
                //db.update("product", values, "name='"+inputname+"'", null);

                dbOpenHelper.close();
                Toast.makeText(MainActivity.this, "가격이" + inputprice+ "(으)로 변경되었습니다", Toast.LENGTH_LONG).show();
            }

        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBOpenHelper dbOpenHelper=new DBOpenHelper(MainActivity.this);
                SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
                String inputname=name.getText().toString();

                //execSQL 사용했을 경우
                //db.execSQL("delete from product where name='"+ inputname +"';");

                db.delete("product", "name='" + inputname + "'", null);

                Toast.makeText(MainActivity.this, "삭제 성공", Toast.LENGTH_LONG).show();
                dbOpenHelper.close();

            }
        });

    }
}
