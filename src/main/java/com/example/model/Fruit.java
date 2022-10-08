package com.example.model;

public class Fruit {

    public String name;
    public String description;

    public Fruit() {}
    public Fruit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
                "%s:{name:\"%s\", description:\"%s\"}", getClass().getSimpleName(), name, description);
    }
}
