package uk.ac.cs1opnu.adventure.engine;

import uk.ac.cs1opnu.adventure.model.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathHint {
    private final String targetRoomId;
    private final List<Direction> directions;

    public PathHint(String targetRoomId, List<Direction> directions) {
        this.targetRoomId = targetRoomId;
        this.directions = new ArrayList<Direction>(directions);
    }

    public String getTargetRoomId() {
        return targetRoomId;
    }

    public List<Direction> getDirections() {
        return Collections.unmodifiableList(directions);
    }
}