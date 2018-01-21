package mx.com.pvictor3.emonitor;

import android.os.Parcel;
import android.os.Parcelable;

public class Earthquake implements Parcelable {
    private int magnitude;
    private String place;
    private String date;
    private String longitude;
    private String latitude;

    public Earthquake(int magnitude, String place, String date, String longitude, String latitude){
        this.magnitude = magnitude;
        this.place = place;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getMagnitude(){return magnitude;}

    public String getPlace(){return place;}

    public String getDate(){return date;}

    public String getLongitude(){return longitude;}

    public String getLatitude(){return latitude;}

    protected Earthquake(Parcel in) {
        magnitude = in.readInt();
        place = in.readString();
        date = in.readString();
        longitude = in.readString();
        latitude = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(magnitude);
        dest.writeString(place);
        dest.writeString(date);
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
