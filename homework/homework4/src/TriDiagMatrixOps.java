import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.io.File;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


public class TriDiagMatrixOps extends LinearSolvers{
    double[] B;
    public static void main(String[] args)
    {
        TriDiagMatrixOps triOps = new TriDiagMatrixOps();
        LinearSolvers linsolve = new LinearSolvers();

        //Create a file object to import data. Handles data in one line, if needed can be altered to multi-line import
        File file = new File("C:\\Users\\Nicole\\IdeaProjects\\homework2\\math5620hw2\\input.txt");
        double[] Kfile = {2.867333,  4.569805,  3.316993,  8.092610,  8.140566,  0.143155,  6.342265,  5.241447, 6.605599,  0.933701,  3.324111,  8.192863,  7.079724,  7.245939,  2.395458,  1.355006, 2.288142,  5.042498,  7.306108,  4.821304,  7.771159,  7.423749,  9.122419,  8.120050, 3.580566 , 3.393176 , 8.418107 , 2.601973 , 0.217588 , 3.894094 , 4.362981,  5.842914, 0.907254,  4.713689,  7.163621,  8.341334,  7.430906,  3.820236,  0.691511,  8.002433, 0.649783,  9.228502,  6.750084,  8.722010 , 9.548238,  4.976954,  1.182421,  6.113974, 3.805117,  8.248314,  8.706021,  1.765543,  8.366169,  6.572338,  0.780077,  7.053698, 9.839133,  7.316398,  1.064157,  6.615321,  2.466196,  8.995519,  7.014925,  9.431717, 5.246028,  9.218961,  3.189759,  8.365877,  1.979470,  9.335279,  6.782625,  5.677813, 7.699140,  4.541973,  6.108508,  5.039710,  6.613854,  1.989440,  6.377985 , 9.878402, 1.553754,  0.131856,  6.063194,  9.487799,  1.741344,  6.059971 , 7.150494,  4.978408, 8.837417,  9.392483,  4.803539,  9.965004,  0.118921,  5.103728,  9.323259,  2.436715, 7.738321,  0.125891,  1.088443,  4.506494,  0.890854,  4.083744,  0.392129,  3.998879, 7.077300,  5.999135,  0.354940,  8.906408, 7.295522,  4.941008,  5.796811,  4.225764, 3.623979, 6.294709,  2.683174,  9.874883,  9.374789,  1.681627,  4.830779,  6.591775, 6.992642,  2.776946,  0.961683,  5.724664,  8.758983,  9.249325,  2.331766,  8.641368};
        //Kfile = triOps.input(file);
        //test and verify input is working
       /* for (int i = 0; i < Kfile.length; i++)
        {
            System.out.println(Kfile[i] + " ");
        }*/

        int n = 100; //Kfile.length;                              //can change this to other numbers
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
        double[] x = new double[n];// =  {0, 1, 2}; //= {-(1.0/9), 0,  -(1.0/9)};        //the values of F in book on pg 16 with
        //u(x) = cos(x). -(1.0/9), 0,  -(1.0/9)
        double xbar = 1;
        int test;


        //Init x values if f(x) = x-x^2
        for (int i = 0; i < n; i++)
        {
            x[i] = Math.sin((pi/2)*i);// = x[i] - Math.pow(x[i],2);
        }

        //assign values based on matrix located on page 16 in the textbook
        for(int i = 0; i < n; i++)
        {
            al[i] = 1;
            ad[i] = -2;
            as[i] = 1;
        }

        //Test factorial
        //test = triOps.factorial(6);
        //System.out.println(test);

        //initialize the array,ut the values into the array
        //toSolve = triOps.triDiagInit(al, ad, as, n);

        //solve for b
        //solution = triOps.triDiagSolve(toSolve, x); //b); ?

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
        //Coeffs = triOps.findCoeffs(k, x, xbar, b);

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
        double ua = 0;                          //The known endpoint lower in Dirichlet BVP
        double ub = 0;                          //The known endpoint upper in Dirichlet BVP

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
        //Coeffs = triOps.findCoeffs(k, x, xbar, b);

        //if it is a square, this works for all coefficient matrix
        //Coeffs = triOps.LUfac(Coeffs);

        //LU factorization
        //ts3A = triOps.LUfac(ts3A);

        //test for correct values
//        for (int i = 0; i < ts3A.length; i++)
//        {
//            for (int j = 0; j < ts3A.length; j++)
//            {
//                System.out.println(ts3A[i][j] + " ");
//            }
//            System.out.println("\n");
//        }


        double[][] arrayForError = new double[4][b.length];
        double[] eNorm = new double[b.length];
        double[] H = new double[eNorm.length];
        double b4half;
        double[] logH = new double[H.length];
        double[] logerr = new double[eNorm.length];
        double[] U = new double[b.length];
        double[][] twoBytwo = new double[2][2];
        double[] error = new double[U.length];
        double[] kX = new double[U.length];

        //RHS initialize
        b = linsolve.RHSinit(b, 0,0);

        for (int i = 0; i < kX.length; i++)
        {
            kX[i] = sin(pi*i);
        }


        //b = linsolve.funcDirichlet(kX, 0, 0, b, h);

        //initialize H array
        for (int i = 0; i < H.length; i++)
        {
            if(i ==0)
            {
                H[i] = h;
            }
            else
            {
                H[i] = (1.0/(2*i))*h;
            }
        }

        //calculate the u(x) values with h
        for (int i = 0; i < b.length; i++)
        {
            U[i] = H[i] *(((1.0/6) * Math.pow(i, 3)) - ((1.0/12) * Math.pow(i, 4)) - ((1.0/12)*i));
            //U[i] = H[i] *(sin(pi*i));

        }

        for (int i = 0; i < U.length; i++)
        {
            //b needs to incorporate H
            b[i] = b[i] * H[i];
            //calculate E with H
            error[i] = Math.abs(b[i] - U[i]);
        }

        //initialize ||E|| array
        for (int i = 0; i < eNorm.length; i++)
        {
            b4half = (Math.abs(Math.pow((error[i]), 2)));
            eNorm[i] = Math.pow(b4half, (1.0/2));
            eNorm[i] = H[i] * eNorm[i];
        }

        //log(h)
        for (int i = 0; i < logH.length; i++)
        {
            logH[i] = Math.log(H[i]);
        }

        //log(||E||)
        for (int i = 0; i < logerr.length; i++)
        {
            logerr[i] = Math.log(eNorm[i]);
        }

        //assign array4error values, H, eNorm, logH, logerr
        for (int i = 0; i < H.length; i++)
        {
            arrayForError[0][i] = H[i];
            arrayForError[1][i] = eNorm[i];
            arrayForError[2][i] = logH[i];
            arrayForError[3][i] = logerr[i];
        }

        double length = H.length;
        double P = 0;
        P = linsolve.calculateP(arrayForError, length);


        System.out.println("p is " + P);
        //test for value of p
        //System.out.println("The value of p: " + p[1] +"." );


        //test for values of U_j (b) and u(x_j) (U)
//        for (int i = 0; i < U.length; i++)
//        {
//            System.out.println(U[i] + " U at index " + i);
//        }
//
//        for (int i = 0; i < b.length; i++)
//        {
//            System.out.println(b[i] + " b at index " + i);
//        }


        //test for values
//        for (int i = 0; i < n; i++)
//        {
//            System.out.println(b[i] + " ");
//        }
//        System.out.println("\n");

        //Dirichlet Solver
        //f(x) = 0
        //solution = linsolve.DirichletSolve(ts3A, b, ua, ub);


        //test for values
//        for (int i = 0; i < n; i++)
//        {
//            System.out.println(solution[i] + " ");
//        }

        //Initialize Matrix
        Coeffs = triOps.findCoeffs(2, x, 1, b);
        //Assign values mulitplied by 1/h^2
        {
            for (int j = 0; j < n; j++)
            { for (int i = 0; i < n; i++)

                Coeffs[i][j] = (Coeffs[i][j] * 1/(Math.pow(h,2)));
            }
        }

        //b = linsolve.NeumannSolve(Coeffs, b, 1, 3);

        //Test for Neumann with values from Finite Difference Calc
//        for(int i = 0; i < n; i++)
//        {
//            System.out.println(b[i] + "\n");
//        }

        double[] aX = new double[n];


        aX = linsolve.aOfXinit(aX);
        //test AX init
//        for(int i = 0; i < aX.length; i++)
//        {
//            System.out.println(aX[i] + " ");
//        }

        kX = linsolve.aXaverage(aX, kX);
        //test a(x) avg
//        for (int i = 0; i < kX.length; i++)
//        {
//            System.out.println(kX[i] + " ");
//        }
//
        //Find the answer of adding the given k from file
        solution = linsolve.funcDirichlet(Kfile, ua, ub, x, h);


        //solution = linsolve.funcDirichlet(kX, ua, ub, Coeffs, x, h);
        //test for TS4 #4
        for (int i = 0; i < solution.length; i++)
        {
            System.out.println(solution[i] + " ");
        }


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

    public static double[] input(File file)
    {
        String expression = "";
        try {
            //created a new File object passed in

            //create a new Scanner
            Scanner read = new Scanner(file);

            //scans file and puts into string to convert.
            expression = read.nextLine();
        }
        catch (FileNotFoundException error) {
            //will print ONLY if the file is not found. Will print in RED
            System.err.println("Did not find file");
        }

        //put read in string into string array and split
        String[] toInt = expression.split(" ");

        // Create new array to store integers from conversion of string array
        double[] ints = new double[toInt.length];

        //Debugging to make sure it is correct
        //System.out.println(ints);

        //convert string to array of integers
        for(int i = 0;i < toInt.length;i++)
        {
            try
            {
                ints[i] = Double.parseDouble(toInt[i]);
                ints[i] = ints[i];
            }
            catch (NumberFormatException nfe)
            {
                ints[i] = Double.parseDouble("0.0");
            }
        }
        double[] sendME = new double[toInt.length];
        sendME = removeNumber(ints, 0.0);

        //return array of doubles
        return sendME;
    }

    public static double[] removeNumber(double[] input, double number)
    {
        LinkedList<Double> result = new LinkedList<Double>();

        for (int item = 0; item < input.length; item++) {
            if (input[item] != number) {
                result.add(input[item]);
            }
        }


        Double[] seeME = result.toArray(new Double[result.size()]);
        double[] parsed = new double[seeME.length];
        for (int i = 0; i < seeME.length; i++)
        {
            parsed[i] = seeME[i];
        }
        return parsed;
    }
}
