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
            //TODO: RETURN values of u
            //return sol;
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
            rhs[i] = 10*sin(2*pi*i);
        }
        //Dirichlet conditions, f(x0) - ua/h^2
        rhs[0] = rhs[1] - ua;
        rhs[n-1] = rhs[n-1] - ub;

        return rhs;
    }

}
