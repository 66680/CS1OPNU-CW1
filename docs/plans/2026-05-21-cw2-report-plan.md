# CW2 Project Reflection Report Plan

**Goal:** Produce a high-scoring `REPORT.md` reflection for CS1OPNU CW2 and export it as a PDF for Blackboard submission.

**Report Type:** Critical reflective technical report.

**Target Length:** 1,800-2,000 words, excluding the reference list if the module accepts references outside the word count. Keep below 2,000 unless Blackboard guidance says otherwise.

**Core Argument:** AI tools were useful as a planning, testing, and design-support aid, but the quality of the project came from human review, test-driven verification, and deliberate design decisions. The Singleton, Observer, and Factory patterns improved structure, but each introduced trade-offs that had to be managed.

---

## 1. CW2 Requirements Summary

Submission requirements:

- Root repository file must be named `REPORT.md`.
- Submit a PDF report to Blackboard Ultra.
- Include front-page fields:
  - Module Code: `CS1OPNU`
  - Assignment report Title: `Project Reflection`
  - Student Number
  - Actual hrs spent
  - Which Artificial Intelligence tools used

Marking breakdown:

- Introduction: 10%
- Analysis of AI Support in Software Development: 25%
- Analysis of Software Patterns in the Project: 25%
- Ethical and Legal Considerations: 20%
- Conclusion: 10%
- English quality, writing style, and references: 10%

The report must show critical thinking, self-reflection, and evaluation rather than just describing the code.

## 2. Recommended Word Budget

| Section | Target Words | Purpose |
|---|---:|---|
| Front matter | brief | Required metadata |
| Introduction | 180-220 | Introduce CW1 project and report focus |
| AI support analysis | 500-550 | Explain use, benefits, limits, learning impact |
| Pattern analysis | 500-550 | Evaluate Singleton, Observer, Factory with trade-offs |
| Ethical/legal considerations | 400-450 | AI integrity, privacy, misuse, accessibility, licensing |
| Conclusion | 180-220 | Summarise learning and recommendations |
| References | 6-8 entries | Support scholarship and writing quality |

## 3. Section-by-Section Design

### Introduction

Main points:

- Briefly describe the CW1 game: Java CLI multiplayer text adventure.
- Mention major features: rooms, items, puzzle, locked door, player switching, `give`, `inspect`, `hint`, win condition.
- State the report evaluates AI support and design patterns.
- Thesis: AI was helpful but required human judgement and testing; patterns improved modularity but had trade-offs.

Evidence from project:

- `README.md`
- `DEVELOPMENT_LOG.md`
- `mvn clean test`: 44 passing tests

### Analysis of AI Support in Software Development

Required subtopics:

- How AI Tools Were Used
- Benefits of Using AI Tools
- Challenges and Limitations
- Overall Impact on Learning and Development

Recommended content:

- AI helped plan the architecture, identify how to use Observer, and design tests.
- AI helped compare whether to implement networking and why not to because networking was optional and high risk.
- AI helped suggest BFS for `hint <target>`, but the code was verified with tests.
- Benefits: faster ideation, clearer breakdown, useful edge-case checklist, improved documentation.
- Limitations: AI suggestions can be too broad, may over-engineer, may miss course-specific constraints, and cannot replace understanding.
- Learning impact: helped expose trade-offs, but TDD and manual review made learning active rather than passive.

Concrete evidence:

- Commits: `834cb54`, `80e045e`, `e09a0e3`, `a5a254c`
- Tests: `PathFinderTest`, `GameEngineInteractionTest`, `ObserverPatternTest`
- README AI declaration

High-score angle:

- Avoid saying AI simply "wrote the code".
- Emphasise evaluation, adaptation, and testing.
- Reflect on academic integrity: AI use was declared and limited to permitted support.

### Analysis of Software Patterns in the Project

Required subtopics:

- How the Patterns Were Used
- Benefits of Using Software Patterns
- Challenges and Limitations
- Overall Impact on Your Project

Singleton:

- Used in `GameState`.
- Benefit: one shared state for players, rooms, active player, events, and win condition.
- Challenge: Singleton can make tests share state accidentally.
- Mitigation: `resetForNewGame()`.

Observer:

- Used with `Observable`, `Observer`, `EventLog`, and `Player`.
- Benefit: player actions notify other players without hard-coding output in the engine.
- Challenge: event messages can become noisy or duplicated in the CLI.
- Mitigation: player message inbox and tests for event delivery.

Factory:

- Used with `GameObjectFactory` and `WorldFactory`.
- Benefit: object creation and world setup are centralised.
- Challenge: template IDs are simple but can become rigid if the world grows.
- Mitigation: clear template errors and focused tests.

Additional design note:

- BFS `PathFinder` is not a required pattern but supports performance and architecture marks.

### Ethical and Legal Considerations

Required subtopics:

- Over-reliance on AI-generated content.
- Potential biases in AI-generated code or suggestions.
- Academic integrity and originality.
- Data handling and privacy.
- Broader ethical and legal implications.

Recommended content:

- Over-reliance risk: if code is accepted without understanding, learning and originality suffer.
- Bias/quality risk: AI may suggest common designs that are not suitable for the assignment.
- Academic integrity: AI use should be declared; final responsibility remains with the student.
- Privacy: the game uses fictional player names and no real personal data; no database, accounts, or network.
- GDPR: if extended to real user accounts, data minimisation and purpose limitation would matter.
- Misuse: low risk because it is a local CLI game, but shared systems could expose messages or user data if networking were added.
- Accessibility: CLI is simple and keyboard-based, but could be improved with clearer prompts and help text.
- Licensing: Java, Maven, and JUnit use standard tooling; dependencies should be cited or acknowledged.

### Conclusion

Recommended content:

- Summarise that AI improved planning and feedback loops but did not replace testing and understanding.
- Patterns improved organisation, collaboration mechanics, and maintainability.
- Main learning: design patterns are useful when tied to concrete problems, not when added only for appearance.
- Recommendation: use AI for alternatives, edge cases, and explanations; use tests and documentation to verify the final design.

## 4. Suggested References

Use 6-8 references. Suggested list:

1. Gamma, E., Helm, R., Johnson, R. and Vlissides, J. (1994) *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.
2. Oracle. Java documentation for collections such as `Map` and `EnumMap`.
3. JUnit Team. *JUnit 5 User Guide*. https://junit.org/junit5/docs/current/user-guide/
4. Information Commissioner's Office. *A guide to the data protection principles*. https://ico.org.uk/for-organisations/uk-gdpr-guidance-and-resources/data-protection-principles/a-guide-to-the-data-protection-principles/
5. GOV.UK. *Data protection*. https://www.gov.uk/data-protection
6. OpenAI. *Responsible and safe use of AI*. https://openai.com/academy/responsible-and-safe-use/
7. Refactoring.Guru. *Observer*. https://refactoring.guru/design-patterns/observer
8. Refactoring.Guru. *Singleton*. https://refactoring.guru/design-patterns/singleton

Use the Design Patterns book as the main pattern reference. Refactoring.Guru can support concise explanations, but avoid relying on it as the only source.

## 5. Implementation Plan for REPORT.md

### Task 1: Fill Report Metadata

Files:

- Modify: `REPORT.md`

Actions:

- Fill `Student Number`.
- Fill `Actual hrs spent`.
- Fill AI tools used.

Requires student input:

- Student number.
- Real hours spent.
- Confirmation of AI tools used.

### Task 2: Draft Introduction

Actions:

- Write 180-220 words.
- Mention project, major features, and evaluation focus.

Quality check:

- Does not repeat the README.
- Establishes a reflective argument.

### Task 3: Draft AI Support Section

Actions:

- Write 500-550 words.
- Use project-specific examples from commits, tests, and design decisions.
- Include benefits and limitations.

Quality check:

- Includes critical reflection, not just praise.
- Explains how AI output was verified.

### Task 4: Draft Software Patterns Section

Actions:

- Write 500-550 words.
- Cover Singleton, Observer, and Factory individually.
- Include benefits, challenges, mitigations, and project impact.

Quality check:

- Each pattern is tied to exact project classes.
- Includes trade-offs.

### Task 5: Draft Ethical and Legal Section

Actions:

- Write 400-450 words.
- Cover AI integrity, over-reliance, bias, privacy, GDPR, misuse, accessibility, and licensing.

Quality check:

- States that no real personal data is handled.
- Explains what would change if the project handled real users.

### Task 6: Draft Conclusion

Actions:

- Write 180-220 words.
- Summarise learning and recommendations.

Quality check:

- Does not introduce new evidence.
- Connects back to the thesis.

### Task 7: Add References

Actions:

- Add 6-8 references.
- Use consistent formatting.
- Include URLs and access dates if appropriate.

Quality check:

- All cited sources are relevant.
- At least one source supports design patterns, one supports testing, one supports privacy/ethics.

### Task 8: Export PDF

Actions:

- Convert `REPORT.md` to PDF.
- Recommended options:
  - VS Code Markdown PDF extension, or
  - `pandoc REPORT.md -o REPORT.pdf` if Pandoc is installed.

Quality check:

- PDF opens correctly.
- Headings, code names, and references render cleanly.
- No TODO fields remain.

## 6. Testing / Quality Assurance Plan for CW2

Report QA checklist:

- Required file name is `REPORT.md`.
- PDF version exists for Blackboard.
- Word count is under 2,000.
- All required sections are present.
- AI tools used are declared.
- Student number and hours are filled.
- Every claim about the project can be traced to code, tests, README, or git log.
- References are listed.
- No fabricated experiences or unsupported claims.
- No code output or AI text is presented as unreviewed personal work.

Technical evidence checklist:

- Run `mvn clean test`.
- Record final test result: currently 44 tests passing.
- Check `git log --oneline`.
- Check `git status --short`.
- Confirm README and docs support the claims made in REPORT.

Academic integrity checklist:

- The student reviews and personalises the report.
- The report accurately states AI use.
- The report reflects actual decisions made in the CW1 project.
- The report does not claim manual work that was not done.

## 7. High-Scoring Writing Strategy

Use this pattern in each analytical paragraph:

1. Claim: state what helped or what was difficult.
2. Evidence: name a specific class, test, commit, or feature.
3. Evaluation: explain why it mattered.
4. Limitation: identify a trade-off.
5. Reflection: state what you learned or would do differently.

Avoid:

- Generic praise of AI.
- Describing code without evaluating it.
- Listing patterns without discussing trade-offs.
- Making privacy claims without explaining what data the project actually handles.
- Leaving references to the final paragraph only.