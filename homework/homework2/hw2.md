# Tasksheet 2 

## Task 1

Build a code that will initialize the tridiagonal matrix for the simple elliptic ordinary differential equation in the textbook. 
Use the array storage version of the matrix storage. Also implement the code that initializes the right hand side vector. Make the
code reusable.

      public double[][] triDiagInit(int[] al, int[] ad, int[] as, int n)
      {
        //assign values based on matrix located on page 16 in the textbook
        for(int i = 0; i < n; i++)
        {
            al[i] = 1;
            ad[i] = 2;
            as[i] = 1;
        }

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

Link to Software Manual Page with the method:
[triDiagInit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/triDiagInit.md)

## Task 2

Implement an algorithm for solving the linear system in the book for the elliptic problem.

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
        double x = b[n-1]/A[n-1][n-1];
        double[] xi = new double[n];
      
        for (int i = n-1; i >= 0; i--)
        {
            xi[i] = b[i];
            for (int j = i-1; j > n; j--)
            {
                xi[i] = xi[i] - (A[i][j] * x);
            }
            xi[i] = xi[i]/A[i][i];              
        }
      
        return b;
      } 


Link to Software Manual Page with the method:
[triDiagSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/triDiagSolve.md)

## Task 3

Write up the results that you obtain in the approximate solution of the elliptic problem.

Output of Results:
        
        0.1111111111111111 
        -0.05555555555555555 
        -0.07407407407407407
        
Those were the results of solving the elliptic problem in the book that my code found.

## Task 4

Write a code that will compute the coefficients for a finite difference approximation of a given derivative. There is a version of the
code (in Matlab) that will do the job. If you use Matlab to get this done, expand out all of the shorthand notation that Matblab uses.

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

Link to Software Manual Page with the method findCoeffs:
[findCoeffs](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/findCoeffs.md)

Link to the Software Manual Page with the method factorial:
[factorial](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/factorial.md)

## Task 5

Write up a software manual page (we will go through a template in class. Put pages in for the codes you have written for this homework assignment.

The link to the Software Manual Folder that holds all the pages written thus far
[Software Manual](https://github.com/nicoleefleming/math5620/tree/master/SoftwareManual)

## Task 6
Search the internet for applications that involve modeling real physical processes with a simple elliptic ODE. Write a brief paragraph
(3 or 4 sentences) that describes your findings. Include links to the sites you cite.

### Response

It was very interesting to see all the different applications of the simple elliptic problem. A lot of the places I found were using Partial 
Differential Equations for their studies, but a few that I found the most fascinating, were the Plasma Wave exact solutions found, and the 
Comparitive Dynamics. It was neat to see how each of these two projects used the elliptic equations to help model and test their findings. 
Coming from the IEEE side, it was very interesting to read the Comparative Dynamics paper.

*For the articles on either of these sites, you need a subscription number to J-Stage, or a sign in and membership criteria to IEEE. 

### Source Links
[Mathmatic Diffusion Theories](https://arxiv.org/pdf/1706.08241.pdf)
[Plasma Waves (in Japanese)](https://www.jstage.jst.go.jp/article/jpsj/74/5/74_5_1431/_article/-char/ja/)
[Dynamics Comparison](https://ieeexplore.ieee.org/abstract/document/5966959)
[Applied Mechanics](https://books.google.com/books?id=S0knAQAAMAAJ&pg=SL10-PA605-IA62&lpg=SL10-PA605-IA62&dq=model+real+physical+processes+using+simple+elliptic+differential+equations&source=bl&ots=f6QdRH1HhA&sig=ACfU3U0Sy8RZ_yZvtgMeKjs6MS-XzZe2Cg&hl=en&sa=X&ved=2ahUKEwjKha7hx67nAhXCbc0KHaTwC18Q6AEwEXoECA4QAQ#v=onepage&q=model%20real%20physical%20processes%20using%20simple%20elliptic%20differential%20equations&f=false)
[ODE Modeling for Linear Systems](http://www.math.ntu.edu.tw/~chern/notes/ode2015.pdf)
