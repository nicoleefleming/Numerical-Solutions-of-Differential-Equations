import java.util.Arrays;

public class Substitution {

    public static double[] forward(double[][] mat, double b[]) {

        int nrow = mat.length;
        //double sol[] =new double[nrow];

        for (int r=0;r<nrow; r++)
        {
            double val =0;
            for (int c=0;c<r; c++) {
                val =val +  b[c] *mat[r][c];
            }
            val = b[r] - val;
            b[r] = val/mat[r][r];
        }
        return b;
    }

    public static double[] backward(double[][] Umat,double b[]) {

        int n = Umat.length;
        int m = b.length;

        if(n != m)
        {
            System.out.println("Invalid length.");
            return b;
        }

        double x = b[n-1]/Umat[n-1][n-1];
        double[] xi = new double[n];

        for (int i = n-1; i >= 0; i--)
        {
            xi[i] = b[i];
            for (int j = i-1; j > n; j--)
            {
                xi[i] = xi[i] - (Umat[i][j] * x);
            }
            xi[i] = xi[i]/Umat[i][i];
        }

        return xi;
    }
}
