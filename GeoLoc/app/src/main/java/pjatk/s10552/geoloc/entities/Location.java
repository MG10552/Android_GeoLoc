package pjatk.s10552.geoloc.entities;

public class Location {

    private double latitude;
    private double longitude;
    private String name;
    private String description;
    private double proximity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getProximity() {
        return proximity;
    }

    public void setProximity(double proximity) {
        this.proximity = proximity;
    }
}