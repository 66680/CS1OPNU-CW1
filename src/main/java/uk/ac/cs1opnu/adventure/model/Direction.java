package uk.ac.cs1opnu.adventure.model;

import java.util.Locale;
import java.util.Optional;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Optional<Direction> fromToken(String token) {
        if (token == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(Direction.valueOf(token.trim().toUpperCase(Locale.ROOT)));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}