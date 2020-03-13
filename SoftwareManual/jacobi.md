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

**Input:** The main diagonal, ad; the first super diagonal, as; the first subdiagonal, al; the second superdiagonal, ud; the second subdiagonal, ld;  the right hand side, b; the unknowns, x; the row dimension, n; and the column dimension, m. 

**Output:** This method will return the approximate answer for the 1D array of unknowns in the basic matrix equation A * x = b. It returns xnew.

**Usage/Example:**  This is an iterative method that will be used to solve the sparsely stored pentadiagonal matrix A. 
The code can be tested by running it and printing out the results via a for loop.
            
        solution = iter.jacobi(ad, as, al, ud, ld, b, x, ad.length, ad.length);

        for (int i = 0; i < solution.length; i++)
        {
            System.out.println("Jacobi Solution :" + solution[i] +" ");
        }
               

**Implementation/Code:** 

    public double[] jacobi(double[] ad, double[] as1, double[] al1, double[] as2, double[] al2, double[] b, double[] x, int n, int m)
    {
        int nx = n; //length of row
        int ny = m; //length of column
        int nxny = nx*ny;
        double[] xold = x;
        double[] xnew = new double[nxny];

        for(int i = 0; i < nxny; i++)
        {
            xnew[i] = b[i];
        }

        for (int i = 0; i < nxny - 1; i++)
        {
            xnew[i] = xnew[i] - (as1[i]*xold[i + 1]);
            xnew[i] = xnew[i] - (al1[i]*xold[i]);
        }

        for (int i = 0; i < nxny - nx; i++)
        {
            xnew[i] = xnew[i] - (as2[i] * xold[i + nx]);
            xnew[i] = xnew[i] - (al2[i] * xold[i]);
        }

        for (int i = 0; i < nxny; i++)
        {
            xnew[i] = xnew[i]/ad[i];
        }

        return xnew;
    }
    
**Last Updated: 29/February/2020**
