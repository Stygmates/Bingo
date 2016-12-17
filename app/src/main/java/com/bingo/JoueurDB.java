package com.bingo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.os.Build.ID;

/**
 * Created by tit-a on 16/12/2016.
 */

public class JoueurDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "JOUEUR.db";
    private static final String TABLE_JOUEUR = "joueur";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PSEUDO = "pseudo";
    private static final String COLUMN_LIMCOIN = "limcoin ";

    public JoueurDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_JOUEUR + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PSEUDO + " TEXT, " +
                COLUMN_LIMCOIN + " INTEGER" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_JOUEUR);
        onCreate(sqLiteDatabase);
    }

    public void addJoueur(Joueur joueur)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PSEUDO, joueur.getPseudo());
        values.put(COLUMN_LIMCOIN, joueur.getLimcoin());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_JOUEUR, null, values);
        db.close();
    }

    public void deleteJoueur(String pseudo)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_JOUEUR + " WHERE " + COLUMN_PSEUDO + "=\"" + pseudo + "\";");
    }

    public void updateJoueur(String pseudo, int limcoin)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE TABLE " + TABLE_JOUEUR + " SET " + COLUMN_LIMCOIN + "WHERE " + COLUMN_PSEUDO + " =\"" + pseudo + "\"");
    }

    public String toString()
    {
        String dbString = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_JOUEUR + " ORDER BY " + COLUMN_LIMCOIN;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            if((cursor.getString(cursor.getColumnIndex(COLUMN_PSEUDO))!= null))
            {
                dbString += cursor.getString(cursor.getColumnIndex(COLUMN_PSEUDO)) + " ";
            }
        }
        sqLiteDatabase.close();
        return dbString;
    }
}
