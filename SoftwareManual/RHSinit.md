# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           RHSinit

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

**Description/Purpose:** This routine will solve for the values of the right hand side, in the simplist case, this means that all of f(x) = 0. Using the Dirichlet Boundaries, f(x0) and the last f(x) subtract the boundary conditions given. This method returns the right hand side vector.

**Input:** There are  inputs: double[] fx, double ua, double ub. The array fx is only used for the length. This could be changed to an int or double for the purpose of getting the size of the array. The boundary conditions are given by ua and ub, where ua is the lower condition, and ub is the higher condition. If ua is equal to ub, in a homogeneous case, it won't matter which value is which when passed in.

**Output:** This routine will return a one dimensional array rhs. The rhs array is then used in the parent method that calls it. For size 10 array where f(x) = 0, and ua = 1, and ub = 3, the rhs was
  
    -1.0
    0.0
    0.0
    0.0
    0.0
    0.0
    0.0
    0.0
    0.0
    -3.0    

**Usage/Example:**

The routine has two arguments the 1D array, and two boundary conditions. To test that it was working the following lines of code were used.
                  
        rhs1 = linsolve.RHSinit(b, ua, ub);

        for (int i = 0; i < n; i++)
        {
            System.out.println(rhs1[i] + " ");
        }

**Implementation/Code:** The following is the unfinished code for DirichletSolve
 
    public double[] RHSinit(double[] fx, double ua, double ub)
    {
        //init array to what the formula is
        int n = fx.length;
        double[] rhs = new double[n];

        //function
        for (int i = 0; i < ub; i++)
        {
            rhs[i] = 0.0;
            //rhs[index] = 10*sin(2*pi*i);
        }
        //Dirichlet conditions, f(x0) - ua/h^2
        rhs[0] = rhs[1] - ua;
        rhs[n-1] = rhs[n-1] - ub;

        return rhs;
    }
     

**Last Modified:** 11/February/2020
