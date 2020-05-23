package com.example.firstprojectever.Storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;

public class InventoryDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "userdata.db";
    public static final int DATABASE_VERSION = 1;
    public static final String USER_INDEX_ID = "_id";
    public static final String BOOKING_INDEX_ID = USER_INDEX_ID;
    public static final String USER_TABLE = "user";
    public static final String BOOKING_TABLE = "booking";
    public static final String USER_ID_STRING = "userid";
    public static final String USER_BOOKING_ID = "bookingid";
    public static final String USER_BOOK_ID = "user_bookid";
    public static final String DATE_BOOK = "bookingdate";

    public InventoryDBHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(USER_INDEX_ID Integer primary key autoincrement, userid text not null,name text not null," +
                "password text not null,pnumb text not null,bdate text not null,gender text not null)");
        db.execSQL("CREATE TABLE booking(BOOKING_INDEX_ID Integer primary key AUTOINCREMENT,user_bookid text not null," +
                "bookingid text not null,kosname text not null," +
                "username text not null,bookingdate text not null,kosfacility text not null," +
                "kosprice text not null,koslong text not null,koslat text not null,kosaddress text not null)");
        db.execSQL("CREATE TABLE consid(CONS_INDEX_ID Integer primary key AUTOINCREMENT,cons_bookid text not null" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS booking");
        db.execSQL("DROP TABLE IF EXISTS consid");
    }

    public boolean consID(String bookid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("cons_bookid",bookid);
        long insert = db.insert("consid",null,cv);
        if(insert == -1 )return false;
        else return true;
    }

    public boolean insertBook (String userid,String bookid,String kosname,String username,String facility,String kosprice,String kosaddress,
                               String koslong,String koslat,String bookdate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_bookid",userid);
        cv.put("bookingid",bookid);
        cv.put("kosname",kosname);
        cv.put("username",username);
        cv.put("bookingdate",bookdate);
        cv.put("kosfacility",facility);
        cv.put("kosprice",kosprice);
        cv.put("koslong",koslong);
        cv.put("koslat",koslat);
        cv.put("kosaddress",kosaddress);
        long insert = db.insert("booking",null,cv);
        if(insert == -1)return false;
        else return true;
    }

    public boolean insertUser (String userid,String name,String pwd,String pnumb,String bdate,String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userid",userid);
        cv.put("name",name);
        cv.put("password",pwd);
        cv.put("pnumb",pnumb);
        cv.put("bdate",bdate);
        cv.put("gender",gender);
        long insert = db.insert("user",null,cv);
        if(insert == -1)return false;
        else return true;
    }

    public String getLastBookingId(){
        String lastId = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM booking ORDER BY bookingid DESC LIMIT 1",null);
        if(cursor.moveToFirst()){
            lastId = cursor.getString(cursor.getColumnIndex(USER_BOOKING_ID));
        }
        cursor.close();
        return lastId;
    }


    public Integer deleteBookingsRow(String bookingid){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("booking","bookingid=?",new String[]{bookingid});
    }

    public long getUserCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,"user");
        db.close();
        return count;
    }

    public long getBookingCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,"booking");
        db.close();
        return count;
    }
    public long getFixedBook(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,"consid");
        db.close();
        return count;
    }

    public String getCurrentId(String idname,String pwd ){
        String curr_id = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT userid FROM user WHERE name=? and password=? ",new String[]{idname,pwd});
        if(cursor != null){
            cursor.moveToFirst();
        }
        do{
            curr_id = cursor.getString(cursor.getColumnIndex(USER_ID_STRING));
        }while(cursor.moveToNext());

        return curr_id;
    }

    public List<data_booking> dataBookings(String userid){
        List<data_booking> dataArray = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM booking WHERE user_bookid=?",new String[]{userid});
        if(c.moveToFirst()){
            do{
                data_booking dbk = new data_booking();
                dbk.setUserId(c.getString(1));
                dbk.setBookingId(c.getString(2));
                dbk.setKosname(c.getString(3));
                dbk.setUsername(c.getString(4));
                dbk.setBookdate(c.getString(5));
                dbk.setFacility(c.getString(6));
                dbk.setKosprice(c.getString(7));
                dbk.setKoslong(c.getString(8));
                dbk.setKoslat(c.getString(9));
                dbk.setKosaddress(c.getString(10));
                dataArray.add(dbk);
            }while(c.moveToNext());
        }
        db.close();
        return dataArray;
    }




    public Boolean validate_Username(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE name=?",new String[]{name});
        if(cursor.getCount()>0) {
            return false;
        }else{
            return true;
        }
    }

    public Boolean login_Validate(String name,String pwd){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE name=? and password=?",new String[]{name,pwd});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}
