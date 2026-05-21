# CS1OPNU CW2 Project Reflection

Module Code: CS1OPNU

Assignment report Title: Project Reflection

Student Number: 33804516

Actual hrs spent for the assignment: 12

Which Artificial Intelligence tools used: ChatGPT/Codex.

## GenAI Acknowledgement

I used ChatGPT/Codex to support project planning, design-pattern discussion, test planning, documentation structure, and reflection drafting. I reviewed and adapted the suggestions, tested the implementation, checked the report against the repository, and take responsibility for the final submitted work.

## Introduction

For CW1, I built a Java command-line multi-player text adventure game set in an abandoned observatory. Alice and Bob share the same game world and take turns exploring rooms, collecting items, giving items to each other, solving a console puzzle, unlocking the control room, and recovering the Star Crystal. The final version also includes `inspect` for interacting with objects, `hint` for route guidance using breadth-first search, and clearer help and error messages.

This report reflects on two parts of the development process: how AI supported the work, and how the required design patterns affected the implementation. My overall view is that AI was useful for planning and review, but it did not remove the need to understand the code. The project only became reliable after I connected suggestions to tests, checked them against the brief, and made deliberate design choices.

## Analysis of AI Support in Software Development

### How AI Tools Were Used

I used ChatGPT/Codex mainly as a planning and review tool. At the start, it helped me compare the three possible CW1 projects against the marking criteria. The text adventure game was the strongest option because it naturally supported object-oriented classes, shared state, player interaction, and the three required patterns. AI also helped turn the brief into a Java, Maven, and JUnit 5 implementation plan.

AI was also useful when deciding where the patterns should sit. For instance, the Observer pattern was considered as a way to notify players when another player moved, collected an item, solved a puzzle, or won the game. That idea became the `Observable`, `Observer`, `EventLog`, and `Player` implementation. Later, AI-assisted review also suggested useful extensions such as `hint <target>` and improved CLI guidance. I did not copy these ideas straight into the final work. I first turned them into tests, then implemented the parts that fitted the project.

### Benefits of Using AI Tools

The biggest benefit was that AI made the early design stage less vague. It helped me break the project into layers: CLI, engine, model, events, factories, and pathfinding. This gave the tests clearer targets. The project now tests domain objects, factories, events, the engine, command parsing, BFS pathfinding, player interaction, and a full game flow. This matches JUnit's purpose as a framework for repeatable tests (JUnit Team, 2024).

AI also helped me notice edge cases that I might otherwise have left until the end. One example is `GameEngineGuidanceTest`. After reviewing usability and accessibility, I improved feedback for invalid movement, missing items, and wrong puzzle answers. The CLI now tells the player the available exits, visible items, or suggests `inspect console`. This was a useful moment in the project because an ethical concern about accessibility became a tested code change, not just a sentence in the report.

### Challenges and Limitations

The main limitation was that AI sometimes suggested more than the project needed. I considered networking because the brief mentioned it as optional, but I rejected it. Testing sockets and shared sessions would have taken attention away from the required design patterns and core OOP structure. It would also have introduced privacy and reliability issues that were outside the scope of CW1.

Another limitation is that AI can sound confident even when a suggestion only partly fits the brief. For that reason, I treated the assignment document, code, tests, and Git history as the main evidence. For example, BFS was appropriate because the map is a small unweighted graph where each exit has the same cost. If different exits had different costs, BFS would not be the right choice. I also had to keep checking Java 8 compatibility because that was the version available locally.

### Overall Impact on Learning and Development

AI made the design space clearer, but the learning came from testing and deciding what to keep. Writing tests first forced me to check whether `give`, `inspect`, and `hint` actually worked. The final project has 48 passing tests, meaningful commits, and documentation, so the result is not just an AI-generated answer. The main lesson I took from the process is that AI is most useful when it challenges and organises my thinking, not when it replaces my own design decisions.

## Analysis of Software Patterns in the Project

### How the Patterns Were Used

The Singleton pattern is implemented in `GameState`. It stores the rooms, players, active player, event log, and win state. This suited the game because Alice and Bob must interact with the same world. If different parts of the program had separate state objects, item transfer, room progression, and win detection would become inconsistent. `GameState.getInstance()` gives one shared access point, while `resetForNewGame()` supports clean test setup.

The Observer pattern is implemented through `Observable`, `Observer`, `EventLog`, and `Player`. `EventLog` publishes events, and each `Player` receives messages. This supports the multi-player requirement because player actions can be announced without the engine manually printing every possible notification.

The Factory pattern is implemented through `GameObjectFactory` and `WorldFactory`. `GameObjectFactory` creates item and puzzle templates, while `WorldFactory` creates rooms, exits, locked doors, and starting objects. This follows the general Factory idea of centralising object creation (Gamma et al., 1994), and keeps construction separate from game rules.

### Benefits of Using Software Patterns

The patterns made the code easier to organise. Singleton provided consistent global state, Observer handled notifications, and Factory kept object creation out of the engine. As a result, `GameEngine` could focus on rules such as movement, item transfer, puzzle solving, hints, and winning.

The patterns also made the testing strategy more direct. `GameStateTest` checks Singleton behaviour, `ObserverPatternTest` checks notifications, and factory tests check that expected rooms and objects are created. These tests made the design easier to justify in the README and easier to discuss in this report.

### Challenges and Limitations

The patterns were useful, but none of them was free of trade-offs. Singleton can make tests interfere with each other if the shared state is not reset, so every engine test starts with `resetForNewGame()`. Observer can also make CLI output noisy, because players receive event messages from previous actions. I noticed this during smoke testing. It is acceptable for a small coursework game, but a larger version would need better message filtering. Factory improved structure, but string template IDs such as `brass_key` and `console` could become rigid if the world became much larger.

The important point is that the patterns solved real problems in the project. They were not just added to satisfy a checklist. BFS in `PathFinder` is not one of the required patterns, but it supports usability and performance. The room graph is searched in `O(V + E)` time, while Java `Map` and `EnumMap` support efficient storage for rooms, items, and exits (Oracle, 2026a; Oracle, 2026b).

### Overall Impact on the Project

The design patterns improved the structure of the project and made the implementation easier to explain. The CLI handles input, the engine handles rules, the model holds state, factories build objects, and events notify players. This separation made the code more maintainable and gave the tests clear responsibilities.

## Ethical and Legal Considerations

The main AI-related ethical risk was over-reliance. If I had accepted AI output without understanding it, the coursework would not have represented my own learning. I reduced this risk by checking suggestions against the brief, reading the code, and verifying features with tests. The repository shows this through test-first commits, implementation commits, and final verification with 48 passing tests.

Originality was also important. The project is my assessed work, but AI was used as a support tool. The final design is specific to this project: a Java CLI adventure game with local multiplayer, no networking, BFS route hints, `give`, `inspect`, and guided error messages. I should not pretend that every idea appeared without assistance, but I can take responsibility for deciding what was included and why.

Acknowledgement is necessary because the coursework brief requires AI use in relation to CW1, and the academic integrity guidance says GenAI use must be stated. This report therefore names ChatGPT/Codex and describes its role. That supports transparency and responsible AI use (OpenAI, 2026).

Responsibility for final work remains with the student. AI cannot guarantee correctness, academic suitability, or legal compliance. I exercised responsibility by running tests, checking claims against the repository, keeping Git evidence, and making sure the report describes decisions that are visible in the code.

The privacy risk in the current project is low because it uses fictional names, no accounts, no database, and no network communication. If it were extended to real users or networked multiplayer, UK data protection principles such as fairness, purpose limitation, data minimisation, storage limitation, security, and accountability would become relevant (ICO, 2026; GOV.UK, 2026). Misuse risk is also limited in the local CLI version, although a networked chat system would need moderation, access control, and privacy notices. Accessibility was partly addressed through keyboard-only interaction, grouped help text, examples, and guided error messages, although a screen-reader-tested interface would be stronger.

## Conclusion

CW1 showed me that AI tools and design patterns are useful only when they are applied critically. AI helped with planning, alternatives, edge cases, and documentation, but the main quality control came from tests, code review, and checking the brief. The design patterns helped because they matched real needs in my implementation: shared game state, player notifications, and object creation.

The project also showed that ethical reflection can change the implementation. The most useful correction was realising that accessibility should affect the actual CLI, not just appear in the report. That led to clearer help text and more helpful error messages. Privacy concerns also supported the decision not to add networking or real accounts. In future projects, I would still use AI for brainstorming and review, but I would keep testing, source control, references, and personal responsibility as the main safeguards.

## References

Gamma, E., Helm, R., Johnson, R. and Vlissides, J. (1994) *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.

GOV.UK (2026) *Data protection*. Available at: https://www.gov.uk/data-protection/ (Accessed: 21 May 2026).

Information Commissioner's Office (2026) *A guide to the data protection principles*. Available at: https://ico.org.uk/for-organisations/uk-gdpr-guidance-and-resources/data-protection-principles/a-guide-to-the-data-protection-principles/ (Accessed: 21 May 2026).

JUnit Team (2024) *JUnit 5 User Guide, version 5.10.2*. Available at: https://docs.junit.org/5.10.2/user-guide/ (Accessed: 21 May 2026).

OpenAI (2026) *Responsible and safe use of AI*. Available at: https://openai.com/academy/responsible-and-safe-use/ (Accessed: 21 May 2026).

Oracle (2026a) *Interface Map<K,V>*. Available at: https://docs.oracle.com/javase/8/docs/api/java/util/Map.html (Accessed: 21 May 2026).

Oracle (2026b) *Class EnumMap<K extends Enum<K>,V>*. Available at: https://docs.oracle.com/javase/8/docs/api/java/util/EnumMap.html (Accessed: 21 May 2026).