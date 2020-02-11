# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           backward

**Author:**                 Nicole Fleming

**Language:**              Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/Solution/*.java -d classes
    "FILE-PATH" -cp classes Solution.java
    
These commands should also work.

**Description/Purpose:** This routine will solve for the back sbstitution of solving for the upper and lower triangle of a tridiagonal linear system.

**Input:** There are two inputs: double[][] Umat, double[] b. Umat is the tridiagonal matrix. This method can be used on the coefficients, and the initial matrix. It can also be used to solve for other square matrix LU factorizations.This method when used in LUfactorization is called after forward substitution, and in Gaussian Elimination used in TriDiagMatrixOps.triDiagSolve it was used at the end.

**Output:** This routine returns a two dimensional array of doubles that can be printed to the screen to verify 
values. When solving by hand, the same values were found, in their respective fraction forms.

**Usage/Example:**

The routine has two arguments needed to return the values after solving for the coefficients. The code for testing is written using for loops, and the data type is a double due to needing more precision with the output.

       //LU factorization
        ts3A = triOps.LUfac(ts3A, b);

        for (int i = 0; i < ts3A.length; i++)
        {
            for (int j = 0; j < ts3A.length; j++)
            {
                System.out.println(ts3A[i][j] + " ");
            }
            System.out.println("\n");
        }
        The addition of a System.out.println(); in the backward loops was used in the method. The commented code is seen in the implementation.

Output from printing out inteh bacward method. Keep in mind this is after the forward substitution has been done. This returns the same array that the LUfac method does.

      128.0, 0.5, 0.0 

      64.0, 96.0, 0.6666666666666666
      
      0.0, 64.0, -42.666666666666664 

The first section of three shows the values found for the original equation, and is printed as expected, matching the work computed prior to running. As do the second and third.  

**Implementation/Code:** The following is the code for LUfac

      public static double[] backward(double[][] Umat,double b[]) {

        int n = Umat.length;
        int m = b.length;

        if(n != m)
        {
            System.out.println("Invalid length.");
            return b;
        }

        double x = b[n-1]/Umat[n-1][n-1];
        double[] xi = new double[n];

        for (int i = n-1; i >= 0; i--)
        {
            xi[i] = b[i];
            for (int j = i-1; j > n; j--)
            {
                xi[i] = xi[i] - (Umat[i][j] * x);
            }
            xi[i] = xi[i]/Umat[i][i];
            //System.out.println(xi[i] + ",");
        }

        return xi;
    }

**Last Modified:** 10/February/2020
