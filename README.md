# cse232b_project
Milestone 1: a naive evaluator for XPath

## Usgae
All commands should be run from the root of the repository.

### Step1: Generate ANTLR Parser
```bash
cd main/antlr
java -jar ../../lib/antlr-4.13.2-complete.jar -visitor -package main.antlr XPath.g4
```

### Step2: Compile
```bash
javac -cp lib/antlr-4.13.2-complete.jar main/*.java main/antlr/*.java
```

### Step3: Run query
```bash
java -cp lib/antlr-4.13.2-complete.jar:. main.Main test/j_caesar.xml test/queries/q1.txt test/output/q1_result.xml
```