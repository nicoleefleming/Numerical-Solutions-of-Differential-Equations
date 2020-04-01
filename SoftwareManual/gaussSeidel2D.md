# Math5620 Finite Difference Methods of Ordinary and Partial Differential Equations
**Routine Name:**           gaussSeidel2D

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

**Description/Purpose:** This code solves for the unknowns in a 2 dimensional problem. This solution solves for a 2 dimensional Poisson problem.

**Input:** The main diagonal, ad; the first super diagonal, as; the first subdiagonal, al; (the second superdiagonal, ud; the second subdiagonal, ld; if pentadiagonal)  the right hand side, b; the row dimension, n; and the mesh value, h. 

**Output:** This method will return the approximate answer for the matrix U based on the values passed in. The matrix U is assigned in the method, and solved with the way specified in the text which is returned.

**Usage/Example:**  This is an iterative method that will be used to solve the sparsely stored pentadiagonal matrix A, stored in ad, as, al. This code tests the method presented in the textbook on page 70 with a U to get new values in U. 
            
        //2D Poisson Problem with Gauss Seidel
        double[][] vars = new double[2][tenAns.length];
        double[][] tenVars = new double[2][tenAns.length];
        double yZero = 1.0;
        iters = 0;
        vars[0][0] = xZero;
        vars[0][1] = yZero;
        //use b but as RHS pde
        ua = 0;
        ub = 0;
        b = iter.RHSpdeInit(pdx, pdy, ua, ub);
        //vars needs to be related to b....
        double [][] Unew = new double[2][ad.length];
        while(iters < maxIters)
        {
            Unew = iter.gaussSeidel2D(ad, al, as, b, n, h);
            iters++;
            vars[0][0] = xZero/iters;
            vars[0][1] = yZero/iters;
        }
        int j;
        for(int i = 0; i < Unew.length; i++)
        {
            for (j = 0; j < Unew.length; j++)
            {
                System.out.println("Gauss-Seidel 2D: " + Unew[i][j]); //should be 81 terms
            }
            System.out.println();
        }
               

**Implementation/Code:** 

    public double[][] gaussSeidel2D(double[] ad, double[] as, double[] al, double[] b, int n, double h)
    {
        double[] newVars = new double[b.length];
        for (int i = 0; i < b.length; i++)
        {
            newVars[i] = b[i];
        }
        int nxny = n*n;
        double[][] Ups = new double[nxny][nxny];
            for(int j = 0; j < 2; j++)
            {
                for(int k = 0; k < ad.length; k++)
                Ups[j][k] = ad[j];
            }

            for (int i = 0; i < ad.length - 2; i++)
            {
                Ups[i][i+1] = as[i];
                Ups[i+1][i] = al[i];
            }

            int k = 0;
            for (int i = 1; i < ad.length - 1; i++)
            {
                for(int j = 1; j < ad.length - 1; j++)
                {
                    Ups[i][j] = (1/4) *(Ups[i-1][j] + Ups[i+1][j] + Ups[i][j-1] + Ups[i][j+1]);

                    Ups[i][j] -=  ((Math.pow(h, 2)/4)*newVars[j]);
                }
            }


        return Ups;
    }
    
**Last Updated: 31/March/2020**
