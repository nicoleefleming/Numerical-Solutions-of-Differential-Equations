# Math5620 Finite Difference Methods of Ordinary and Partial Differential Equations
**Routine Name:**           test_convergence

**Author:** Nicole Fleming

**Language:** Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/IterativeMethods/*.java -d classes
    "FILE-PATH" -cp classes IterativeMethods.java
    
where the method belonds to the class IterativeMethods. 

**Description/Purpose:** This is the Java implementation that was written for the course math5620 in Spring 2020. This is the code strucure that works there, and was the model for the C++ parallel implementation of the code. 

**Input:** The inputs are the new x array, the old x array, and the dimension of n. 

**Output:** This method will return an integer, if it converges, the number will be 0. 

**Usage/Example:**  This is an inner method used by the Jacobi method to test the convergence of the method. To test it, a for loop can be added to display what values are found. 

**Implementation/Code:** 

public int test_convergence(double []x, double []oldx, int n)
    {
        double maxvalue, tempvalue;
        int i;
        double epsilon = 0.000000001;

        maxvalue = 0.0;
        for(i=0; i < n; i++)
        {
            tempvalue = Math.abs(x[i] - oldx[i]);
            if (tempvalue > maxvalue)
                maxvalue = tempvalue;

        } // for
        if (maxvalue > epsilon)
            return 0;
        else
            return 1;

    } // test_convergence
    
**Last Updated: 29/February/2020**
