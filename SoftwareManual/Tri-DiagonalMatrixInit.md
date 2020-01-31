# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           triDiagInit

**Author:** Nicole Fleming

**Language:** Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/TriDiagOps/*.java -d classes
    "FILE-PATH" -cp classes TriDiagOps.java
    
These commands should also work.

**Description/Purpose:** This routine will initialize a tridiagonal linear system, and initialize the right hand side of the equation.
This method can be used to initialize a tridiagonal matrix with 1 on the first sub-diagonal, 1 on the first super-diagonal, and -2 on the
main diagonal.

**Input:** There are four inputs: int[] al, int[] ad, int[] as, and int n. The first integer array is the array that will hold all the values
for the first sub-diagonal. The second integer array has the values for the main diagonal. The third integer array has the values for the
first super-diagonal. Integer 'n' is the size for the array. 'n' is the size for each diagonal array, and will give the dimensions for the
tri-diagonal matrix. 

**Output:** This routine returns a single two dimensional array of doubles that can be printed to the screen to verify values are in the correct
locations

**Usage/Example:**

The routine has four arguments needed to return the values after initializing the tri-diagonal matrix. The code is written using for loops, and 
the data type is a double due to needing more precision with other Matrix Operations that use the tri-diagonal matrix.

       toSolve = triOps.triDiagInit(al, ad, as, n);
       for(int i = 0; i < n; i++)
       {
          for(int j = 0; j < n; j++)
          {
              System.out.println(toSolve[i][j] + " ");
          }
          System.out.println("\n");
       }

Output from the lines above where n is equal to 3 and n is equal to 7:

      -2 1 0
      1 -2 1
      0 1 -2
      
      -2 1 0 0 0 0 0
      1 -2 1 0 0 0 0
      0 1 -2 1 0 0 0 
      0 0 1 -2 1 0 0 
      0 0 0 1 -2 1 0
      0 0 0 0 1 -2 1
      0 0 0 0 0 1 -2

The first matrix shows that the main diagonal is printed as expected, with one zero in either corner. The second was proof of concept to 
verify that the code worked for 'n' equal to sizes bigger than 3. 

**Implementation/Code:** The following is the code for triDiagInit

public double[][] triDiagInit(int[] al, int[] ad, int[] as, int n)
    {
        //assign values based on matrix located on page 16 in the textbook
        for(int i = 0; i < n; i++)
        {
            al[i] = 1;
            ad[i] = 2;
            as[i] = 1;
        }

        //create matrix that stores al, ad, as
        double[][] tridiag = new double[n][n];

        for (int i = 0; i < n-1; i++)
        {
            tridiag[i][i]   = ad[i];    //main diagonal
            tridiag[i][i+1] = as[i];    //first super-diagonal
            tridiag[i+1][i] = al[i];    //first sub-diagonal
        }

        return tridiag;
    }

**Last Modified:** 30/January/2020
