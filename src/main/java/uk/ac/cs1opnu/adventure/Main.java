package uk.ac.cs1opnu.adventure;

import uk.ac.cs1opnu.adventure.cli.CommandLineInterface;
import uk.ac.cs1opnu.adventure.engine.GameEngine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new CommandLineInterface(new GameEngine(), new Scanner(System.in)).run();
    }
}