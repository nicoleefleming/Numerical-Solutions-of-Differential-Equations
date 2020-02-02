# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           findCoeffs

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

**Description/Purpose:** This routine will solve for the coefficients of a tridiagonal linear system.

**Input:** There are four inputs: int k, double[] x, double xbar, double[] b. The first integer k, is 
the order of the derivative. The double array x has the values of x calcualted in the elliptic problem, and is defined as the F matrix.
The third xbar, represents an arbitrary point that is what the equation is centered on. The last element
that is passed in is the double array b, which will change to help aide in solving for the coefficients and 
will be returned with the coefficient values when the method is finished

**Output:** This routine returns a single dimensional array of doubles that can be printed to the screen to verify 
values. When solving by hand, the same values were found, in their respective fraction forms.

**Usage/Example:**

The routine has four arguments needed to return the values after solving for the coefficients. The code is written using for loops, and 
the data type is a double due to needing more precision with the output.

        Coeffs = triOps.findCoeffs(k, x, xbar, b);
        for(int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.println(Coeffs[i][j] + " ");
            }
            System.out.println("\n");
        }

Output from the lines above where k is 2, x is the vector { (1/9), 0, -(1/9) }, xbar is 1, and b is passed in then initialized to the vector { 0,0,1 } where 
the 1 is in the kth position:

      1.0 
      -0.8888888888888888 
      0.7901234567901234 
      
      
      1.0 
      -0.16666666666666674 
      0.3240740740740742 
      
      
      0.5 
      -0.09259259259259256 
      0.001714677640603568 

The first section of three shows the values found for the original equation, and is printed as expected, matching the work computed prior to running. As do the second and third. The answers differ by the amount of digits displayed.
This is for one case, using the above specified values to pass into the function. 

**Implementation/Code:** The following is the code for findCoeffs

    public double[][] findCoeffs(int k, double[] x, double xbar, double[] b)
    {
        //TODO: Translate MATLAB code to JAVA
        int n = x.length;
        double[] xrow = new double[n];
        double[][] A = new double[n][n];

        if(x == null)
        {
            return null;
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                A[i][j] = 1.0;
            }
            b[i] = 0.0;
        }
        b[k] = 1.0;

        for (int i = 0; i < n; i++)
        {
            xrow[i] = x[i] - xbar;
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                A[i][j] = Math.pow(xrow[i], j) / factorial(i); //factorial 1*2*3*4...
            }
        }
        triDiagSolve(A, b);

        return A;
    }

**Last Modified:** 31/January/2020
