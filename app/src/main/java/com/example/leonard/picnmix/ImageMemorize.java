package com.example.leonard.picnmix;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Leonard on 29/06/2015.
 */
public class ImageMemorize implements Parcelable {

    String dir;
    String name;
    String hint; // Set a hint, so when player doesnt remember help out

    public ImageMemorize(String Dir, String Name) {
        dir = Dir;
        name = Name;
    }

    protected ImageMemorize(Parcel in) {
        dir = in.readString();
        name = in.readString();
    }

    public static final Creator<ImageMemorize> CREATOR = new Creator<ImageMemorize>() {
        @Override
        public ImageMemorize createFromParcel(Parcel in) {
            return new ImageMemorize(in);
        }

        @Override
        public ImageMemorize[] newArray(int size) {
            return new ImageMemorize[size];
        }
    };

    public String getDir() {
        return dir;
    }

   public String getImageName()
   {
       return name;
   }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dir);
        dest.writeString(name);
    }
}
