package uk.ac.cs1opnu.adventure.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {
    @Test
    void puzzleSolvesAnswerIgnoringCaseAndWhitespace() {
        Puzzle puzzle = new Puzzle("console", "Name the constellation.", "ORION");

        assertTrue(puzzle.solve("  orion  "));
        assertTrue(puzzle.isSolved());
    }

    @Test
    void puzzleRejectsWrongAnswer() {
        Puzzle puzzle = new Puzzle("console", "Name the constellation.", "ORION");

        assertFalse(puzzle.solve("lyra"));
        assertFalse(puzzle.isSolved());
    }
}