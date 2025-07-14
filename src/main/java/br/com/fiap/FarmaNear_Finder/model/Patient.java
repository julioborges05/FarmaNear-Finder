package br.com.fiap.FarmaNear_Finder.model;

public class Patient {

  private final Long id;
  private final String addressName;

  public Patient(Long id, String addressName) {
    checkId(id);
    checkAddress(addressName);

    this.id = id;
    this.addressName = addressName;
  }

  public String getAddressName() {
    return addressName;
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
