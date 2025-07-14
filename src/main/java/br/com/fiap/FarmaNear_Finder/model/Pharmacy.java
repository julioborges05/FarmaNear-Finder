package br.com.fiap.FarmaNear_Finder.model;

public class Pharmacy {

  private final Long id;
  private final String name;
  private final String addressName;
  private final Location location;

  public Pharmacy(long id, String name, String addressName, Location location) {
    checkId(id);
    checkName(name);
    checkAddress(addressName);
    checkLocationEntity(location);

    this.id = id;
    this.name = name;
    this.addressName = addressName;
    this.location = location;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddressName() {
    return addressName;
  }

  public Location getLocationEntity() {
    return location;
  }

  private static void checkId(Long id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Invalid Id");
    }
  }

  private static void checkName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid Name");
    }
  }

  private static void checkAddress(String addressName) {
    if (addressName == null || addressName.isBlank()) {
      throw new IllegalArgumentException("Invalid Address");
    }
  }

  private static void checkLocationEntity(Location location) {
    if (location == null) {
      throw new IllegalArgumentException("Invalid Location Entity");
    }
  }
}
