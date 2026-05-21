package uk.ac.cs1opnu.adventure.events;

public class GameEvent {
    private final GameEventType type;
    private final String actor;
    private final String message;

    public GameEvent(GameEventType type, String actor, String message) {
        this.type = type;
        this.actor = actor;
        this.message = message;
    }

    public GameEventType getType() {
        return type;
    }

    public String getActor() {
        return actor;
    }

    public String getMessage() {
        return message;
    }
}