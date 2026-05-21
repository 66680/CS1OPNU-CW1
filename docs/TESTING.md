# Testing Guide

This project uses JUnit 5 through Maven.

## Run All Tests

```powershell
mvn clean test
```

If Maven is not installed globally in this workspace:

```powershell
..\.tools\apache-maven-3.8.8\bin\mvn.cmd clean test
```

## Current Result

Latest verification:

```text
Tests run: 48, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Test Coverage

The tests are grouped by responsibility:

- `model` tests cover domain objects such as `Room`, `Player`, `Puzzle`, and item subclasses.
- `events` tests cover Observer registration, removal, notification, and event logging.
- `factory` tests cover object templates and default world construction.
- `engine` tests cover movement, inventory, player switching, item transfer, inspection, puzzle progression, route hints, and the win condition.
- `cli` tests cover command parsing.
- `FullGameFlowTest` covers an integrated two-player playthrough.

## Edge Cases Covered

- Invalid movement directions.
- Missing items.
- Unknown players.
- Item transfer when players are in different rooms.
- Wrong puzzle answers.
- Locked doors before puzzle/key progression.
- Missing hint targets.
- Hints blocked by locked doors.
- Zero-length hint paths when already at the target room.
- Defensive copying of BFS path results.

## Manual Smoke Test

```text
look
inspect room
go east
take brass_key
switch Bob
go east
switch Alice
give brass_key Bob
switch Bob
inventory
inspect brass_key
solve console ORION
use brass_key
hint star_crystal
quit
```

Expected result:

- The game starts and accepts CLI commands.
- Players can switch turns.
- Players in the same room can transfer an item.
- Inventory and inspection show the transferred item.
- Observer events are printed after shared actions.