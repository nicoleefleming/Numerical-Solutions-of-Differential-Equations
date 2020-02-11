# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           forward

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

**Description/Purpose:** This routine will solve for the upper and lower triangle of a tridiagonal linear system.

**Input:** There are two inputs: double[][] array, double[] b. The array is the tridiagonal matrix. This method can be used on the coefficients, and the initial matrix. It can also be used to solve for other square matrix LU factorizations.

**Output:** This routine returns a two dimensional array of doubles that can be printed to the screen to verify 
values. This is a substitution method developoped for ease of using and implementing LU facotrization.

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

        The addition of a System.out.println(); in the forward loops was used in the method. The commented code is seen in the implementation.

Here is the output from printing in the forward method:

      128.0, 0.0, 0.0 
      
      64.0, 96.0, 0.0
      
      0.0, 64.0, -42.666666666666664 

The first section of three shows the values found for the original equation, and is printed as expected, matching the work computed prior to running. As do the second and third.  

**Implementation/Code:** The following is the code for forward

    public static double[] forward(double[][] mat, double b[]) {

        int nrow = mat.length;
        //double sol[] =new double[nrow];

        for (int r=0;r<nrow; r++)
        {
            double val =0;
            for (int c=0;c<r; c++) {
                val =val +  b[c] *mat[r][c];
            }
            val = b[r] - val;
            b[r] = val/mat[r][r];
        }
        
     //   for(int i = 0; i < mat.length; i ++)
     //   {
     //       System.out.println(b[i] + ",");
     //   }
        
        return b;
    }

**Last Modified:** 10/February/2020
