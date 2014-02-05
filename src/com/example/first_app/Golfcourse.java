package com.example.first_app;

import android.os.Parcel;
import android.os.Parcelable;

public class Golfcourse implements Parcelable {
	String	name = "None";
	String	address = "None";
	String	city = "None";
	String	county = "None";
	String	courseInfo = "None";
	String	directions = "None";
	int   	holes;
	boolean	isPublic;
	String	imageURL = "None";
	String	id = "None";
	String	info = "None";
	double	latitude = 0.0;
    double	longitude = 0.0;
	String  phone = "None";
	String  slope = "None";
	String  thumbnailURL = "sanmateo";
	String  woeid = "None";	
	
	Golfcourse(String name) {
		this.name = name;
	}
	
	public String toString () {
		return name;

	}
	
	// Parcelable implementation
	
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
    }

    public static final Parcelable.Creator<Golfcourse> CREATOR
            = new Parcelable.Creator<Golfcourse>() {
        public Golfcourse createFromParcel(Parcel in) {
            return new Golfcourse(in);
        }

        public Golfcourse[] newArray(int size) {
            return new Golfcourse[size];
        }
    };
    
    private Golfcourse(Parcel in) {
        name = in.readString();
    }
}
