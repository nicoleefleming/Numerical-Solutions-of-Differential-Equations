# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           nonLinearInit

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

**Input:** Tthere are four inputs. Three are 1D arrays for the main diagonal section of a centralized tri-diagonal matrix. the other is the dimension. 

**Output:** This routine returns a 2D array of the diagonals in each column for storage. 

**Usage/Example:**

This code can be used with the following set ups for al, ad, and as.    

      for (int i = 0; i < n; i++)     
        {    
            al[i]= 1;     
            ad[i] = ((-2)+(Math.pow(h,2)* cos(theta[i])));      
            as[i] = 1;      
        }   

The following code will send the code in the method to be solved.     

    toSolve = triOps.nonlinearFDSolve(al, ad, as, n, b, k, x, xbar, h);

        //test of toSolve for nonlinear case
        for (int i = 0; i < toSolve.length; i++) {
            for (int j = 0; j < toSolve[i].length; j++) {
                System.out.print(toSolve[i][j]);
                System.out.print("; ");
            }
            System.out.println();
        }
        
Inside the nonlinearFDSolve method, the call to this one is made with the following line of code     

      double[][] toSolve = nonLinearInit(al, ad, as, n);
      
To test just the line above was working correctly, the following code was used.     

     for (int i = 0; i < toSolve.length; i++) 
     {
            for (int j = 0; j < toSolve[i].length; j++) 
            {
                System.out.print(toSolve[i][j]);
                System.out.print("; ");
            }
            System.out.println();
      }

**Implementation/Code:** 

    public double[][] nonLinearInit(double[] al, double[] ad, double[] as, int n)
    {
        double[][] tridiag = new double[n][n];

        for (int i = 0; i < n-1; i++)
        {
            tridiag[i][i]   = ad[i];    //main diagonal
            tridiag[i][i+1] = as[i];    //first super-diagonal
            tridiag[i+1][i] = al[i];    //first sub-diagonal
        }

        return tridiag;
    }
         

**Last Modified:** 29/Februaru/2020
