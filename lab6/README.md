## Lab6.1

### e) Has your project passed the defined quality gate? Elaborate your answer.

Yes, the project passed the defined quality gate with the following results:
- Security Rating: A (0 issues)
- Reliability Rating: A (0 open issues)
- Maintainability Rating: A (30 open issues, 8M and 22L)
- Coverage: 79.4%
- Duplications: 0.0%
- Security Hotspots: 1

The 30 open issues are related to maintainability, where 1 is a Consistency issue and the other 29 are Intentionaly issues. 

### f) Explore the analysis results and complete with a few sample issues, as applicable.

| Issue Type            | Problem Description                                                                       | How to Solve                                                                                         |
| --------------------- | ----------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| Code Smell x3 (Minor) | Invoke method(s) only conditionally.                                                      | "Preconditions" and logging arguments should not require evaluationjava:S2629                        |
| Code Smell (Minor)    | Remove this unused import 'java.security.NoSuchAlgorithmException'.                       | Unnecessary imports should be removedjava:S1128                                                      |
| Code Smell (Minor)    | Remove this unused import 'java.security.SecureRandom'.                                   | Unnecessary imports should be removedjava:S1128                                                      |
| Code Smell x2 (Major) | Refactor the code in order to not assign to this loop counter from within the loop body.  | "for" loop stop conditions should be invariantjava:S127                                              |
| Code Smell (Minor)    | Reorder the modifiers to comply with the Java Language Specification.                     | Modifiers should be declared in the correct orderjava:S1124                                          |
| Code Smell (Minor)    | Replace the type specification in this constructor call with the diamond operator ("<>"). | The diamond operator ("<>") should be usedjava:S2293                                                 |
| Code Smell (Info)     | Complete the task associated to this TODO comment.                                        | Track uses of "TODO" tagsjava:S1135                                                                  |
| Code Smell x12 (info) | Remove this 'public' modifier.                                                            | JUnit5 test classes and methods should have default package visibilityjava:S5786                     |
| Code Smell (Major)    | Use assertEquals instead.                                                                 | JUnit assertTrue/assertFalse should be simplified to the corresponding dedicated assertionjava:S5785 |
| Code Smell x2 (Major) | Use assertNotEquals instead.                                                              | JUnit assertTrue/assertFalse should be simplified to the corresponding dedicated assertionjava:S5785 |


## Lab6.2

### a) Analyze this project with SonarQube. (...) Document the analysis findings with a screenshot (of the sonar dashboard for this project).

![Dashboard](/static/lab6_2a_overview.png)

### Technical debt

![Technical debt](/static/lab6_2a_technical_debt.png)

Technical debt in SonarQube is a measure of the estimated time required to fix all the code smells, bugs, and security vulnerabilities in your codebase. It's a way of quantifying the amount of work needed to bring the code up to the team's defined coding standards.

### b) Analyze the reported problems and be sure to correct the severecode smells reported 

This project has 7 Intentional code smells, all of them are related to the same issue: "Remove this 'public' modifier."

![Issues](/static/lab6_2b_issues.png)

### c) Run the static analysis and discuss the coverage values on the SonarQube dashboard (how many lines are “not covered”? And how many conditions? Are the values good?...)

![Coverage](/static/lab6_2c_coverage.png)

The coverage is good but could be better. There is 1 line/condition in CarRestController that should be covered. The other uncovered lines are just hashcode and equals methods and the main method of the application.

## Lab6.3

### After changing the overall code quality gate condition for Duplicated Lines (%) to 2%:

![Quality Gate](/static/lab6_3_quality_gate_1.png)

### After adding a if/else statement that returns the same value:

![Quality Gate](/static/lab6_3_quality_gate_2.png)