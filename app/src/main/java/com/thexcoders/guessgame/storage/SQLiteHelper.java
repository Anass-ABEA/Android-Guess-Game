package com.thexcoders.guessgame.storage;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.thexcoders.guessgame.model.Score;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SCORE = "score";
    private static final String DATE = "date";
    private static final String TABLE = "scores";


    public SQLiteHelper(Context context) {
        super(context, "Score_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_Q =
                String.format(
                        "CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s VARCHAR(20), %s INTEGER, %s DATE);",
                        TABLE, ID, NAME, SCORE, DATE
                );
        sqLiteDatabase
                .execSQL(SQL_Q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("DROP %s IF EXISTS scores;", TABLE));
        onCreate(sqLiteDatabase);
    }

    public boolean saveScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, score.getPlayerName());
        values.put(SCORE, score.getTries());
        values.put(DATE, score.getDate());
        long res = db.insert(TABLE, null, values);
        db.close();
        return res != -1;
    }

    public List<Score> getScores() {
        List<Score> res = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s;", TABLE), null);
        while (!cursor.isAfterLast()) {
            res.add(
                    new Score(
                            cursor.getString(cursor.getColumnIndexOrThrow(NAME)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(SCORE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(DATE))
                    )
            );
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return res;
    }

}
