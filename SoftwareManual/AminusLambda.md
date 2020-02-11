# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           AminusLambda

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

**Description/Purpose:** This routine will solve for A - lambda. Both of these are a matrix, and this was thought to help solve for the Vvalues.

**Input:** There are two inputs: double[][] a and double[] l. The input is used to calculate the values on the diagonal of A after subtracting lambda.

**Output:** This routine returns a two dimensional array of doubles that can be printed to the screen to verify 
values. 

**Usage/Example:**

The routine has four arguments needed to return the values after solving for the coefficients. The code is written using for loops, and 
the data type is a double due to needing more precision with the output.

        ts3A = triOps.Aminuslambda(ts3A, lambda);
        for(int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.println(ts3A[i][j] + " ");
            }
            System.out.println("\n");
        }

Output from the lines above where lambda was hardcoded to be 0.5, and A is the matrix of coefficients before entering the method Aminuslambda:

      0.5
      -0.8888888888888888 
      0.7901234567901234 
      
      
      1.0 
      -0.66666666666666674 
      0.3240740740740742 
      
      
      0.5 
      -0.59259259259259256 
      0.001714677640603568 

The first section of three shows the values found for the original equation, and is printed as expected, with the first value on the diagonal being one lambda less than it was prior to entering the method (see [findCoeffs](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/findCoeffs.md) for original values). As do the second and third. 
This is for one case, using the above specified values to pass into the function. 

**Implementation/Code:** The following is the code for AminusLambda

    public double[][] AminusLambda(double[][] a, double[] l)
    {
        double[][] minus = a;

        for (int i = 0; i < minus.length; i++)
        {
            minus[i][i] -= l[i];
        }

        return minus;
    }

**Last Modified:** 10/February/2020
