package com.example.talek.project2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final String name = "movie.sqlite3";
    private static final int version = 2;


    public MovieDBHelper(Context ctx) {
        super(ctx, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE movie (" +
                "_id integer primary key autoincrement," +
                "branch text not null," +
                "fast7 int not null," +
                "thor int not null," +
                "taken int not null);";
        db.execSQL(sql);
    }

  //  SELECT SUM(credit) cr, SUM(value*credit) gp From course;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS movie;";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
