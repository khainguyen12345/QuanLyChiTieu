package com.example.quanlychitieu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Date;

public class myDB extends SQLiteOpenHelper {
    public myDB(@Nullable Context context) {
        super(context, "login.db", null, 1);
    }
    // query not return result
    public void queryData(String sqlQuery) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlQuery);
    }
    // query return result
    public Cursor getData(String sqlQuery , String whereClause) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sqlQuery ,new String[] {whereClause});
    }
    @Override
    public void onCreate(SQLiteDatabase mydb) {
           mydb.execSQL("create table if not exists users(userId int primary key autoincrement , username varchar(20) unique, password varchar(20) , email vachar(20) , number varchar(20) )");
           mydb.execSQL("create table if not exists userInfor(userInfor userId int , ngaythang varchar(20) , note varchar(20) , money int , foreign key (userId) references users(userId))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase mydb, int i, int i1) {
           mydb.execSQL("drop table if exists users");
           mydb.execSQL("drop table if exists userInfor");
    }
    public Boolean insertData( String username , String password , String number , String email) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("username" , username);
        data.put("password" , password);
        data.put("number" , number);
        data.put("email" , email);
        long result = db.insert("users" , null,data);
        if(result == -1) {
            return false;
        }else
            return true;
    }
    public Boolean insertUserInfor(String date , String ghichu , int tienChi ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("ngaythang" , date);
        data.put("note" , ghichu);
        data.put("money" , tienChi);
        long result = db.insert("userInfor" ,null , data);
        if(result == -1) {
            return false;
        }else{
            Log.d("error", "userInfor: " + date +" " +  ghichu +" " + tienChi);
            return true;
        }
    }
    public Boolean checkUser(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?" , new String[] {username}) ;
        if(cursor.getCount() > 0) {
            return false;
        }else{
            return true;
        }
    }
    public Boolean checkUsernamePassword ( String username , String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ? and password = ?" , new String[] {username , password});
        if(cursor.getCount() > 0) {
            return true;
        }else{
            return false;
        }
    }

    
}
