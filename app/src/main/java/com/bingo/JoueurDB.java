package com.bingo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntegerRes;
import android.util.Log;
import android.widget.Toast;

import static android.os.Build.ID;

/**
 * Created by tit-a on 16/12/2016.
 */

public class JoueurDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_JOUEUR);
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
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOUEUR);
    }

    public String toString()
    {
        String dbString = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_PSEUDO + ", " + COLUMN_LIMCOIN + " FROM " + TABLE_JOUEUR + " ORDER BY " + COLUMN_LIMCOIN;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        String[]tableauNoms = cursor.getColumnNames();
        for(int i = 0; i < tableauNoms.length; i++) {
            dbString +=tableauNoms[i] + " ";
        }
        dbString += "\n";
        while (!cursor.isAfterLast())
        {
            if((cursor.getString(cursor.getColumnIndex(COLUMN_PSEUDO))!= null))
            {
                dbString += cursor.getString(1) + " " + cursor.getString(2)+ "\n";
            }
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return dbString;
    }
}
