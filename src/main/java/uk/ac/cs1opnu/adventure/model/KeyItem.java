package uk.ac.cs1opnu.adventure.model;

public class KeyItem extends Item {
    private final String unlocksRoomId;

    public KeyItem(String id, String name, String description, String unlocksRoomId) {
        super(id, name, description);
        this.unlocksRoomId = unlocksRoomId;
    }

    public String getUnlocksRoomId() {
        return unlocksRoomId;
    }
}