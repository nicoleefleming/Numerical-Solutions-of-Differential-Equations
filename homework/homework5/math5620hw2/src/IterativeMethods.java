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
            for (int i = 0; i < n; i++)
            {
                System.out.println(rhs[i] + " ");
            }
            rhs[0] = rhs[1] - ua;
            rhs[n-1] = rhs[n-1] - ub;

            return rhs;
    }
}
