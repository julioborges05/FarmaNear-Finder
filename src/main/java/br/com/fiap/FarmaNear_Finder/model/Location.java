package br.com.fiap.FarmaNear_Finder.model;

public class Location {

    private final double lat;
    private final double lng;

    public Location(double lat, double lng) {
        checkLat(lat);
        checkLng(lng);

        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    private static void checkLat(double lat) {
        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90 degrees.");
        }
    }

    private static void checkLng(double lng) {
        if (lng < -180 || lng > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180 degrees.");
        }
    }
}
