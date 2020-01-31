# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           triDiagSolve

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
    
where the method belonds to the class TriDiagOps. These commands should also work.

**Description/Purpose:** This routine will solve a tridiagonal linear system with a passed in array for the right hand side of the equation.
This method can be used to solve a tridiagonal matrix for the elliptic problem with 1 on the first sub-diagonal, 1 on the first super-diagonal, and -2 on the
main diagonal. The right had side vector b will be passed in but will be altered in solving the matrix, but maintaining the derivative order.

**Input:** There are two inputs: double[][] toSolve, double[] b. The first array is the array that will hold all the values for the tri-diagonal
matrix. The second array has the values for the right hand side. Both of these have been initialized to sizes 'n'. 

**Output:** This routine returns a single one dimensional array, b, that is altered when going through the solving process and returns the values for 
the right hand side when solving the elliptic program. 

**Usage/Example:**

The routine has two arguments required to perform the operations to return a matrix with the values after solving the tri-diagonal matrix. 
The code is written using for loops, and logical statements. The data type is a double due to needing more precision with the numbers for the answers.

       solution = triOps.triDiagSolve(toSolve, x);
       for (int i = 0; i < n; i++)
        {
                System.out.println(solution[i] + " ");
        }

Output from the lines above where n is equal to 3, toSolve is the initialized tri-Diagonal Matrix, and x contains the values calculated by hand for 
the second derivative case of the elliptic problem. Those are { 0.1111112, 0, -0.1111112 }. The solution matrix is:
     
     0.1111111111111111 
     -0.05555555555555555 
     -0.07407407407407407 

This solution matches that which was calculated by hand.

**Implementation/Code:** The following is the code for triDiagSolve

     public double[] triDiagSolve(double[][] toSolve, double[] b)
    {
        int r = toSolve.length;
        int m = b.length;

        if(r != m)
        {
            System.out.println("The rows of the matrix A do not match the rows in the known solution vector.");
            return b;
        }

        double[][] A;                            //the matrix that is being acted on.
        A = toSolve;                             //assign the matrix to that which was passed in to make a copy
        double factor;                           //the factor that the GE calculates


        //Gaussian Elimination
        for (int k = 0; k < n-1; k++)
        {
            for (int i = k + 1; i < n; i++)
            {
                factor = A[i][k]/A[k][k];
                for (int j = k + 1; j < n; j++)
                {
                    A[i][j] = A[i][j] - (factor * A[k][j]);
                }
                b[i] = b[i] - (factor * b[k]);
            }
        }

        //Back Substitution
        double x = b[r-1]/A[r-1][r-1];
        double[] xi = new double[r];

        for (int i = r-1; i >= 0; i--)
        {
            xi[i] = b[i];
            for (int j = i-1; j > n; j--)
            {
                xi[i] = xi[i] - (A[i][j] * x);
            }
            xi[i] = xi[i]/A[i][i];              //Not sure if this line is completely accurate
        }

        return b;
    }

**Last Modified:** 30/January/2020
