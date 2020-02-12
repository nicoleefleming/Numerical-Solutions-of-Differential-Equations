import static java.lang.Math.cos;
import static java.lang.Math.sqrt;
import java.io.File;
import java.io.*;

public class TriDiagMatrixOps extends LinearSolvers{
    double[] B;
    public static void main(String[] args)
    {
        TriDiagMatrixOps triOps = new TriDiagMatrixOps();
        LinearSolvers linsolve = new LinearSolvers();
        int n = 200;                              //can change this to other numbers
        //have to initialize the size
        double pi = 3.14159265359;
        int[] al = new int[n];                  //first sub-diagonal
        int[] ad = new int[n];                  //main diagonal
        int[] as = new int[n];                  //first super-diagonal
        double[][] toSolve;                     //matrix for the tridiagonal to be housed in
        double[][] Coeffs;                      //matrix for the coefficients to be housed in
        double[][] ts3A;
        double[] solution;                      //soulution for U(x) vector
        double[] b = new double[n];                 //hard coded to test for the second derivative case for coefficients
        int k = 2;                              //The order of the derivative
        double[] x = new double[n];        //the values of F in book on pg 16 with
        //u(x) = cos(x). -(1.0/9), 0,  -(1.0/9)
        double xbar = 1;
        int test;

        for (int i = 0; i < n; i++)
        {
            if(i %2 ==0)
            {
                x[i] = 0;
            }
            else
            {
                x[i] = -(1.0/9);
            }
        }

        //assign values based on matrix located on page 16 in the textbook
        for(int i = 0; i < n; i++)
        {
            al[i] = 1;
            ad[i] = -2;
            as[i] = 1;
        }

        //Test factorial
        test = triOps.factorial(6);
        //System.out.println(test);

        //initialize the array,ut the values into the array
        toSolve = triOps.triDiagInit(al, ad, as, n);

        //solve for b
        solution = triOps.triDiagSolve(toSolve, x); //b); ?

        //test
//        for (int i = 0; i < n; i++)
//        {
//            System.out.println(solution[i] + " ");
//        }
//        System.out.println( "Solution");

        //k is the order of derivative
        //x is the values unknown
        //xbar is an arbitrary point
        //b a vector........ that will get changed in the function
        Coeffs = triOps.findCoeffs(k, x, xbar, b);
        //test
//        for(int i = 0; i < n; i++)
//        {
//            for (int j = 0; j < n; j++)
//            {
//                System.out.println(Coeffs[i][j] + " ");
//            }
//            System.out.println("\n");
//        }



        double m = 7;                           //This is the mesh size. It can be changed. (For ts3 it will be 7, then 255)
        //double m = 255;
        double h = 1/(m+1);                     //The O(h) h part. (denominator in limit definition of derivative)
        double ua = 1;                          //The known endpoint lower in Dirichlet BVP
        double ub = 3;                          //The known endpoint upper in Dirichlet BVP

        //TODO: Send Tri-Diagonal A into LUdecomp
        //initialize A
        ts3A = triOps.triDiagInit(al, ad, as, n);
        //multiply the matrix A by 1/h^2 to get A on page 16 of the text.
        for (int i = 0; i < ts3A.length; i++)
        {
            for (int j = 0; j < ts3A.length; j++)
            {
                ts3A[i][j] = (ts3A[i][j] * (1/Math.pow(h,2)));
            }
        }

        //LU factorization
        Coeffs = triOps.findCoeffs(k, x, xbar, b);

        //if it is a square, this works for all coefficient matrix
        Coeffs = triOps.LUfac(Coeffs);

        //LU factorization
        ts3A = triOps.LUfac(ts3A);

        //test for correct values
//        for (int i = 0; i < ts3A.length; i++)
//        {
//            for (int j = 0; j < ts3A.length; j++)
//            {
//                System.out.println(ts3A[i][j] + " ");
//            }
//            System.out.println("\n");
//        }

        //RHS initialize
        b = linsolve.RHSinit(b, 1,3);

        //test for values
//        for (int i = 0; i < n; i++)
//        {
//            System.out.println(b[i] + " ");
//        }
//        System.out.println("\n");

        //Dirichlet Solver
        //f(x) = 0
        solution = linsolve.DirichletSolve(ts3A, b, ua, ub);

        //TODO: f(x) = 10*sin(2i*pi)
        //solution =

        //test for values
        for (int i = 0; i < n; i++)
        {
            System.out.println(solution[i] + " ");
        }

        //TODO: Neumann Solver


        System.out.println("End of Main data");
    }

    public double[][] triDiagInit(int[] al, int[] ad, int[] as, int n)
    {
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
       backward(A, b);

        return b;
    }

    public double[][] findCoeffs(int k, double[] x, double xbar, double[] b)
    {

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

    public double[][] LUfac(double[][] array)
    {
        int n = array.length;
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

    public double[][] AminusLambda(double[][] a, double[] l)
    {
        double[][] minus = a;

        for (int i = 0; i < minus.length; i++)
        {
            minus[i][i] -= l[i];
        }

        return minus;
    }

    public double[] AssignLambda(double[][] A, double[] lambda, double h, double b1, double c, double pi)
    {
        for (int i = 0; i < A.length; i++)
        {
            lambda[i] = A[i][i] - (2*b1)*sqrt(c/b1)*cos(h*pi*i);
        }

        return lambda;
    }


}
