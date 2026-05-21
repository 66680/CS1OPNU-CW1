package uk.ac.cs1opnu.adventure.cli;

import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.model.Direction;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {
    @Test
    void parserHandlesValidCommands() {
        assertEquals(CommandType.LOOK, CommandParser.parse("look").getType());
        assertEquals(Direction.NORTH, CommandParser.parse("go north").getDirection().orElse(null));
        assertEquals("brass_key", CommandParser.parse("take brass_key").getFirstArgument().orElse(null));
        assertEquals("Alice", CommandParser.parse("switch Alice").getFirstArgument().orElse(null));
        assertEquals("vault", CommandParser.parse("hint vault").getFirstArgument().orElse(null));
        assertEquals(CommandType.HINT, CommandParser.parse("hint vault").getType());
        assertEquals(CommandType.SOLVE, CommandParser.parse("solve console ORION").getType());
    }

    @Test
    void parserHandlesUnknownOrBlankInput() {
        assertEquals(CommandType.UNKNOWN, CommandParser.parse("").getType());
        assertEquals(CommandType.UNKNOWN, CommandParser.parse("dance").getType());
    }
}