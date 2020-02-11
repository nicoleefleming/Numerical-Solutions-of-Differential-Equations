# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           LUfac

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

**Description/Purpose:** This routine will solve for the upper and lower triangle of a tridiagonal linear system.

**Input:** There are two inputs: double[][] array, double[] b. The array is the tridiagonal matrix. This method can be used on the coefficients, and the initial matrix. It can also be used to solve for other square matrix LU factorizations.

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

Output from the method:

      128.0, 0.5, 0.0 

      64.0, 96.0, 0.6666666666666666
      
      0.0, 64.0, -42.666666666666664 

The first section of three shows the values found for the original equation, and is printed as expected, matching the work computed prior to running. As do the second and third.  

**Implementation/Code:** The following is the code for LUfac

    private double[][] LUfac(double[][] array, double[] b)
    {
        int n = array.length;
        int m = b.length;


        if(n != m)
        {
            System.out.println("The rows of the matrix A do not match the rows in the known solution vector.");
            return array;
        }

        double[][] A;  //the matrix that is being acted on.
        A = array;                //assign the matrix to that which was passed in to make a copy
        double factor;                 //the factor that the GE calculates


        //LU Factorization
        for (int k = 0; k < n-1; k++)
        {
            for (int i = k + 1; i < n; i++)
            {
                factor = A[i][k]/A[k][k];
                for (int j = k + 1; j < n; j++)
                {
                    A[i][j] = A[i][j] - (factor * A[k][j]);
                }
                A[k][i]= factor; //b[i] = b[i] - (factor * b[k]);
            }
        }

        return A;
    }

**Last Modified:** 11/February/2020
