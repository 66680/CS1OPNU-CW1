package uk.ac.cs1opnu.adventure.cli;

import uk.ac.cs1opnu.adventure.engine.GameEngine;
import uk.ac.cs1opnu.adventure.engine.GameResult;
import uk.ac.cs1opnu.adventure.engine.GameState;
import uk.ac.cs1opnu.adventure.model.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {
    private final GameEngine engine;
    private final Scanner scanner;

    public CommandLineInterface(GameEngine engine, Scanner scanner) {
        this.engine = engine;
        this.scanner = scanner;
    }

    public void run() {
        engine.startNewGame(Arrays.asList("Alice", "Bob"));
        System.out.println("CS1OPNU Text Adventure");
        System.out.println(helpText());
        while (!GameState.getInstance().isGameWon()) {
            Player active = GameState.getInstance().getActivePlayer();
            System.out.print(active.getName() + "> ");
            if (!scanner.hasNextLine()) {
                return;
            }
            Command command = CommandParser.parse(scanner.nextLine());
            if (command.getType() == CommandType.QUIT) {
                System.out.println("Goodbye.");
                return;
            }
            System.out.println(handle(command).getMessage());
            printMessages(active);
        }
    }

    private GameResult handle(Command command) {
        switch (command.getType()) {
            case HELP:
                return GameResult.success(helpText());
            case PLAYERS:
                return engine.players();
            case SWITCH:
                return command.getFirstArgument()
                        .map(engine::switchPlayer)
                        .orElse(GameResult.failure("Usage: switch <player>"));
            case LOOK:
                return engine.look();
            case GO:
                return command.getDirection()
                        .map(engine::move)
                        .orElse(GameResult.failure("Usage: go <north|east|south|west>"));
            case TAKE:
                return command.getFirstArgument()
                        .map(engine::take)
                        .orElse(GameResult.failure("Usage: take <item>"));
            case INVENTORY:
                return engine.inventory();
            case USE:
                return command.getFirstArgument()
                        .map(engine::use)
                        .orElse(GameResult.failure("Usage: use <item>"));
            case HINT:
                return command.getFirstArgument()
                        .map(engine::hint)
                        .orElse(GameResult.failure("Usage: hint <room|item>"));
            case SOLVE:
                List<String> args = command.getArguments();
                if (args.size() < 2) {
                    return GameResult.failure("Usage: solve <puzzle> <answer>");
                }
                return engine.solve(args.get(0), args.get(1));
            default:
                return GameResult.failure("Unknown command. Type help for available commands.");
        }
    }

    private void printMessages(Player player) {
        for (String message : player.drainMessages()) {
            System.out.println("[Event] " + message);
        }
    }

    private String helpText() {
        return "Commands: help, players, switch <player>, look, go <direction>, take <item>, inventory, use <item>, hint <room|item>, solve <puzzle> <answer>, quit";
    }
}