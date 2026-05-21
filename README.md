# CS1OPNU CW1 Project

Module Code: CS1OPNU

Assignment report Title: Project

Student Number: TODO

Actual hrs spent for the assignment: TODO

Which Artificial Intelligence tools used: ChatGPT/Codex was used to support the project planning, test planning, and the design of the Observer event notification structure. The code should be reviewed, understood, tested, and adapted by the student before final submission.

## Overview

This project is a Java command-line multi-player text adventure game. Players explore an abandoned observatory, collect items, solve a puzzle, unlock restricted areas, and work toward a shared win condition. The game is designed to demonstrate object-oriented programming, layered software design, unit testing, three software design patterns, and a small graph-search algorithm for route hints.

## Build and Run

```powershell
mvn clean test
mvn exec:java
```

If Maven is not installed globally on Windows from this workspace, use:

```powershell
..\.tools\apache-maven-3.8.8\bin\mvn.cmd clean test
..\.tools\apache-maven-3.8.8\bin\mvn.cmd exec:java
```

## Implementation Highlights

The project implements a local multi-player text adventure game set inside an abandoned observatory. Two players, Alice and Bob, can take turns in one shared command-line session. The game world contains multiple connected rooms, including an entrance hall, library, workshop, control room, and vault. Players can inspect rooms, move between locations, collect items, ask for route hints, solve a console puzzle, unlock a restricted room with a key, and win by recovering the Star Crystal.

The implementation uses a layered structure. The CLI package parses and routes user commands, the engine package applies game rules, the model package represents domain objects, the events package implements Observer notifications, and the factory package creates objects and builds the default world. This keeps user interaction separate from the core game logic and makes the code easier to test.

The required design patterns are implemented explicitly. `GameState` is a Singleton that provides one shared state for the rooms, players, active player, event log, and win condition. The Observer pattern is implemented through `Observable`, `Observer`, `GameEvent`, and `EventLog`, allowing players to receive messages when game events occur. The Factory pattern is implemented through `GameObjectFactory` and `WorldFactory`, which centralise creation of items, puzzles, rooms, exits, and the default map.

The game world is represented as a graph, where rooms are nodes and exits are edges. The `hint <target>` command uses breadth-first search to find the shortest currently available route from the active player's room to a target room or item. BFS was chosen because all exits have equal cost, so it gives the shortest path with simple and predictable `O(V + E)` complexity.

The test suite uses JUnit 5 and covers domain objects, movement, inventory management, puzzle progression, the win condition, command parsing, Factory creation, Observer notification, Singleton behaviour, and BFS route hints.

## Requirements

Priority 1:
- Implement a structured game world with multiple connected rooms.
- Allow multiple players to take turns in a shared game session.
- Support core commands: `look`, `go`, `take`, `inventory`, `switch`, `use`, `hint`, `solve`, `players`, `help`, and `quit`.
- Implement a clear win condition.

Priority 2:
- Use Singleton, Observer, and Factory patterns.
- Use object-oriented domain classes with inheritance and composition.
- Separate CLI, game engine, model, factory, and event logic.
- Use graph search to provide route hints without hard-coding paths.

Priority 3:
- Add JUnit 5 unit and integration tests.
- Provide setup, run, and test instructions.
- Document design assumptions and architecture diagrams.

## Design

### Architecture Diagram

```mermaid
flowchart TD
    User["Player input"] --> CLI["CLI Layer\nCommandLineInterface\nCommandParser"]
    CLI --> Engine["Engine Layer\nGameEngine\nGameResult"]
    Engine --> State["Singleton State\nGameState"]
    Engine --> Model["Domain Model\nPlayer Room Item Puzzle Door"]
    Engine --> Events["Observer Events\nEventLog GameEvent"]
    Engine --> Pathing["Pathfinding\nPathFinder BFS"]
    State --> Factory["Factory Layer\nWorldFactory GameObjectFactory"]
    Factory --> Model
    Events --> PlayerObservers["Player observers\nMessage inbox"]
    Pathing --> Model
```

### Class Diagram

```mermaid
classDiagram
    class GameState {
        -static GameState INSTANCE
        -Map rooms
        -Map players
        -EventLog eventLog
        +getInstance()
        +startNewGame(List)
        +resetForNewGame()
    }

    class GameEngine {
        +startNewGame(List)
        +move(Direction)
        +take(String)
        +use(String)
        +solve(String, String)
        +switchPlayer(String)
    }

    class GameObject {
        <<abstract>>
        -String id
        -String name
        -String description
    }

    class Item
    class KeyItem
    class TreasureItem
    class Room
    class Door
    class Puzzle
    class Player
    class Observer {
        <<interface>>
        +update(GameEvent)
    }
    class Observable {
        +addObserver(Observer)
        +removeObserver(Observer)
    }
    class EventLog {
        +publish(GameEvent)
    }
    class GameObjectFactory {
        +createItem(String)
        +createPuzzle(String)
    }
    class WorldFactory {
        +createDefaultWorld()
    }
    class PathFinder {
        +findPath(Map, String, String)
    }
    class PathHint {
        -String targetRoomId
        -List directions
    }

    GameObject <|-- Item
    Item <|-- KeyItem
    Item <|-- TreasureItem
    Observer <|.. Player
    Observable <|-- EventLog
    GameEngine --> GameState
    GameState --> EventLog
    Room o-- Item
    Room o-- Door
    Room o-- Puzzle
    WorldFactory --> Room
    GameObjectFactory --> Item
    PathFinder --> PathHint
    PathFinder --> Room
```

### Design Patterns

Singleton:
- `GameState` provides a single authoritative state object.
- It stores the world, players, active player, event log, and game outcome.
- `resetForNewGame()` is included so tests and a new game can start from a clean state.

Observer:
- `Observer` defines the notification contract.
- `Observable` manages registered observers.
- `EventLog` publishes events and stores event history.
- `Player` implements `Observer` and receives event messages.

Factory:
- `GameObjectFactory` creates item and puzzle objects from template IDs.
- `WorldFactory` creates the default room map and places objects.
- This keeps object creation outside the game engine.

### Algorithm and Performance Considerations

The project uses simple algorithms deliberately. They are efficient for the game size and easy to reason about:

- The game world is stored as `Map<String, Room>`, giving average `O(1)` lookup for rooms by ID.
- Room items are stored as `Map<String, Item>`, giving average `O(1)` lookup and removal when a player takes an item.
- Room exits use `EnumMap<Direction, String>`, which is compact and type-safe because directions are fixed enum values.
- The route hint system uses breadth-first search in `PathFinder`. For a world with `V` rooms and `E` exits, the complexity is `O(V + E)`.
- BFS only follows exits where `Room.canExit(direction)` is true, so hints do not incorrectly route players through locked doors.

The BFS feature is intentionally placed in `PathFinder` rather than inside the CLI or model classes. This keeps pathfinding as a separate engine-level responsibility and supports unit testing without needing console input.

### Edge Case Handling

The implementation handles these edge cases:

- Invalid movement direction or no exit in that direction.
- Attempting to take an item that is not in the current room.
- Trying to unlock a door without the required key.
- Trying to unlock the control room before solving the console puzzle.
- Wrong puzzle answers do not mark the puzzle as solved.
- Attempting to switch to an unknown player returns a failure result.
- `hint` returns a clear failure when a target does not exist.
- `hint` does not route through locked doors.
- `hint` handles the case where the player is already at the target room.

## Assumptions

- Networking is not implemented because the assignment marks it as optional.
- Multiple players are supported through turn switching in one shared CLI session.
- The game uses Java 8-compatible syntax because the available local JDK is Java 8. The code can also run on newer JDKs.
- The command-line interface is intentionally simple so the project focuses on object-oriented design, patterns, game logic, and testing.
- Student number and final hours spent should be filled in by the student before submission.

## Example Commands

```text
look
go north
take note
switch Bob
go east
take brass_key
solve console ORION
use brass_key
hint star_crystal
go east
go north
take star_crystal
```

## Testing

Run all tests:

```powershell
mvn clean test
```

Or, from this workspace without global Maven:

```powershell
..\.tools\apache-maven-3.8.8\bin\mvn.cmd clean test
```

The test suite covers:
- Singleton state management.
- Observer event notification.
- Factory-created items, puzzles, and world map.
- Player movement and player switching.
- Inventory management.
- Puzzle solving and locked-door progression.
- Win condition.
- CLI command parsing.
- BFS pathfinding for `hint <target>`.
- Edge cases including locked routes, missing targets, and zero-length paths.

## Testing Strategy

The tests are organised by responsibility:

- Unit tests validate individual domain classes such as `Room`, `Player`, `Puzzle`, and item subclasses.
- Pattern-specific tests validate Singleton, Observer, and Factory behaviour directly.
- Engine tests validate player movement, inventory rules, puzzle progression, locked-door behaviour, route hints, and the win condition.
- `PathFinderTest` validates the BFS algorithm, including shortest paths, locked paths, missing targets, and defensive copying of path results.
- `CommandParserTest` validates user command parsing before commands reach the game engine.
- `FullGameFlowTest` acts as an integration test for a complete two-player playthrough.