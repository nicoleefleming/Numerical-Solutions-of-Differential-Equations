# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           DirichletSolve

**Author:**                 Nicole Fleming

**Language:**              Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/TriDiagOps/*.java -d classes
    "FILE-PATH" -cp classes TriDiagOps.java
    
These commands should also work.

**Description/Purpose:** This routine will solve for the V values as found when computing the Dirichlet method in class. This method remains unfinished due to a lot of confusion behind what was supposed to be happnening and how to calculate the values to graph. Most likely it is due to a lack of understanding, or an overcomplication of simple things.

**Input:** There are  inputs:

**Output:** This routine remains unfinished, and no output was Tested. 

**Usage/Example:**

The routine has eight arguments. Ideally this routine would return two files, Vvalues and X where these could then be used to graph the result in MatLab to include here.

**Implementation/Code:** The following is the unfinished code for DirichletSolve

     

**Last Modified:** 10/February/2020
