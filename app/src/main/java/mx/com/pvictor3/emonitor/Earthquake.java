package mx.com.pvictor3.emonitor;

import android.os.Parcel;
import android.os.Parcelable;

public class Earthquake implements Parcelable {
    private double magnitude;
    private String place;
    private long date;
    private String longitude;
    private String latitude;

    public Earthquake(double magnitude, String place, long date, String longitude, String latitude){
        this.magnitude = magnitude;
        this.place = place;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getMagnitude(){return magnitude;}

    public String getPlace(){return place;}

    public long getDate(){return date;}

    public String getLongitude(){return longitude;}

    public String getLatitude(){return latitude;}

    protected Earthquake(Parcel in) {
        magnitude = in.readDouble();
        place = in.readString();
        date = in.readLong();
        longitude = in.readString();
        latitude = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(magnitude);
        dest.writeString(place);
        dest.writeLong(date);
        dest.writeString(longitude);
        dest.writeString(latitude);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Earthquake> CREATOR = new Parcelable.Creator<Earthquake>() {
        @Override
        public Earthquake createFromParcel(Parcel in) {
            return new Earthquake(in);
        }

        @Override
        public Earthquake[] newArray(int size) {
            return new Earthquake[size];
        }
    };
}
