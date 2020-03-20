# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           nonlinearFDSolve

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

**Input:** There are 10 inputs. The inputs al, ad, and as are 1D vectors and each hold a piece of the diagonal part of the matrix. The dimension, n is an integer.  integer k, is the order of the derivative. The double array x has the values of x calcualted in the elliptic problem, and is defined as the F matrix. The third xbar, represents an arbitrary point that is what the equation is centered on. The last element that is passed in is the double array b, which will change to help aide in solving for the coefficients and will be returned with the coefficient values when the method is finished

**Output:** This routine returns a 2D matrix of finite difference coefficients. 

**Usage/Example:**

The routine has 10 arguments. The method has an inner call to nonLinearInit, and findCoeffs. This effectively will solve the nonlinear system for the solution of Coefficients.

        toSolve = triOps.nonlinearFDSolve(al, ad, as, n, b, k, x, xbar, h);

        //test of toSolve for nonlinear case
        for (int i = 0; i < toSolve.length; i++) {
            for (int j = 0; j < toSolve[i].length; j++) {
                System.out.print(toSolve[i][j]);
                System.out.print("; ");
            }
            System.out.println();
        }

Depending on what x and b are set to will change the outcome of this method. 

**Implementation/Code:** The following is the code for findCoeffs

     public double[][] nonlinearFDSolve(double[] al, double[] ad, double[] as, int n, double[] b, int k, double[] x, double xbar, double h)
    {
        double[][] toSolve = nonLinearInit(al, ad, as, n);
        double[] xrow = new double[n];

        //test tri-diagonal matrix nonlinear initialized
        for (int i = 0; i < al.length; i++)
        {
            for (int j = 0; j < al.length; j++)
            {
                toSolve[i][j] *= (1/(Math.pow(h, 2)));
                System.out.println(toSolve[i][j] + " ");
            }
            System.out.println("\n");
        }
        //solve for the Finite Difference Coeffs.
        double[][] sol = new double[n][n]; // = findCoeffs(k, x, xbar, b);

        sol = toSolve;
        b[k] = 1.0;

        for (int i = 0; i < n; i++)
        {
            xrow[i] = x[i] - xbar;
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                sol[i][j] = Math.pow(xrow[i], j) / factorial(i); //factorial 1*2*3*4...
            }
        }
        triDiagSolve(sol, b);

        return sol;
    }

**Last Modified:** 29/February/2020
