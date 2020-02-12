# Tasksheet 2 

## Task 1

For the tridiagonal matrix that arises from the central difference applied to the elliptic problem 
u'′=f(x) 
on [0,1] develop an algorithm that performs the LU decomposition of the coefficient matrix. Also, develop the forward and backward substitution methods to go along with the LU factorization.

### Solution
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
         
          return A;
       }
       
The Software manual page for LUfac, forward and backward are listed below.    

[LUfac](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/LUfac.md)   

[forward](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/forward.md)    

[backward](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/backward.md)    

## Task 2

Apply the code from the previous task to the case where f(x)=0 and u(0)=1 and u(1)=3. Use some graphing utility to display your results. Do this for varying numbers of mesh points. Make the coarsest mesh size 
h=1/8 and refine to h=1/256.

### Response
The following code was used to solve the Dirichlet Boundary Value problem. The original misunderstanding was with the substitution methods. I was attempting to put all the methods into one, which was not how it should be, since the substitution aids in solving the system. 
  
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

            //TODO: RETURN values of u
            //return u;
        }
        catch(IOException io)
        {
            System.out.println("IO EXCEPTION CAUGHT \n");
            io.printStackTrace();
        }
        return u;
    }   
    
To solve for the Right Hand Side the following function was used.   

      public double[] RHSinit(double[] fx, double ua, double ub)
      {
        //init array to what the formula is
        int n = fx.length;
        double[] rhs = new double[n];

        //function
        for (int i = 0; i < ub; i++)
        {
            rhs[i] = 0.0;
            //rhs[index] = 10*sin(2*pi*i);
        }
        //Dirichlet conditions, f(x0) - ua/h^2
        rhs[0] = rhs[1] - ua;
        rhs[n-1] = rhs[n-1] - ub;

        return rhs;
    }
    
    
The graphs that follow are for n = 200, with h = 1/8 and h = 1/256 for f(x) = 0, ua = 1, and ub = 3
![Graph of f(x) = 0, h= 1/8](https://github.com/nicoleefleming/math5620/blob/master/homework/homework3/Graphofts3.2nis200x200mis8.png)
For h = 1/256, the difference was the power of 10, instead of 10^-10, it was 10^19. I am working to add an image of that graph.

The Software Manual Page for each of the functions listed above are linked below.   

[DirichletSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/DirichletSolve.md)    
[RHSinit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/RHSinit.md)     
    
## Task 3

Repeat the previous task with $f(x)=10.0\ sin(2\pi x)\) and use homogeneous Dirichlet boundary conditions. This means u(a)=0, and u(b) = 0.

### Response
In the process of completion. . . .    

## Task 4

Repeat the previous two tasks combining f(x) from Task 3 with the boundary conditions given in Task 2.

### Response
In the process of completion. . . .     

## Task 5

Create a new code from the elliptic problem with Neumann boundary conditions. Using the same data as in Task 4 test your code using homogeneous Neumann conditions. Also, run a test case for unit fluxes at both ends of the domain.

### Response
There is still some confusion with this problem, but it is in the process of being completed. . . .   

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
