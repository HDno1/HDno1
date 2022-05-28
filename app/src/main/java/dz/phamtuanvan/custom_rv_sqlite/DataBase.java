package dz.phamtuanvan.custom_rv_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table StudentInfo (Id integer primary key, name text, phone text)");
        /*sqLiteDatabase.execSQL("insert into StudentInfo values (1,'Phạm Tuấn Văn','0339864774')");
        sqLiteDatabase.execSQL("insert into StudentInfo values (2,'Trần Quang Học','03203734560')");
        sqLiteDatabase.execSQL("insert into StudentInfo values (3,'Nguyễn Đăng Chiến','0359334168')");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists StudentInfo");
        onCreate(sqLiteDatabase);
    }
    public boolean deleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from StudentInfo where Id = ?",new String[]{String.valueOf(id)});
        if (c.getCount()>0){
            long result = db.delete("StudentInfo","Id=?",new String[]{String.valueOf(id)});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }
    public boolean insertData(String id, String name, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id",id);
        values.put("name",name);
        values.put("phone",phone);

        long result = db.insert("StudentInfo",null,values);

        if (result == -1) return false;
        else
            return true;
    }
    public ArrayList<Student> getaAllData(){
        ArrayList<Student> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM StudentInfo order by name ASC",null);

        while (c.moveToNext()){
            Integer id = c.getInt(0);
            String name = c.getString(1);
            String phone = c.getString(2);

            Student student = new Student(id,name,phone);

            arrayList.add(student);
        }

        return arrayList;

    }
}
