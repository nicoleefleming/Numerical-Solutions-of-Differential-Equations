# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           aOfXinit

**Author:**                 Nicole Fleming

**Language:**              Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/LinearSolvers/*.java -d classes
    "FILE-PATH" -cp classes LinearSolvers.java
    
These commands should also work.

**Description/Purpose:** This method takes the function given k(x) and will solve for the values in the boundary conditions for k(x) at given points. It will then return the initialized array. 

**Input:** There is one input, an empty array. This array is assigned values and returned.

**Output:** This routine will return a one dimensional array aX. The array aX is assigned values based on the function given for k in the equation (k(x) * u')' = f(x). For example, if k(x) = 1 for all x, aX would be

      1.0
      1.0
      1.0
      ...to the last spot in the array.

**Usage/Example:**

The routine has one argument the 1D array. To test that it was working the following lines of code were used.
                  
        aX = linsolve.aOfXinit(aX);
        //test AX init
        for(int i = 0; i < aX.length; i++)
        {
            System.out.println(aX[i] + " ");
        }

**Implementation/Code:** The following is the unfinished code for DirichletSolve
 
    public double[] aOfXinit(double[] aX)
    {
        int n = aX.length;
        //function:
        //TS4 #1: a(x) is a constant, so a(x) = 1

        for (int i = 0; i < n; i ++)
        {
            aX[i] = sin(pi*i); //1.0;
        }

        //return the calculated values
        return aX;
    } 

**Last Modified:** 15/February/2020

