# Tasksheet 5
 
 IN PROCESS
 
## Task 1
Write a finite difference code for the nonlinear elliptic problem
θ′′(t) + (g/l) * sin(θ(t)) = 0
with 
θ(0) = 1 and theta(2) = −1. Use (g/l) = 1.
### Response
INCORRECT. Waiting on fixing.   

      
The Software Manual Pages to the methods used are listed below           
[nonLinearInit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/nonLinearInit.md)         
[nonlinearFDSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/nonlinearFDSolve.md)          
[findCoeffs](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/findCoeffs.md)         
[factorial](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/factorial.md)        
[triDiagSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/triDiagSolve.md)        

## Task 2
Simplify the problem in Task 1 using the small angle assumption 
sin(θ) ≈ θ.
### Response
INCORRECT
## Task 3
 Let's start into approximate solution of partial difference equations. With the simplest equation,       
Δu = ∂^2u/∂x^2 + ∂^2u/∂y^2 = f(x,y)       
Start by writing a code to initialize the associated pentadiagonal matrix using central differences. Do this using the sparse storage into 5 vectors. Also, write a routine that initializes the right hand side of the system of equations.       
### Response   
I am still trying to figure out how to put in the logic to account for the rows of zeros in the matrix, to take it out of the sparse matrix that has been coded for solving. 

     ADJUSTMENTS ARE BEING MADE TO FIX WHAT WAS NOT UNDERSTOOD.

The new methods written are pDiagInit, and RHSpdeInit. pDiag init takes the 5 diagonal rows and puts them into 5 columns in a matrix. The zeros diagonal logic is coming, but I am not sure how to implement it currently.          
        
    public double[][] pDiagInit(double[] ld, double[] al, double[] ad, double[] as, double[] ud)
    {
        double[][] init = new double[5][ad.length];

        //do all the things.
        //put all diagonals into the 5 following vectors.
        for (int i = 0; i < ad.length; i++)
        {
            init[0][i] = ld[i];
            init[1][i] = al[i];
            init[2][i] = ad[i];
            init[3][i] = as[i];
            init[4][i] = ud[i];
        }

        return init;
    }
       
The other is the RHSpdeInit which takes two variables of different values, and performs a funciton operation with them.        
      
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
    
The links to the Software Manual pages are listed below.        
[pDiagInit](https://github.com/nicoleefleming/math5620/tree/master/SoftwareManual/pDiagInit.md)
[RHSpdeInit](https://github.com/nicoleefleming/math5620/tree/master/SoftwareManual/RHSpdeInit.md)

## Task 4
Write a routine that implements Jacobi iteration for the approximate solution linear systems of equations.
### Response
The code developed exists in two new methods. One is the Jacobi calculation. I want to either have a variation of this method, or go back and make this work for the way we wrote the matrix in class, with the    
x^(k+1) = d^(-1) * (b - (L+U) * x^(k)).           
Currently I don't beleive I have that version implemented. I will look at the methods more after I have task 3 working, and task 5 is being worked on. For now the methods used can be found at their Software Manual Links below.       
    
        
            
            THIS CODE WORKS, JUST NOT HOW IT IS NEEDED TO. UPDATED CODE WILL BE PUSHED WHEN COMPLETE




[jacobi](https://github.com/nicoleefleming/math5620/tree/master/SoftwareManual/jacobi.md)    
[test_convergence](https://github.com/nicoleefleming/math5620/tree/master/SoftwareManual/test_convergence.md)    

## Task 5
Write a code that solves the elliptic problem in Task 1 using your Jacobi iteration. Use f(x,y) = xy and homogeneous boundary conditions.
### Response
Working on the code will push when finished.

## Task 6
Search the internet for discussions of using finite difference methods for elliptic operators. Write a brief paragraph (3 or 4 sentences) that describes your findings. Include links to the sites you cite.
### Response
The article in Sciendo discussed the uses and had more of a walk through of the convergence, the method, and the truncation error. I found the other discussion more interesting, with the modeling of Diffusion of the charge effects. The application of using the finite difference method to calculate volume was very interesting, and the modeling was really interesting to read about.

#### Sources
[Sciendo](https://content.sciendo.com/configurable/contentpage/journals$002famns$002f3$002f1$002farticle-p311.xmlSciendo)       
[Modeling of Diffuse Charge Effects in a Microfluidic Based Laminar Flow Fuel Cell](https://www.researchgate.net/publication/228821547_Modeling_of_Diffuse_Charge_Effects_in_a_Microfluidic_Based_Laminar_Flow_Fuel_Cell)       
