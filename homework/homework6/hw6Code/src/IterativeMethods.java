import java.util.Vector;

public class IterativeMethods extends LinearSolvers {

    static int test_convergence(double []x, double []oldx, int n)
    {
        double maxvalue, tempvalue;
        int i;
        double epsilon = 0.000000001;

        maxvalue = 0.0;
        for(i=0; i < n; i++)
        {
            tempvalue = Math.abs(x[i] - oldx[i]);
            if (tempvalue > maxvalue)
                maxvalue = tempvalue;

        } // for
        if (maxvalue > epsilon)
            return 0;
        else
            return 1;

    } // test_convergence

    public double[] jacobi(double[] ad, double[] as1, double[] al1, double[] as2, double[] al2, double[] b, double[] x, int n, int m)
    {
        int nx = n; //length of row
        int ny = m; //length of column
        int nxny = nx*ny;
        double[] xold = x;
        double[] xnew = new double[nxny];

        System.out.println(b.length);
        for(int i = 0; i < nxny; i++)
        {
            xnew[i] = b[i];
        }

        for (int i = 0; i < nxny - 1; i++)
        {
            xnew[i] = xnew[i] - (as1[i]*xold[i + 1]);
            xnew[i] = xnew[i] - (al1[i]*xold[i]);
        }

        for (int i = 0; i < nxny - nx; i++)
        {
            xnew[i] = xnew[i] - (as2[i] * xold[i + nx]);
            xnew[i] = xnew[i] - (al2[i] * xold[i]);
        }

        for (int i = 0; i < nxny; i++)
        {
            xnew[i] = xnew[i]/ad[i];
        }

        return xnew;
    }

    public double[] gaussSeidel(double[] ad, double[] as1, double[] al1, double[] b, double[] x, int n, int m)
    {
        int nx = n; //length of row
        int ny = m; //length of column
        int nxny = nx*ny;
        double[] xold = x;
        double[] xnew = new double[nxny];

        for (int i = 0; i < nxny; i++)
        {
            xnew[i] = b[i];
        }
        for (int i = 0; i < nxny - 1; i++)
        {
            xnew[i] = xnew[i] - (as1[i] * xold[i]);
        }
//        for (int i = 0; i < nxny - nx; i++)
//        {
//            xnew[i] = xnew[i] - (al2[i] * xold[i]);
//        }

        double[] temp = new double[xnew.length];
        for (int i = 0; i < nxny - 1; i++)
        {
            xnew[i] = xnew[i]*al1[i];
            temp[i] = xnew[i];
        }
//        for(int i = 0; i < nxny - nx; i++)
//        {
//            xnew[i] = xnew[i]*as2[i];
//            temp[i] += xnew[i];
//        }
        for (int i = 0; i < nxny; i++)
        {
            xnew[i] = temp[i] + xnew[i]*ad[i];
        }

        return xnew;
    }

    public double[][] gaussSeidel2D(double[] ad, double[] as, double[] al, double[] b, int n, double h)
    {
        double[] newVars = new double[b.length];
        for (int i = 0; i < b.length; i++)
        {
            newVars[i] = b[i];
        }
        int nxny = n*n;
        double[][] Ups = new double[nxny][nxny];
            for(int j = 0; j < 2; j++)
            {
                for(int k = 0; k < ad.length; k++)
                Ups[j][k] = ad[j];
            }

            for (int i = 0; i < ad.length - 2; i++)
            {
                Ups[i][i+1] = as[i];
                Ups[i+1][i] = al[i];
            }

            int k = 0;
            for (int i = 1; i < ad.length - 1; i++)
            {
                for(int j = 1; j < ad.length - 1; j++)
                {
//STALLS OUT AT j ==1, i ==1. Why??
                    Ups[i][j] = (1/4) *(Ups[i-1][j] + Ups[i+1][j] + Ups[i][j-1] + Ups[i][j+1]);

                    Ups[i][j] -=  ((Math.pow(h, 2)/4)*newVars[j]);
                }
            }


        return Ups;
    }

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

    public double[][] ninePointStencil()//I DONT KNOW WHAT TO DO HERE...................
    {

        //return A;
    }

    public void pDiagInit(double[] ld, double[] al, double[] ad, double[] as, double[] ud, int n)
    {
        double[][] init = new double[5][ad.length];
        //initialize vector values
        int nx = n; //# of nodes in the first coordinate
        int ny = n; //# of nodes in the second coordinate
        int nxny = nx*ny; //the size of teh dimensions of the sparse matrix.

        for(int i = 0; i < nxny; i++)
        {
            ad[i] = -4.0;
        }
        for(int i = 0; i < (nxny - 1); i++)
        {
            as[i] = 1.0;
            al[i] = 1.0;
        }
        for(int i = 0; i < nxny - nx; i++)
        {
            ud[i] = 1.0;
            ld[i] = 1.0;
        }
        //void method for initializing values for the sparse matrix storage
    }

    public double[] RHSpdeInit(double[] pdx, double[] pdy, double ua, double ub)
    {
            //init array to what the formula is
            int n = pdx.length;
            double[] rhs = new double[n];

            for (int i = 0; i <= n-1; i++)
            {
                //rhs[i] = x*y
                rhs[i] = pdx[i] * pdy[i];
            }
            //Dirichlet conditions, f(x0) - ua/h^2
//            for (int i = 0; i < n; i++)
//            {
//                System.out.println(rhs[i] + " ");
//            }
            rhs[0] = rhs[1] - ua;
            rhs[n-1] = rhs[n-1] - ub;

            return rhs;
    }
}
