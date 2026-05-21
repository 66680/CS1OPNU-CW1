package uk.ac.cs1opnu.adventure.model;

import java.util.Objects;

public abstract class GameObject {
    private final String id;
    private final String name;
    private final String description;

    protected GameObject(String id, String name, String description) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Object id must not be blank.");
        }
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GameObject)) {
            return false;
        }
        GameObject that = (GameObject) other;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}