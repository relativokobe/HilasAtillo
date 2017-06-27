package com.example.asus.hilasatillo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by asus on 12/05/2017.
 */

public class PersonModel implements Parcelable{
    String Name;
    int toPay;
    String status;


    protected PersonModel(Parcel in) {
        Name = in.readString();
        toPay = in.readInt();
        status = in.readString();
    }

    public static final Creator<PersonModel> CREATOR = new Creator<PersonModel>() {
        @Override
        public PersonModel createFromParcel(Parcel in) {
            return new PersonModel(in);
        }

        @Override
        public PersonModel[] newArray(int size) {
            return new PersonModel[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getToPay() {
        return toPay;
    }

    public void setToPay(int toPay) {
        this.toPay = toPay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PersonModel(String name, int toPay, String status) {

        Name = name;
        this.toPay = toPay;
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeInt(toPay);
        dest.writeString(status);
    }
}
