# Tasksheet 3 

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

            //close the files, assign sol to u for return.
            //u = sol due to the fact if the try is not ran, but theres no error, u will still be returned unchanged.
            writer.close();
            xwriter.close();
            u = sol;
        }
        catch(IOException io)
        {
            System.out.println("IO EXCEPTION CAUGHT \n");
            io.printStackTrace();
        }
        return u;
    } 
    
To solve for the Right Hand Side the following function was used. It is in the process of getting updated to take a function arguement in the passed in values. When that is updated, the Software Manual page will be updated to reflect that change. For the purpose of tasksheet 3 the following version works for the tasks given.   

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
    
    
The graphs that follow are for n = 200, with h = 1/8 and h = 1/256 for f(x) = 0, ua = 1, and ub = 3
![Graph of f(x) = 0, h= 1/256](https://github.com/nicoleefleming/math5620/blob/master/homework/homework3/honeeigthwith200.png)     h = 1/256 where f(x) = 0, and nxn = 200      

![Graph of f(x) = 0, h = 1/8](https://github.com/nicoleefleming/math5620/blob/master/homework/homework3/honeeigthwith200.png)       
h = 1/8 where f(x) = 0, and nxn = 200      

The Software Manual Page for each of the functions listed above are linked below.   

[DirichletSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/DirichletSolve.md)    
[RHSinit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/RHSinit.md)     
    
## Task 3

Repeat the previous task with $f(x)=10.0\ sin(2\pi x)\) and use homogeneous Dirichlet boundary conditions. This means u(a)=0, and u(b) = 0.

### Response
In using homogeneous roots, only a few modifictations were made to the code. The main modification being that in the RHSinit method, instead of assigning values of zero, the code was computing the value of f(x) which was an equation. The x in the equation was computed in a for loop in the right hand side to determine the answers. The graphs are shown below. for h = 1/8, and h = 1/256 with ua and ub equal to 0.    

![Graph of f(x) = 10sin(2pix), h = 1/8, ua = 0, ub = 0](https://github.com/nicoleefleming/math5620/blob/master/homework/homework3/10sinhiseighthomo.png)     

![Graph of f(x) = 10sin(2pix), h = 1/256, ua = 0, ub = 0](https://github.com/nicoleefleming/math5620/blob/master/homework/homework3/10sinhis256homo.png)     



## Task 4

Repeat the previous two tasks combining f(x) from Task 3 with the boundary conditions given in Task 2.

### Response
In using roots that are unequal to each other, only a few modifictations were made to the code. The main modification being that in the RHSinit method, instead of assigning values of zero, the code was computing the value of f(x) which was an equation. The x in the equation was computed in a for loop in the right hand side to determine the answers. The initial value of the right hand side was also changed since using Dirichlet's conditions, the first and last values are modified. In this cast the modification can be seen. The graphs are shown below. for h = 1/8, and h = 1/256 with ua equal to 1 and ub equal to 3. The size, n, was set to 200.    

![Graph of f(x) = 10sin(2pix), h = 1/8, ua = 1, ub = 3](https://github.com/nicoleefleming/math5620/blob/master/homework/homework3/10sinhis8hetero.png)     

![Graph of f(x) = 10sin(2pix), h = 1/256, ua = 1, ub = 3](https://github.com/nicoleefleming/math5620/blob/master/homework/homework3/10sinhis256hetero.png)       

## Task 5

Create a new code from the elliptic problem with Neumann boundary conditions. Using the same data as in Task 4 test your code using homogeneous Neumann conditions. Also, run a test case for unit fluxes at both ends of the domain.

### Response
The Neumann problem that was faced was in generating an array that had the correct numbers and values in the array the entire time. The soluiton was found to be that the Finite Difference Coefficient solver already developed did this rather nicely. That difference fo the coefficeints was calculated first, before calling the method NeumannSolve. This method was created in the last section and is called findCoeffs. It returned an array that was accurate to what was needed for this problem. After the coefficients were solved the array was passed into the method NeumannSolve, which takes the array, and solves for the u values in the A * U = F equation. I used the conditions ua' = 1, ub' = 3, and ua' = 0, ub' = 0 to verify that it was working properly, and not giving the same values for either conditions. The code for NeumannSolve is below followed by the link to the Software Manual page.

The unit test for flux I was not sure what to do or how to go about doing that.

    public double[] NeumannSolve(double[][] array, double[] u, double uaprime, double ubprime)
    {
        try {
            //for : Neumann Conditions specifically those on the third tasksheet.
            BufferedWriter writer = new BufferedWriter(new FileWriter("Y.txt"));
            BufferedWriter xwriter = new BufferedWriter(new FileWriter("X.txt"));
            // for 0:0 with f(x) = 0 and for 1:3 with f(x) = 0
            //BufferedWriter writer = new BufferedWriter(new FileWriter("Vvalues.txt"));
            //BufferedWriter xwriter = new BufferedWriter(new FileWriter("Xvalues.txt"));

            double[] solve;
            double[] fullSol;
            int len = array.length;
            double[] fx = new double[len];
            fx = u;
            //use coeffs for array values.
            //1, {0,1,2}, 2
            //Array is the Coeffs matrix

            //need to initialize RHS for solving, with values to prove if it is right.
            solve = RHSinit(fx, 1, 3);

            //LU factorization or GEsolve. 
            solve = forward(array,solve);
            fullSol = backward(array, solve);


            //Print out the values to a file of U and f(x)
            for (int i = 0; i < len; i++) {
                //write file to Vvalues.txt for all u
                String writeMe = Double.toString(fullSol[i]);
                writer.write(writeMe);
                writer.write("\n");

                //write to file all x for i
                String xVal = Double.toString(i);
                xwriter.write(xVal);
                xwriter.write("\n");
            }

            writer.close();
            xwriter.close();
            u = fullSol;

        }
        catch(IOException io)
        {
            System.out.println("IO EXCEPTION CAUGHT \n");
            io.printStackTrace();
        }

        //return answer array.
        return u;
    }


The Software Manual Page for NeumannSolve. The findCoeffs, and RHSinit methods are also used.     
[NeumannSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/NeumannSolve.md)


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
