package uk.ac.cs1opnu.adventure.model;

public class Door {
    private final String targetRoomId;
    private final String requiredItemId;
    private boolean locked = true;

    public Door(String targetRoomId, String requiredItemId) {
        this.targetRoomId = targetRoomId;
        this.requiredItemId = requiredItemId;
    }

    public String getTargetRoomId() {
        return targetRoomId;
    }

    public String getRequiredItemId() {
        return requiredItemId;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean unlockWith(String itemId) {
        if (requiredItemId.equals(itemId)) {
            locked = false;
            return true;
        }
        return false;
    }
}