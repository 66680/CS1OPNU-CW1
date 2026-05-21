# CS1OPNU CW2 Project Reflection

Module Code: CS1OPNU

Assignment report Title: Project Reflection

Student Number: TODO

Actual hrs spent for the assignment: TODO

Which Artificial Intelligence tools used: ChatGPT/Codex.

## GenAI Acknowledgement

ChatGPT/Codex was used to support planning, design-pattern discussion, test planning, documentation structure, and reflection drafting. I reviewed, adapted, tested, and checked the outputs against the assignment brief and my project repository. I take responsibility for the final submitted work.

## Introduction

My CW1 project is a Java command-line multi-player text adventure game set in an abandoned observatory. Two players, Alice and Bob, share one game state and take turns exploring rooms, collecting items, giving items to each other, solving a console puzzle, unlocking a control room, and recovering the Star Crystal. The project also includes `inspect` for interacting with objects, `hint` for route guidance using breadth-first search, and improved help and error messages for usability.

This report evaluates two main aspects of the development process: the usefulness of AI support and the effectiveness of the required design patterns. The project uses Singleton, Observer, and Factory explicitly, supported by a layered structure separating CLI, engine, model, event, factory, and pathfinding responsibilities. My main reflection is that AI was useful for planning and reviewing alternatives, but it was not a substitute for understanding the design, writing tests, or checking whether the implementation matched the coursework requirements.

## Analysis of AI Support in Software Development

### How AI Tools Were Used

I used ChatGPT/Codex as a support tool during planning, design review, test design, and documentation. Early in the project, AI helped compare possible project choices and identify that the text adventure game was the strongest match for the CW1 marking scheme. It also helped turn the assignment brief into an implementation plan using Java, Maven, and JUnit 5. This was useful because the coursework did not only require working code; it also required evidence of object orientation, layered design, design patterns, testing, and documentation.

AI was also used to reason about where the three required patterns should fit. For example, the Observer pattern was discussed as a way to notify players when another player moved, collected an item, solved a puzzle, or won the game. This became the `Observable`, `Observer`, `EventLog`, and `Player` implementation. AI also helped identify additional improvements, such as the BFS-based `hint <target>` command and later CLI guidance improvements. However, these suggestions were only accepted after being turned into tests and implemented in the repository.

### Benefits of Using AI Tools

The main benefit was faster design exploration. Instead of starting with a vague idea of a game, I could compare options and choose a structure that matched the marking criteria. AI was particularly helpful in breaking down the work into layers and tests. For example, the project now has tests for the model, factories, events, engine, parser, BFS pathfinding, player interaction, and a full game flow. This aligns with JUnit's role as a unit testing framework for repeatable tests (JUnit Team, 2024).

AI also helped expose edge cases. The later addition of `GameEngineGuidanceTest` came from reviewing usability and accessibility concerns. The game now gives clearer feedback such as available exits after invalid movement, visible items after a failed `take`, and an `inspect console` suggestion after a wrong puzzle answer. This improved the project because it converted reflection into tested code rather than leaving accessibility as a theoretical issue.

### Challenges and Limitations

The main limitation was that AI suggestions could be too broad or more ambitious than necessary. For example, networking was optional, and adding it would have increased complexity, privacy issues, and testing burden. A networked version might look more advanced, but it could have distracted from the required design patterns and core OOP structure. Human judgement was needed to reject that feature.

Another limitation is that AI can sound confident even when a suggestion is not suitable for a specific brief. This is why I used the assignment document, code, tests, and Git history as the main evidence. For example, BFS was only appropriate because the game world is a small unweighted graph where all exits have equal cost. If exits had different costs, a different pathfinding algorithm would be needed. I also had to check that AI-assisted code stayed Java 8-compatible because the local environment used Java 8.

### Overall Impact on Learning and Development

AI improved my workflow by making the design space clearer, but the learning came from evaluating and verifying the suggestions. Writing tests first made me check whether features such as `give`, `inspect`, and `hint` actually worked. The final result was not just AI-generated code; it was a tested project with 48 passing tests, meaningful commits, and documentation. The most useful lesson was that AI works best as a reviewer and planning partner, while the student must still make the engineering decisions and understand the consequences.

## Analysis of Software Patterns in the Project

### How the Patterns Were Used

The Singleton pattern is implemented in `GameState`. It stores the rooms, players, active player, event log, and win state. This suited the game because all players should interact with one shared world, not separate copies of the map. `GameState.getInstance()` provides a single access point, while `resetForNewGame()` allows tests and new sessions to start cleanly.

The Observer pattern is implemented through `Observable`, `Observer`, `EventLog`, and `Player`. `EventLog` publishes events, and each `Player` receives messages. This supports the multi-player requirement because when Alice moves or gives Bob an item, that action can be observed without hard-coding every notification into the CLI.

The Factory pattern is implemented through `GameObjectFactory` and `WorldFactory`. `GameObjectFactory` creates item and puzzle templates, while `WorldFactory` creates rooms, exits, locked doors, and starting objects. This keeps construction separate from game rules and follows the general Factory idea of centralising object creation (Gamma et al., 1994).

### Benefits of Using Software Patterns

The patterns made the project easier to organise. Singleton supported consistent global state, Observer supported player notifications, and Factory reduced object creation scattered across the engine. This helped the layered architecture because `GameEngine` could focus on rules such as movement, item transfer, puzzle solving, and winning.

The patterns also made testing clearer. `GameStateTest` checks the Singleton behaviour, `ObserverPatternTest` checks notifications, and factory tests check that expected items and rooms are created. This made the design easier to justify in the README and easier to discuss in this report.

### Challenges and Limitations

Each pattern introduced a trade-off. Singleton can make tests interfere with each other if state is not reset, so I added `resetForNewGame()` and called it in test setup. Observer can create noisy event output because every player receives events, including some messages from earlier turns. This is manageable in the current CLI but would need refinement in a larger interface. Factory improves organisation, but simple string template IDs such as `brass_key` and `console` could become rigid if the world expanded.

The key learning is that patterns should solve real problems. They should not be added only to satisfy a checklist. In this project, each pattern has a clear role tied to the game requirements. BFS in `PathFinder` is not one of the required patterns, but it supports performance and usability. It uses the room graph, where rooms are nodes and exits are edges, and finds a shortest available route in `O(V + E)` time. Java `Map` and `EnumMap` also supported efficient and type-appropriate storage for rooms, items, and exits (Oracle, 2026a; Oracle, 2026b).

### Overall Impact on the Project

The patterns improved the project's structure and made the final implementation easier to explain. The most important impact was that features were not all placed in one class. The CLI handles user input, the engine handles rules, the model holds domain state, factories build objects, and events notify players. This made the code more maintainable and gave the tests clear targets.

## Ethical and Legal Considerations

The main AI-related ethical risk was over-reliance. If I had accepted AI output without understanding it, the work would not have represented my own learning. To reduce this risk, I checked suggestions against the assignment brief, reviewed the code, and verified features with tests. The final repository shows this through test-first commits, implementation commits, and final verification with 48 passing tests.

Originality was also important. The project should be submitted as my own assessed work, while acknowledging that AI was used as a support tool. The final design is project-specific: a Java CLI adventure game with local multiplayer, no networking, BFS route hints, `give`, `inspect`, and guided error messages. I should not claim that every idea came without assistance, but I can take responsibility for deciding what was included and why.

Acknowledgement is necessary because the coursework brief permits and requires AI use in relation to CW1, and the academic integrity guidance requires students to state whether GenAI was used. Therefore, the report includes a GenAI acknowledgement naming ChatGPT/Codex and describing its role. This supports transparency and responsible AI use (OpenAI, 2026).

Responsibility for final work remains with the student. AI cannot guarantee correctness, academic suitability, or legal compliance. I exercised responsibility by running tests, checking claims against the code, keeping Git evidence, and leaving personal fields such as student number and hours to be filled truthfully.

The project's privacy risk is low because it uses fictional names, no accounts, no database, and no network communication. If it were extended to real users or networked multiplayer, UK data protection principles such as fairness, purpose limitation, data minimisation, storage limitation, security, and accountability would become relevant (ICO, 2026; GOV.UK, 2026). Misuse risk is also limited in the current local CLI version, but networked chat or accounts would need moderation, access control, and clearer privacy notices. Accessibility was partly addressed by keyboard-only interaction, grouped help text, examples, and guided error messages, although a graphical or screen-reader-tested version could improve this further.

## Conclusion

CW1 showed that AI tools and design patterns are useful when they are applied critically. AI helped me plan the project, compare options, identify edge cases, and improve documentation, but the main quality control came from testing, code review, and checking the work against the brief. The design patterns also helped, but only because they matched real needs: shared game state, player notifications, and object creation.

The project also showed that ethical reflection can influence implementation. Accessibility concerns led to clearer help text and more useful error messages. Privacy concerns supported the decision not to add networking or real accounts. In future projects, I would continue to use AI for brainstorming, alternatives, and review, but I would keep tests, source control, references, and personal responsibility as the main safeguards.

## References

Gamma, E., Helm, R., Johnson, R. and Vlissides, J. (1994) *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.

GOV.UK (2026) *Data protection*. Available at: https://www.gov.uk/data-protection/ (Accessed: 21 May 2026).

Information Commissioner's Office (2026) *A guide to the data protection principles*. Available at: https://ico.org.uk/for-organisations/uk-gdpr-guidance-and-resources/data-protection-principles/a-guide-to-the-data-protection-principles/ (Accessed: 21 May 2026).

JUnit Team (2024) *JUnit 5 User Guide, version 5.10.2*. Available at: https://docs.junit.org/5.10.2/user-guide/ (Accessed: 21 May 2026).

OpenAI (2026) *Responsible and safe use of AI*. Available at: https://openai.com/academy/responsible-and-safe-use/ (Accessed: 21 May 2026).

Oracle (2026a) *Interface Map<K,V>*. Available at: https://docs.oracle.com/javase/8/docs/api/java/util/Map.html (Accessed: 21 May 2026).

Oracle (2026b) *Class EnumMap<K extends Enum<K>,V>*. Available at: https://docs.oracle.com/javase/8/docs/api/java/util/EnumMap.html (Accessed: 21 May 2026).