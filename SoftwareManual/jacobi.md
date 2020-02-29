# Math5620 Finite Difference Methods of Ordinary and Partial Differential Equations
**Routine Name:**           jacobi

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

**Input:** A 2D matrix, A; the right hand side, a 1D matrix, b; the unknowns, a 1D matrix, x; and the dimension, n. 

**Output:** This method will return the approximate answer for the 1D array of unknowns in the basic matrix equation A * x = b. 

**Usage/Example:**  This is an iterative method that is used to approximate the values of the unknown values in matrices. This specific method was used in solving partial differential equation problems. It was written in February with intent to solve a equation of the form      
Δu = ∂u/∂x^2 + ∂u/∂y^2 = f(x,y)

The code can be tested by running it and printing out the results via a for loop. (Will insert test code when I have it written)

**Implementation/Code:** 

    public void jacobi(double A[][], double b[], int n, double x[])
    {

        int i, j, k, p;
        double []oldx;

        oldx =  new double [n];

        do {
            for (i=0; i < n; i++)
                oldx[i] = x[i];

            for (i=0; i < n; i++)
            {
                x[i] = b[i];
                for(j=0; j < n; j++)
                    if ( i != j)
                        x[i] = x[i] - A[i][j] * oldx[j];
                x[i] = x[i]/A[i][i];

            } // for

        } while(test_convergence(x, oldx,n) == 0);

    } //  jacobi
    
**Last Updated: 29/February/2020**
