package com.bingo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tit-a on 16/12/2016.
 */

public class JoueurScoreDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "JOUEURSCORE.db";
    private static final String TABLE_JOUEURSCORE = "joueurscore";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PSEUDO = "pseudo";
    private static final String COLUMN_NIVEAU = "niveau";

    public JoueurScoreDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_JOUEURSCORE);
        String query = "CREATE TABLE " + TABLE_JOUEURSCORE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PSEUDO + " TEXT, " +
                COLUMN_NIVEAU + " INTEGER" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_JOUEURSCORE);
        onCreate(sqLiteDatabase);
    }


    public void addJoueur(Joueur joueur, int niveau)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PSEUDO, joueur.getPseudo());
        values.put(COLUMN_NIVEAU, niveau);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_JOUEURSCORE, null, values);
        db.close();
    }

    public void deleteJoueur(String pseudo)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_JOUEURSCORE + " WHERE " + COLUMN_PSEUDO + "=\"" + pseudo + "\";");
    }

    public void updateJoueur(String pseudo, int niveau)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE TABLE " + TABLE_JOUEURSCORE + " SET " + COLUMN_NIVEAU + "WHERE " + COLUMN_PSEUDO + " =\"" + pseudo + "\"");
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOUEUR);
    }

    public String toString()
    {
        String dbString = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_PSEUDO + ", " + COLUMN_NIVEAU + " FROM " + TABLE_JOUEURSCORE + " ORDER BY " + COLUMN_NIVEAU;
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
