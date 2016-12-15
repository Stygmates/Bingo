package com.bingo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tit-a on 12/12/2016.
 */

public class Joueur implements Parcelable {
    private String pseudo;
    private int limcoin;

    protected Joueur(Parcel in) {
        pseudo = in.readString();
        limcoin = in.readInt();
    }

    public Joueur(String pseudo)
    {
        this.pseudo = pseudo;
        this.limcoin = 500;
    }

    public int getLimcoin() {
        return limcoin;
    }

    public void setLimcoin(int limcoin) {
        this.limcoin = limcoin;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public static final Creator<Joueur> CREATOR = new Creator<Joueur>() {
        @Override
        public Joueur createFromParcel(Parcel in) {
            return new Joueur(in);
        }

        @Override
        public Joueur[] newArray(int size) {
            return new Joueur[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pseudo);
        parcel.writeInt(limcoin);
    }
}
