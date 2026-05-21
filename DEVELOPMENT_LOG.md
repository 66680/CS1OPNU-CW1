# Development Log

This log summarises the main development stages for the CS1OPNU-CW1 repository. The detailed evidence is in the Git commit history.

## Stage 1: Project Setup

Commit: `1ad8a51 chore: initialise Maven project`

- Created the Maven project structure.
- Added JUnit 5 configuration.
- Added VS Code project settings and recommended extensions.
- Added initial `README.md` and `REPORT.md` placeholders.

## Stage 2: Test-First Planning

Commit: `834cb54 test: add adventure game test suite`

- Added tests for the model, engine, factories, events, parser, and full game flow before implementation.
- Defined expected behaviour for movement, inventory, puzzle progression, win condition, Singleton state, Observer events, and Factory creation.

## Stage 3: Core Game Implementation

Commit: `80e045e feat: implement multiplayer text adventure`

- Implemented the CLI text adventure game.
- Added room, item, player, puzzle, door, and direction models.
- Implemented Singleton `GameState`.
- Implemented Observer event notifications.
- Implemented Factory-based object and world creation.

## Stage 4: README Documentation

Commit: `ac87305 docs: complete cw1 readme`

- Added project overview, requirements, setup instructions, architecture diagram, class diagram, design pattern notes, assumptions, and test instructions.

## Stage 5: Route Hint Extension

Commit: `e09a0e3 feat: add bfs route hints`

- Added `hint <target>`.
- Implemented breadth-first search in `PathFinder`.
- Added tests for shortest routes, locked paths, missing targets, and zero-length paths.
- Documented `O(V + E)` pathfinding complexity.

## Stage 6: Player Interaction and Final Polishing

Commit: `a5a254c feat: add player interaction commands`

- Added `give <item> <player>` for direct player collaboration.
- Added `inspect <target>` for interacting with rooms, items, and puzzles.
- Added edge case tests for item transfer and inspection.
- Added focused Javadoc to key design-pattern and engine classes.
- Reduced README length to stay below the coursework word limit.

## Current Verification

- `mvn clean test` passes.
- Current test count: 44 tests.
- Main manual smoke tests cover `inspect`, `give`, `inventory`, and normal CLI flow.