# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           NeumannSolve

**Author:** Nicole Fleming

**Language:** Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/TriDiagOps/*.java -d classes
    "FILE-PATH" -cp classes TriDiagOps.java
    
where the method belonds to the class TriDiagOps. These commands should also work.

**Description/Purpose:** This routine will take input to solve a Boundary Value Problem with Neumann conditions. As of February 10th it has yet to be written.

**Input:** 

**Output:** 

**Usage/Example:**




**Implementation/Code:** The following is the code for triDiagSolve



**Last Modified:** 10/February/2020
