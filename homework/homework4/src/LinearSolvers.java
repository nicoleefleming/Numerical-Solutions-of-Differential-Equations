import static java.lang.Math.cos;
import static java.lang.Math.sqrt;
import static java.lang.Math.sin;
import java.io.File;
import java.io.IOException;
import java.io.*;

public class LinearSolvers extends Substitution
{
    double pi = 3.14159627;

    public double[] GEsolve(double[][] toSolve, double[] b)//, int m)
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

    public double[] DirichletSolve(double[][] array, double[] u, double ua, double ub)
    {
        try {
            //for : with f(x) = 10*sin(2i*pi)
            BufferedWriter writer = new BufferedWriter(new FileWriter("Y.txt"));
            BufferedWriter xwriter = new BufferedWriter(new FileWriter("X.txt"));
            // for 1:3 with f(x) = 0
//            BufferedWriter writer = new BufferedWriter(new FileWriter("Vvalues.txt"));
//            BufferedWriter xwriter = new BufferedWriter(new FileWriter("Xvalues.txt"));



            double[] solFor;
            double[] sol;
            int len = array.length;
            double[] fx = new double[len];
            //initialize Right Hand Side
            //first case, f(x) = 0;
            fx = RHSinit(u, ua, ub);

            //second case 10sin(2pix)


            //Linear Solve for Lower*y = RHS
            solFor = forward(array, fx);

            //Linear Solve for Upper*u = y
            sol = backward(array, solFor);

            //Print out the values to a file of U and f(x)
            for (int i = 0; i < len; i++) {
                //write file to Vvalues.txt for all u
                String writeMe = Double.toString(sol[i]);
                writer.write(writeMe);
                writer.write("\n");

                //write to file all x for i
                String xVal = Double.toString(i);
                xwriter.write(xVal);
                xwriter.write("\n");
            }

            writer.close();
            xwriter.close();
            u = sol;
        }
        catch(IOException io)
        {
            System.out.println("IO EXCEPTION CAUGHT \n");
            io.printStackTrace();
        }
        return u;
    }

    public double[] RHSinit(double[] fx, double ua, double ub)
    {
        //init array to what the formula is
        int n = fx.length;
        double[] rhs = new double[n];

        for (int i = 0; i < n; i++)
        {
            //rhs[i] = 0.0;
            //rhs[i] = cos(i*(pi));
            //rhs[i] = 10*sin(2*pi*i);
            //rhs[i] = sin(pi*i);
            rhs[i] = i - Math.pow(i, 2);
        }
        //Dirichlet conditions, f(x0) - ua/h^2
        rhs[0] = rhs[1] - ua;
        rhs[n-1] = rhs[n-1] - ub;

        return rhs;
    }

    public double[] NeumannSolve(double[][] array, double[] u, double uaprime, double ubprime)
    {
        try {
            //for : Neumann Conditions specifically those on the third tasksheet.
            BufferedWriter writer = new BufferedWriter(new FileWriter("Y.txt"));
            BufferedWriter xwriter = new BufferedWriter(new FileWriter("X.txt"));
            // for 1:3 with f(x) = 0
            //BufferedWriter writer = new BufferedWriter(new FileWriter("Vvalues.txt"));
            //BufferedWriter xwriter = new BufferedWriter(new FileWriter("Xvalues.txt"));

            double[] solve;
            double[] fullSol;
            int len = array.length;
            double[] fx = new double[len];
            fx = u;
            //use coeffs for array values.
            //1, {0,1,2}, 2
            //BEFORE ENTER INTO NEUMANN

            //need to initialize RHS for solving, with values to prove if it is right.
            solve = RHSinit(fx, 1, 3);

            //LU factorization?? or GEsolve??
            solve = forward(array,solve);
            fullSol = backward(array, solve);


            //Print out the values to a file of U and f(x)
            for (int i = 0; i < len; i++) {
                //write file to Vvalues.txt for all u
                String writeMe = Double.toString(fullSol[i]);
                writer.write(writeMe);
                writer.write("\n");

                //write to file all x for i
                String xVal = Double.toString(i);
                xwriter.write(xVal);
                xwriter.write("\n");
            }

            writer.close();
            xwriter.close();
            u = fullSol;

        }
        catch(IOException io)
        {
            System.out.println("IO EXCEPTION CAUGHT \n");
            io.printStackTrace();
        }

        //return answer array.
        return u;
    }

    public double[] aOfXinit(double[] aX)
    {
        int n = aX.length;
        //function:
        //TS4 #1: a(x) is a constant, so a(x) = 1

        for (int i = 0; i < n; i ++)
        {
            aX[i] = sin(pi*i); //1.0;
        }

        //return the calculated values
        return aX;
    }

    public double[] aXaverage(double[] a, double[] k)
    {
        int n = k.length;

        //loop through a, to compute the average between numbers
        //returning final k array.
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                k[i] = (1 / 2.0) * a[i] + a[i - 1];
            } else {
                k[i] = (1 / 2.0) * (a[i] + a[i + 1]);
            }
        }

        return k;
    }

    public double[] funcDirichlet(double[] kX, double ua, double ub, double[] b, double h)
    {
        double[] sol = new double[kX.length];
        double[][] matrix = new double[b.length][b.length];
        double hold;

        //Initialize RHS
        b = RHSinit(b, ua, ub);

        //Get the Coeffs multiplied by kX correctly...
        for (int row = 0; row < b.length; row++)
        {
            for (int col = 0; col < b.length; col++)
            {
                //Compute the kX matrix
                    if(row == col)
                    {
                        //compute values of k to multiply, so not to go out of bounds of the array.
                        if(row < kX.length - 1)
                            hold = (-1) * (kX[row] + kX[row+1]);
                        else
                            hold = (-1)* kX[row];

                        //main diagonal
                        matrix[row][col] = hold;
                    }
                    if(row + 1 < b.length)
                    {
                        //superdiagonal
                        matrix[row + 1][row] = kX[row + 1];
                    }
                    if(row + 2 < b.length)
                    {
                        //subdiagonal
                        matrix[row][row + 1] = kX[row + 2];
                    }
            }
        }



        //Initial conditions for range added to b[]
        b[0] = b[0] - ((kX[0]/(Math.pow(h, 2)))*ua);
        b[b.length-1] = b[b.length-1] - (((kX[b.length-1])/(Math.pow(h, 2)))*ub);



        //Gaussian Elimination to solve
        sol = GEsolve(matrix, b);

        //return the solution
        return sol;
    }

    public double calculateP(double[][] arr4err, double length)
    {

        double ans = 0;

        //calculate ans based on equation on pg 255 in Appendix A in textbook
        for (int i = 0; i < length-1; i++)
        {
            ans = ((arr4err[3][i]/arr4err[3][i+1])/(arr4err[2][i]/arr4err[2][i+1]));
        }

        return ans;
    }

}
