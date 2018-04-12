package pinkjacket.coffeenow;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Coffee {
    String name;
    String phone;
    String website;
    String imageUrl;
    ArrayList<String> address = new ArrayList<>();
    double latitude;
    double longitude;

    public Coffee(){}

    public Coffee(String name, String phone, String website, String imageUrl, ArrayList<String> address, double latitude, double longitude){
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.imageUrl = imageUrl;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
