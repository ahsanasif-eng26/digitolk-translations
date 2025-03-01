package com.digital_tolk.translations.enums;

public enum Role {
  USER(1, "USER"),
  ADMIN(2, "ADMIN");

  private final int id;
  private final String name;

  Role(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static Role fromId(int id) {
    for (Role role : values()) {
      if (role.id == id) {
        return role;
      }
    }
    throw new IllegalArgumentException("Invalid role ID: " + id);
  }
}
