# Tasksheet 2 

## Task 1

For the tridiagonal matrix that arises from the central difference applied to the elliptic problem 
u'′=f(x) 
on [0,1] develop an algorithm that performs the LU decomposition of the coefficient matrix. Also, develop the forward and backward substitution methods to go along with the LU factorization.

###Solution
To preform the LU decomposition on the elliptic problem, the following code was created.

      private double[][] LUfac(double[][] array, double[] b)
      {
          int n = array.length;
          int m = b.length;
      
      
          if(n != m)
          {
              System.out.println("The rows of the matrix A do not match the rows in the known solution vector.");
              return array;
          }
      
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
          
          forward(A, b);
          backward(A, b);
          B = b;
          return A;
       }
       
The Software manual page for LUfac, forward and backward are listed below.    

[LUfac](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/LUfac.md)   

[forward](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/forward.md)    

[backward](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/backward.md)    

## Task 2

Apply the code from the previous task to the case where f(x)=0 and u(0)=1 and u(1)=3. Use some graphing utility to display your results. Do this for varying numbers of mesh points. Make the coarsest mesh size 
h=1/8 and refine to h=1/256.

###Response
Due to lack of understanding how to go about actually solving this problem the following code was created, and thought over. 
The conclusion of this excersise and the following three was that the understanding of what was being done was not there, at least to get
the answers that were being requested.   
   
Here is the code that was created in an attempt to solve the problem.  
  
     public double[] DirichletSolve(double[][] A, double[] lambda, double[] b, double b1, double c, int k,  double u0, double u1) throws IOException
    {
        File file = new File("Vvalues");
        FileOutputStream output = new FileOutputStream(file);

        //TODO: SOLVE FOR ROOTS
        double[] r = new double[k];
        double[] v = new double[A.length];
        double mew;
        double gamma;

        for (int i = 0; i < k; i++)
        {
            if(k == 2)
            {
                //#DO-DO??
                r[i] = solveRoots(A, lambda, b1, c, i);

            }
        }

        //TODO: SOLVE FOR CONSTANTS

        //TODO: SOLVE FOR V

        //TODO: ASSIGN V

        //TODO: PUT VALUES OF X AND V FOR 0.01-0.99 INTO A FILE
        //ASSIGN 0 AND 1 BEFORE THE LOOP

        output.close();
        //TODO: RETURN V
        return b;
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
    
The Software Manual Page for each of the functions listed above are linked below. 

[DirichletSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/DirichletSolve.md)    
[AminusLambda](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/AminusLambda.md)     
[AssignLambda](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/AssignLambda.md)     

    
    
    
## Task 3

Repeat the previous task with $f(x)=10.0\ sin(2\pi x)\) and use homogeneous Dirichlet boundary conditions.

###Response
Due to lack of understanding how to go about actually solving this problem, the conclusion of this excersise and the following three was that the understanding of what was being done was not there, at least to get
the answers that were being requested. There were 5 whiteboards filled with attempts to solve this problem with pseudocode and handwritten work.

## Task 4

Repeat the previous two tasks combining f(x) from Task 3 with the boundary conditions given in Task 2.

###Response
Due to lack of understanding how to go about actually solving this problem, the conclusion of this excersise and the following three was that the understanding of what was being done was not there, at least to get
the answers that were being requested. 

## Task 5

Create a new code from the elliptic problem with Neumann boundary conditions. Using the same data as in Task 4 test your code using homogeneous Neumann conditions. Also, run a test case for unit fluxes at both ends of the domain.

###Response
Due to lack of understanding how to go about actually solving this problem, the conclusion of this excersise and the following three was that the understanding of what was being done was not there, at least to get
the answers that were being requested. The confusion for this one arose mainly after getting to a certain point in solving the Dirichlet ba=oundary problems. 

## Task 6
 Search the internet for discussions of handling Dirichlet versus Neumann conditions. Write a brief paragraph (3 or 4 sentences) that describes your findings. Include links to the sites you cite.

### Response
Dirichlet boundaries are given to end the actual graph, the end conditions u(a) = c, and u(b) = d, are given. In Neumann the boundary is givne on the derivative, 
u'(a) = c, and u'(b) = d. Both are assuming a interval of [a,b]. Upon reading and looking more into the difference, I found that the last link was most helpful
in learning about the difference of two Boundary Values. It says that Dirichlet conditions exist in most spaces, wehreas in not well defined space, Neumann conditions might not.
For example, the space H,<sup>1</sup> vs H<sup>2</sup>.
 

### Source Links
[Introduction to Boundary Value Problems](https://people.sc.fsu.edu/~jpeterson/bvp.pdf)    
[Graphs with Mathmatica, discussion of BVP](https://mathematica.stackexchange.com/questions/120727/confusion-with-neumann-and-dirichlet)
[Discussion of Neumann vs Dirichlet](https://math.stackexchange.com/questions/2313690/neumann-boundary-condition-vs-dirichlet-boundary-condition-why-neumann-conditi)
