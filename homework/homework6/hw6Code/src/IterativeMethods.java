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
