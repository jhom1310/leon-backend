package br.edu.ufersa.leon.leon.entities;

public enum RoleType {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private final String name;

    RoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
