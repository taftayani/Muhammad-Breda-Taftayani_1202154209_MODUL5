package com.example.breda.sqlliteandsharedpreference;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

/**
 * Created by Breda on 3/24/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String Db_name= "modul5";
    private static final String tb_name= "praktikum_5";

    //colomun table
    private static final String key_ID= "id";
    private static final String key_name= "name";
    private static final String key_desc= "desc";
    private static final String key_priority= "priority";

    public DatabaseHandler(Context context) {
        super(context, Db_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creat_table = "CREATE TABLE " + tb_name + "("
                + key_ID + " INTEGER PRIMARY KEY," + key_name + " TEXT,"
                + key_desc+ " TEXT, " +key_priority + " TEXT)";
        sqLiteDatabase.execSQL(creat_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tb_name);

        onCreate(sqLiteDatabase);
    }

    public void insert(sqlModel model){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(key_name,model.getName());
        contentValues.put(key_desc,model.getDesc());
        contentValues.put(key_priority,model.getPriority());

        sqLiteDatabase.insert(tb_name,null,contentValues);
        sqLiteDatabase.close();
    }

    public LinkedList<sqlModel> getModel(){
        LinkedList<sqlModel> listName = new LinkedList<sqlModel>();
        String query= "SELECT * FROM "+tb_name;

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);

        if (cursor.moveToFirst()){
           do {
                sqlModel sql=new sqlModel();
                sql.setId(Integer.valueOf(cursor.getString(0)));
                sql.setName(cursor.getString(1));
                sql.setDesc(cursor.getString(2));
                sql.setPriority(cursor.getString(3));
                listName.add(sql);
            } while (cursor.moveToNext());
        }
        return listName;
    }
}
