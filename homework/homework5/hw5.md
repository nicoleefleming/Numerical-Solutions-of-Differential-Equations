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
The first method used here, is the pDiagInit, which uses sparse storage of the matrix form, only storing the values in the non-zero diagonals ad, as, al, ud, and ld. Where ad is the main diagonal, as is the first super diagonal, al is the first sub diagonal, ud is the second super diagonal, and ld is the second lower diagonal. 

     ADJUSTMENTS ARE BEING MADE TO FIX WHAT WAS NOT UNDERSTOOD.         
        
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
       
The other is the RHSpdeInit which takes two variables of different values, and performs a funciton operation with them. This is still being adjusted, and will be updated when it is finished.       
      
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
    
        
            
            THIS CODE WORKS, JUST NOT HOW IT IS NEEDED TO. UPDATED CODE WILL BE PUSHED WHEN COMPLETE.
            THE CORRECT CODE FOR THE SPARSE MATRIX WILL BE STORED IN 5 VECTORS, AND JACOBI WILL BE UPDATED WITH THE METHOD OF USING THAT TO SOLVE FOR THE APPROXIMATIONS. 
            
            TEST_CONVERGENCE MAY BE UN-NEEDED FOR THIS TO WORK.




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
