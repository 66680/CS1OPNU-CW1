package uk.ac.cs1opnu.adventure.engine;

public class GameResult {
    private final boolean success;
    private final String message;

    private GameResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static GameResult success(String message) {
        return new GameResult(true, message);
    }

    public static GameResult failure(String message) {
        return new GameResult(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}