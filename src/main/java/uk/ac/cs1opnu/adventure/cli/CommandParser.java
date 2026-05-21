package uk.ac.cs1opnu.adventure.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class CommandParser {
    private CommandParser() {
    }

    public static Command parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new Command(CommandType.UNKNOWN, Collections.<String>emptyList());
        }
        String[] tokens = input.trim().split("\\s+");
        String verb = tokens[0].toLowerCase(Locale.ROOT);
        List<String> args = new ArrayList<String>(Arrays.asList(tokens).subList(1, tokens.length));

        if ("help".equals(verb)) {
            return new Command(CommandType.HELP, args);
        }
        if ("players".equals(verb)) {
            return new Command(CommandType.PLAYERS, args);
        }
        if ("switch".equals(verb)) {
            return new Command(CommandType.SWITCH, args);
        }
        if ("look".equals(verb)) {
            return new Command(CommandType.LOOK, args);
        }
        if ("inspect".equals(verb)) {
            return new Command(CommandType.INSPECT, args);
        }
        if ("go".equals(verb)) {
            return new Command(CommandType.GO, args);
        }
        if ("take".equals(verb)) {
            return new Command(CommandType.TAKE, args);
        }
        if ("give".equals(verb)) {
            return new Command(CommandType.GIVE, args);
        }
        if ("inventory".equals(verb) || "inv".equals(verb)) {
            return new Command(CommandType.INVENTORY, args);
        }
        if ("use".equals(verb)) {
            return new Command(CommandType.USE, args);
        }
        if ("hint".equals(verb)) {
            return new Command(CommandType.HINT, args);
        }
        if ("solve".equals(verb)) {
            return new Command(CommandType.SOLVE, args);
        }
        if ("quit".equals(verb) || "exit".equals(verb)) {
            return new Command(CommandType.QUIT, args);
        }
        return new Command(CommandType.UNKNOWN, args);
    }
}