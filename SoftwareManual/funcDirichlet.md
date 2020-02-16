# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           funcDirichlet

**Author:**                 Nicole Fleming

**Language:**              Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/LinearSolvers/*.java -d classes
    "FILE-PATH" -cp classes LinearSolvers.java
    
These commands should also work.

**Description/Purpose:** This method was written to handle k as a function k(x) in the equation (k(x) * u')' = f(x). This function takes arguments in and applies the logic for solving the 2D and 1D arrays to get the resulting array out of the unknowns.

**Input:** There are 6 inputs to this method. The 1D double array kX holds all the averaged values from the function k(x). The double ua and ub are the boundary conditions for the system. The 2D array is the array of coefficients or the tridiagonal matrix to be solved. The 1D array b is the array that will return the end values. The double h is the mesh factor. 

**Output:** This method will return a 1D array b, which will hold all the answers to the unknowns. 
  
  

**Usage/Example:**

To test this method, the following lines of code were used.
                  
     aX = linsolve.aOfXinit(aX);
     kX = linsolve.aXaverage(aX, kX);
    
     solution = linsolve.funcDirichlet(kX, ua, ub, Coeffs, x, h);
     //test for funcNeumann
     for (int i = 0; i < solution.length; i++)
     {
         System.out.println(solution[i] + " ");
     }

**Implementation/Code:** The following is the unfinished code for DirichletSolve
 
    public double[] funcDirichlet(double[] kX, double ua, double ub, double[][] Coeffs, double[] b, double h)
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
                //Compute the kX impact on Coeffs Matrix
                    if(row == col)
                    {
                        //compute values of k to multiply, so not to go out of bounds of the array.
                        if(row < kX.length - 1)
                            hold = (-1) * (kX[row] + kX[row+1]);
                        else
                            hold = (-1)* kX[row];

                        //main diagonal
                        matrix[row][col] = Coeffs[row][col] * hold;
                    }
                    if(row + 1 < b.length)
                    {
                        //superdiagonal
                        matrix[row + 1][row] = Coeffs[row + 1][row] * kX[row + 1];
                    }
                    if(row + 2 < b.length)
                    {
                        //subdiagonal
                        matrix[row][row + 1] = Coeffs[row][row + 1] * kX[row + 2];
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
     

**Last Modified:** 15/February/2020
