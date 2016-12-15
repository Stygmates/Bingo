package com.bingo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by tit-a on 12/12/2016.
 */

public class Grille implements Parcelable {
    private int []grille;

    public Grille()
    {
        grille = new int[9];
        Random random = new Random();
        for(int i = 0; i < 9; i++)
        {
            grille[i] = random.nextInt(100);
        }
    }
    public int[] getGrille() {
        return grille;
    }

    public void setGrille(int[] grille) {
        this.grille = grille;
    }

    public void setGrille(int position, int valeur)
    {
        this.grille[position] = valeur;
    }

    protected Grille(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Grille> CREATOR = new Creator<Grille>() {
        @Override
        public Grille createFromParcel(Parcel in) {
            return new Grille(in);
        }

        @Override
        public Grille[] newArray(int size) {
            return new Grille[size];
        }
    };
}
