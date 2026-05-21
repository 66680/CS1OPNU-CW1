package uk.ac.cs1opnu.adventure.model;

public class Puzzle {
    private final String id;
    private final String prompt;
    private final String answer;
    private boolean solved;

    public Puzzle(String id, String prompt, String answer) {
        this.id = id;
        this.prompt = prompt;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getPrompt() {
        return prompt;
    }

    public boolean isSolved() {
        return solved;
    }

    public boolean solve(String attempt) {
        if (attempt != null && answer.trim().equalsIgnoreCase(attempt.trim())) {
            solved = true;
            return true;
        }
        return false;
    }
}