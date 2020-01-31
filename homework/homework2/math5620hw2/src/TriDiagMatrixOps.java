import java.util.*;
import java.lang.Math;

public class TriDiagMatrixOps {
    public static void main(String[] args)
    {
        TriDiagMatrixOps triOps = new TriDiagMatrixOps();
        int n = 3;                              //can change this to other numbers
                                                //have to initialize the size
        int[] al = new int[n];                  //first sub-diagonal
        int[] ad = new int[n];                  //main diagonal
        int[] as = new int[n];                  //first super-diagonal
        double[][] toSolve;                     //matrix for the tridiagonal to be housed in
        double[][] Coeffs;                      //matrix for the coefficients to be housed in
        double[] solution;                      //soulution for U(x) vector
        double[] b = {0, 0, 0};                 //hard coded to test for the second derivative case for coefficients
        int k = 2;                              //The order of the derivative
        double[] x = {(1.0/9), 0, -(1.0/9)};    //the values of F in book on pg 16 with
                                                //u(x) = cos(x).
        double xbar = 1;

        int test;
        //Test factorial
        test = triOps.factorial(6);
        System.out.println(test);

        //initialize the array,ut the values into the array
        toSolve = triOps.triDiagInit(al, ad, as, n);

        //k is the order of derivative
        //x is the values unknown
        //xbar is an arbitrary point
        //b a vector........ that will get changed in the function.


        //TODO: Send in a b[] to test with the method written to solve matrix system
        solution = triOps.triDiagSolve(toSolve, x); //b); ?

        for (int i = 0; i < n; i++)
        {
               System.out.println(solution[i] + " ");
        }
        System.out.println();
        //k is the order of derivative
        //x is the values unknown
        //xbar is an arbitrary point
        //b a vector........ that will get changed in the function
        Coeffs = triOps.findCoeffs(k, x, xbar, b);
        for(int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.println(Coeffs[i][j] + " ");
            }
            System.out.println("\n");
        }
        System.out.println("End of Main data");
    }


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

    public double[] triDiagSolve(double[][] toSolve, double[] b)//, int m)
    {
        int n = toSolve.length;
        int m = b.length;

        if(n != m)
        {
            System.out.println("The rows of the matrix A do not match the rows in the known solution vector.");
            return b;
        }

        double[][] A;  //the matrix that is being acted on.
        A = toSolve;                //assign the matrix to that which was passed in to make a copy
        double factor;                 //the factor that the GE calculates


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
        double x = b[n-1]/A[n-1][n-1];
        double[] xi = new double[n];

        for (int i = n-1; i >= 0; i--)
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

    public double[][] findCoeffs(int k, double[] x, double xbar, double[] b)
    {
        //TODO: Translate MATLAB code to JAVA
        int n = x.length;
        double[] xrow = new double[n];
        double[][] A = new double[n][n];

        if(x == null)
        {
            return null;
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                A[i][j] = 1.0;
            }
            b[i] = 0.0;
        }
        b[k] = 1.0;

        for (int i = 0; i < n; i++)
        {
            xrow[i] = x[i] - xbar;
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                A[i][j] = Math.pow(xrow[i], j) / factorial(i); //factorial 1*2*3*4...
            }
        }
        triDiagSolve(A, b);

        return A;
    }

    public int factorial(int calculate) {
        if (calculate <= 0) {
            calculate = 1;
            return calculate;
        } else {
            int res = 1, i;
            for (i = 2; i <= calculate; i++) {
                res *= i;
            }
            calculate = res;

            return calculate;
        }
    }
}
