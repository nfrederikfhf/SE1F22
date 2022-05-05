Run instructions
================
This project is run by running the GUI class, as it accesses the main menu for
Softwarehuset A/S's projectplanner software. The inputs in the GUI is entered
in the terminal, by following the on-screen instructions.

An example of GUI being run
===========================
```
GUI.main();
```
Then the following output is given:
```
Welcome to the project planner for Softwarehuset A/S
Following operations are available: 
 1: Create project 
 2: Add activity 
 3: Add worker 
 4: Register time 
 5: Assign project manager 
 6: Rename project 
 7: Rename activity 
 8: Edit registered time 
 9: Get status
 0: Exit 
```
Then by inputting
> 1
into the terminal, you select option 1: and as such are presented with:
```
Input project name:
```
As such by following the on-screen instructions, the rest of the program 
can be executed

Functionallity restrictions
===========================
When using the GUI, you cannot add an activity to a non existant project,
and as such an error message will be outputted.

Running Cucumber and JUnit tests
================================
Running the file
> AcceptanceTest
with JUnit, will run all the Cucumber feature files.
If inclusion of JUnit whitebox tests is wanted, the run configuration
for JUnit needs to include the whole project.
