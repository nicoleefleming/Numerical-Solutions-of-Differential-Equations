# Math5620 Finite Difference Methods of Ordinary and Partial Differential Equations
**Routine Name:**           gaussSeidel

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

**Description/Purpose:** This code solves for the unknowns in a 1 dimensional problem. The iterative solution used here converges a lot faster than the jacobi method.  

**Input:** The main diagonal, ad; the first super diagonal, as; the first subdiagonal, al; (the second superdiagonal, ud; the second subdiagonal, ld; if pentadiagonal)  the right hand side, b; the unknowns, x; the row dimension, n; and the column dimension, m. 

**Output:** This method will return the approximate answer for the 1D array of unknowns in the basic matrix equation A * x = b. It returns xnew.

**Usage/Example:**  This is an iterative method that will be used to solve the sparsely stored pentadiagonal matrix A, stored in ad, as, al. Currently the method takes a tridiagonal matrix but to adjust for pentadiagonl there woule be an as1 and al1 added to be solved to. 
The code can be tested by running it and printing out the results via a for loop. Testing this was done with the Poisson problem rather than the elliptic. 
            
        //Poisson problem 1D with Gauss Seidel

        for (int i = 0; i < ad.length; i++)
        {
            ad[i] = 0;
        }

        iters = 0;
        x[0] = 1.0;
        while(iters < maxIters)
        {
            tenAns = iter.gaussSeidel(ad, al, as, b, x, n, n);
            iters++;
            x[0] = xZero/iters;
        }
        for (int i = 0; i < tenAns.length; i++)
        {
            System.out.println("Gauss Seidel solution: " + tenAns[i] + " ");
        }
               

**Implementation/Code:** 

    public double[] gaussSeidel(double[] ad, double[] as1, double[] al1, double[] b, double[] x, int n, int m)
    {
        int nx = n; //length of row
        int ny = m; //length of column
        int nxny = nx*ny;
        double[] xold = x;
        double[] xnew = new double[nxny];
        
        for (int i = 0; i < nxny; i++)
        {
            xnew[i] = b[i];
        }
        for (int i = 0; i < nxny - 1; i++)
        {
            xnew[i] = xnew[i] - (as1[i] * xold[i]);
        }
        //for (int i = 0; i < nxny - nx; i++)
        //{
        //    xnew[i] = xnew[i] - (al2[i] * xold[i]);
        //}
        double[] temp = new double[xnew.length];
        for (int i = 0; i < nxny - 1; i++)
        {
            xnew[i] = xnew[i]*al1[i];
            temp[i] = xnew[i];
        }
        //for(int i = 0; i < nxny - nx; i++)
        //{
        //    xnew[i] = xnew[i]*as2[i];
        //    temp[i] += xnew[i];
        //}
        for (int i = 0; i < nxny; i++)
        {
            xnew[i] = temp[i] + xnew[i]*ad[i];
        }
        return xnew;
    }
    
**Last Updated: 31/March/2020**
