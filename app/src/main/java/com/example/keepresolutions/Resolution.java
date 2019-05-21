package com.example.keepresolutions;

import java.io.Serializable;

public class Resolution {

    private int id;
    private String name;
    private String description;
    private String reason;
    private String damage;

    public Resolution() {}

    public Resolution(String name, String description, String reason, String damage) {
        this.name = name;
        this.description = description;
        this.reason = reason;
        this.damage = damage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String raison) {
        this.reason = raison;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }
}
