# Math5620 Finite Difference Methods of Ordinary and Partial Differential Equations
**Routine Name:**           conjugateGradient

**Author:** Nicole Fleming

**Language:** Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/IterativeMethods/*.java -d classes
    "FILE-PATH" -cp classes IterativeMethods.java
    
where the method belonds to the class IterativeMethods.

**Description/Purpose:** This method uses the Conjugate-Gradient method to solve a linear system of equations. The particular example used the Poisson Linear System of 0s and 1s, and xy as the f vector. 

**Input:**  This method takes four inputs. The matrix A, as a 2 dimensional double array, the vector x, as a 1 dimensional array of values for the values of the unknowns, a 1 dimensional array of the right hand side of the equation, and the tolerance that the values will be tested against.

**Output:** This method returns a 1 dimensional array of the values of p.(?)

**Usage/Example:**  This is an iterative method that will be used to solve the sparsely stored pentadiagonal matrix A, with main diagonal as all 0, and the first super and sub diagonal as all 1. This code tests the method presented in the textbook on page 87 with a guess of u, to get the values of p. 
            
        n = 9;
        double[] cg = new double[x.length];
        for(int i = 0; i < al.length; i++)
        {
            al[i] = 1.0;
            as[i] = 1.0;
        }
        for(int i = 0; i < ad.length; i++)
        {
            ad[i] = 0.0;
        }
        toSolve = triOps.triDiagInit(al,ad,as,n);

        for(int i = 0; i < x.length; i++)
        {
            x[i] = 0.0;
        }
        cg = iter.conjugateGradient(toSolve, b, x, tolerance);
        for (int i = 0; i < cg.length; i++)
        {
            System.out.println("Conjugant-gradient Solution: " + cg[i]);
        }
               

**Implementation/Code:** 

        public double[] conjugateGradient(double[][] A, double[] b, double[] x, double tol) //could use Vector if needed, or wanted.
    {
        //TODO: the following puts together the logic on page 87 for the CG algorithm.
            //x b and A have to have the same length. A needs to be square and able to transpose.
        double[] r = new double[x.length];
        double[] p = new double[x.length];
        double[] w = new double[x.length];
        double[] a = new double[x.length];
        double[] beta = new double[x.length];
        //double tol = 0.00001;

        if(A.length != A[0].length || A.length != x.length)
        {
            System.out.println("A is not a square. The method won't work, since the transpose of A will not be correctly applied to x or b.");
            return x;
        }
        double[] tem = new double[r.length];
        double[] hold = new double[r.length];
        //get r0
        for (int i = 0; i < x.length; i++){
            for (int j = 0; j < x.length; j++)
            {
                r[i] = b[i] - (A[i][j] * x[i]);
                tem[i] += r[i];
            }
            r[i] = tem[i];
        }


        //get p0
        for (int i = 0; i < p.length; i++)
        {
            if(i != 0)
                p[i] = 1.0;
            else
                p[i] = r[0];
        }
        //get w[k-1]
        //get a[k-1]
        for(int k = 1; k < a.length + 1; k++)
        {
            for (int j = 0; j < a.length; j++)
            {
                w[k - 1] = A[k-1][j] * p[k-1];
                tem[k-1] += w[k-1];
                a[k-1] = ((r[k-1]*r[k-1]) / (p[k-1]*tem[k-1]));
            }
            w[k-1] = tem[k-1];
        }

        //get uk
        //get rk
        for(int k = 1; k < a.length + 1; k++)
        {
            b[k] = x[k-1] + (a[k-1] * p[k-1]);
            r[k] = r[k-1] - (a[k-1] * w[k-1]);

            //beta[k-1] = r^T_k*rk / r^T_k-1*r[k-1]
            beta[k-1] = ((r[k]*r[k])/(r[k-1]*r[k-1]));
            //pk = rk + beta[k-1]*p[k-1]
            p[k] = r[k] + beta[k-1] * p[k-1];

            //if ||rk|| < tolerance, stop
            if(Math.abs(r[k]) < tol)
            {
                break;
            }
        }

        for (int i = 1; i < p.length+1; i++)
        {
            p[i-1] = r[i-1];
        }

        return p;
    }
    
**Last Updated: 9/April/2020**
