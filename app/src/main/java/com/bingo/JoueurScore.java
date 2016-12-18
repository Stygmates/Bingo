package com.bingo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tit-a on 12/12/2016.
 */

public class JoueurScore implements Parcelable {
    private String pseudo;
    private int niveau;

    protected JoueurScore(Parcel in) {
        pseudo = in.readString();
        niveau = in.readInt();
    }

    public JoueurScore(String pseudo)
    {
        this.pseudo = pseudo;
        this.niveau = 1;
    }

    public JoueurScore(String pseudo, int niveau)
    {
        this.pseudo = pseudo;
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public static final Creator<JoueurScore> CREATOR = new Creator<JoueurScore>() {
        @Override
        public JoueurScore createFromParcel(Parcel in) {
            return new JoueurScore(in);
        }

        @Override
        public JoueurScore[] newArray(int size) {
            return new JoueurScore[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pseudo);
        parcel.writeInt(niveau);
    }
}
