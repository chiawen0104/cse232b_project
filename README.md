# cse232b_project
Milestone 2: an evaluator for XQuery

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

### Step3: Run Query
```bash
java -cp lib/antlr-4.13.2-complete.jar:. main.Main data/j_caesar.xml data/queries/q6.txt data/result/q6.xml
```

### Step4: Package
```bash
zip -r submission.zip main/
```