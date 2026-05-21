package uk.ac.cs1opnu.adventure.cli;

import uk.ac.cs1opnu.adventure.model.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Command {
    private final CommandType type;
    private final List<String> arguments;

    public Command(CommandType type, List<String> arguments) {
        this.type = type;
        this.arguments = new ArrayList<String>(arguments);
    }

    public CommandType getType() {
        return type;
    }

    public List<String> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    public Optional<String> getFirstArgument() {
        if (arguments.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(arguments.get(0));
    }

    public Optional<Direction> getDirection() {
        return getFirstArgument().flatMap(Direction::fromToken);
    }
}