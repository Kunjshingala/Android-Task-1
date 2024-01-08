package com.example.finaltask1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.finaltask1.DataModel.DataModal;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Users";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT,name TEXT,contact TEXT,gender TEXT,city TEXT,country TEXT,language TEXT,secondaryEmail TEXT,favoriteCity TEXT)";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS register");
        onCreate(db);
    }

    public boolean addUser(String email, String name, String contact, String gender, String city, String country, String language, String secondaryEmail, String favoriteCity) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("gender", gender);
        contentValues.put("city", city);
        contentValues.put("country", country);
        contentValues.put("language", language);
        contentValues.put("secondaryEmail", secondaryEmail);
        contentValues.put("favoriteCity", favoriteCity);

        long isRegister = sqLiteDatabase.insert("user", null, contentValues);
        sqLiteDatabase.close();

        if (isRegister > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<DataModal> getAllUserDetailsHelper() {

        ArrayList<DataModal> allUser = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user", null);

        if (cursor.moveToFirst()) {
            do {

                String email = cursor.getString(1);
                String name = cursor.getString(2);
                String contact = cursor.getString(3);
                String gender = cursor.getString(4);
                String city = cursor.getString(5);
                String country = cursor.getString(6);
                String language = cursor.getString(7);
                String secondaryEmail = cursor.getString(8);
                String favoriteCity = cursor.getString(9);

                DataModal dataModal = new DataModal(email, name, contact, gender, city, country, language, secondaryEmail, favoriteCity);

                allUser.add(dataModal);

            } while (cursor.moveToNext());
        }
        return allUser;
    }

    public ArrayList<String> getSpecificUserHelper(String email) {

        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String get_user_Query = "SELECT * FROM user WHERE email='" + email + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(get_user_Query, null);

        if (cursor.moveToFirst()) {
            String userId = cursor.getString(0);
            String userEmail = cursor.getString(1);
            String userName = cursor.getString(2);
            String userContactNumber = cursor.getString(3);
            String userGender = cursor.getString(4);
            String userCity = cursor.getString(5);
            String userCountry = cursor.getString(6);
            String userLanguage = cursor.getString(7);
            String userSecondryEmail = cursor.getString(8);
            String userFavCity = cursor.getString(9);

            arrayList.add(userId);
            arrayList.add(userEmail);
            arrayList.add(userName);
            arrayList.add(userContactNumber);
            arrayList.add(userGender);
            arrayList.add(userCity);
            arrayList.add(userCountry);
            arrayList.add(userLanguage);
            arrayList.add(userSecondryEmail);
            arrayList.add(userFavCity);
        }
        return arrayList;
    }

    public boolean updateUserHelper(String email, String name, String contact, String gender, String city, String country, String language, String secondaryEmail, String favoriteCity) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("gender", gender);
        contentValues.put("city", city);
        contentValues.put("country", country);
        contentValues.put("language", language);
        contentValues.put("secondaryEmail", secondaryEmail);
        contentValues.put("favoriteCity", favoriteCity);

        int isUpdate = sqLiteDatabase.update("user", contentValues, "email=?", new String[]{email});

        if (isUpdate > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUserHelper(String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int isDelete = sqLiteDatabase.delete("user", "email=?", new String[]{email});
        if (isDelete > 0) {
            return true;
        } else {
            return false;
        }
    }
}
