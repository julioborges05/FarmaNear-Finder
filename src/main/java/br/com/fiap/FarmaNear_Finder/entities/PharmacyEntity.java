package br.com.fiap.FarmaNear_Finder.entities;

public class PharmacyEntity {

  private final Long id;
  private final String name;
  private final String addressName;
  private final LocationEntity locationEntity;

  public PharmacyEntity(long id, String name, String addressName, LocationEntity locationEntity) {
    checkId(id);
    checkName(name);
    checkAddress(addressName);
    checkLocationEntity(locationEntity);

    this.id = id;
    this.name = name;
    this.addressName = addressName;
    this.locationEntity = locationEntity;
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

  public LocationEntity getLocationEntity() {
    return locationEntity;
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

  private static void checkLocationEntity(LocationEntity locationEntity) {
    if (locationEntity == null) {
      throw new IllegalArgumentException("Invalid Location Entity");
    }
  }
}
