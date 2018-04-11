package pinkjacket.coffeenow;

import java.util.ArrayList;

public class Coffee {
    private String name;
    private String phone;
    private String website;
    private String imageUrl;
    private ArrayList<String> address = new ArrayList<>();
    private double latitude;
    private double longitude;

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
