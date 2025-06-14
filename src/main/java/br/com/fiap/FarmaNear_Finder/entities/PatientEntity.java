package br.com.fiap.FarmaNear_Finder.entities;

public class PatientEntity {

  private final Long id;
  private final String addressName;

  public PatientEntity(Long id, String addressName) {
    checkId(id);
    checkAddress(addressName);

    this.id = id;
    this.addressName = addressName;
  }

  public String getAddressName() {
    return addressName;
  }

  public Long getId() {
    return id;
  }

  private static void checkId(Long id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Id Invalid");
    }
  }

  private static void checkAddress(String addressName) {
    if (addressName == null || addressName.isBlank()) {
      throw new IllegalArgumentException("Endereço Inválido");
    }
  }
}
