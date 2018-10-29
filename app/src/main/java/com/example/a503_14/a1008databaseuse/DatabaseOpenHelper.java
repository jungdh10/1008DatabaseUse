package com.example.a503_14.a1008databaseuse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    //생성자
    public DatabaseOpenHelper(Context context){
        //첫 번째는 Context, 두 번째는 파일명, 세 번째는 Cursor, 네 번째는 버전
        super(context, "data.db", null, 1);
    }

    //데이터베이스를 처음 사용할 때 호출되는 메소드, 테이블을 생성하고 샘플 데이터를 추가
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table tb_data("+"_id integer primary key autoincrement,"+"name text,"+"alias text);");
        db.execSQL("insert into tb_data(name, alias)"+ "values('해리포터', '해리');");
        db.execSQL("insert into tb_data(name, alias)"+ "values('론위즐리', '론');");
    }
    //버전이 변경되었을 때 호출되는 메소드, 테이블을 삭제하고 다시 생성
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table tb_data;");
        onCreate(db);
    }
}
