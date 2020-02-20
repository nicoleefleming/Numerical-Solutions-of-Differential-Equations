# Tasksheet 4
 
## Task 1
Write a finite difference code for the elliptic problem       
(k(x)u′)′=f(x)      
on [0,1]. Use the Gaussian elimination routine that you developed for your first elliptic solver to solve the resulting linear system of equations. Test your code in the case when the coefficient, k(x), is a constant. This should produce the same results as in your previous tasks.
### Response
If this means to do the findCoeffs funciton, this is incomplete at the moment. However, if it means solve for b using a already set up array, the following will work. The code below was tested with an array of coefficients, and the tridiagonal matrix. The routine to find the answers, is included below and with links to the manual at the end of this response section.   
    
For this problem, a three new methods were created, with the main method being to take the function and incorporate the values of the function, a constant here, into the matrix. The code that was written and tested with various values for k(x) as constants and as the funtion k(x) = sin(pix). The manual assumes b to be the values found from u(x) = cos(x).

    public double[] funcDirichlet(double[] kX, double ua, double ub, double[] b, double h)
    {
        double[] sol = new double[kX.length];
        double[][] matrix = new double[b.length][b.length];
        double hold;
       
        //Initialize RHS (f(x))
        //This is here due to using b in another method before this one. 
        //This verifies the values of b are what they should be
        b = RHSinit(b, ua, ub);
        
        //kX init to A
        for (int row = 0; row < b.length; row++)
        {
            for (int col = 0; col < b.length; col++)
            {
                //Compute the kX Matrix
                    if(row == col)
                    {
                        //compute values of k to multiply, so not to go out of bounds of the array.
                        if(row < kX.length - 1)
                            hold = (-1) * (kX[row] + kX[row+1]);
                        else
                            hold = (-1)* kX[row];

                        //main diagonal
                        matrix[row][col] = hold;
                    }
                    if(row + 1 < b.length)
                    {
                        //superdiagonal
                        matrix[row + 1][row] = kX[row + 1];
                    }
                    if(row + 2 < b.length)
                    {
                        //subdiagonal
                        matrix[row][row + 1] = kX[row + 2];
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

    
Here is the link to the Software Manual Pages for the functions used above.    
[funcDirichlet](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/funcDirichlet.md)    
[aOfXinit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/aOfXinit.md)    
[aXaverage](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/aXaverage.md)    

## Task 2
In class we will see how to perform a computational convergence analysis. Do this on the constant coefficient case where homogeneous Dirichlet conditions are imposed. Note that you will need an exact solution for the problem. If 
f(x)=x−x^2, determine an exact solution for this task.
### Response
Fixing on this one.

## Task 3
Repeat the previous task for the case when the coefficient is specified as    
k(x)=sin(πx)
### Response - This upon working on number 2 and 4 might need to be reworked...
This was actually one of the verified functions tested with k(x) when f(x) = cos(x). The difference here is that k(x) is applied to f(x) = x - x^2. The change of f(x) was changed in the last task, so the process was simple once having the working code for task 2, all this task needed to change was the value of k(x).     

The function that was altered for this was the aOfXinit, where aX was set to a different value depending on the value of x. The code that follows shows the change in k(x). 

    public double[] aOfXinit(double[] aX)
    {
        int n = aX.length;
        //function:
        //TS4 #1: a(x) is a constant, so a(x) = 1

        for (int i = 0; i < n; i ++)
        {
            aX[i] = sin(pi*i); //1.0;
        }

        //return the calculated values
        return aX;
    }
    
The answers to this problem as computed for a 3x3 matrix by the code written are as follows:     
          -2.0       
          -3.0         
          -96.38461538461539           
The values found were the result of the funcDirichlet code, and the calls inside that method to RHSinit, aOfXinit, aXaverage, and GEsolve. The links to the Software Manual pages are below.     

[funcDirichlet](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/funcDirichlet.md)    
[aOfXinit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/aOfXinit.md)    
[aXaverage](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/aXaverage.md)
[RHSinit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/RHSinit.md)

## Task 4
Use f and homogeneous Dirichlet conditions in this task. Use the array of values provided by your instructor to represent the coefficient function, k(x). This may require that you modify your code to handle an array over a function definition.
### Response
This task was met with some confusion due to values in the oslution array returning NaN. The solution was to adjust the funcDirichlet to not alter the Coeffs matrix, and use k(x) as A in the way presented in class. Using a hard coded array with values given and parsing the data from a file yielded the same results. They are as follows:     



Software Manual Pages used
[funcDirichlet](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/funcDirichlet.md)    
[input](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/input.md)    

## Task 5
Build software manual pages for the elliptic solver codes you have written so far.
### Response
This was completed after each of the methods were written and verified they worked. The following links are the links to the new methods developed for this list of tasks. 
1. [Table of Contents]()
2. [funcDirichlet](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/funcDirichlet.md)    
3. [aOfXinit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/aOfXinit.md)    
4. [aXaverage](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/aXaverage.md)
5. [input](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/input.md)

## Task 6
Search the internet for discussions of using finite difference methods to handle a nonconstant coefficient function. That is, find sites for elliptic problems of the form     
(k(x)u′)′=f      
with with any type of boundary conditions. Write a brief paragraph (3 or 4 sentences) that describes your findings. Include links to the sites you cite.
### Response
It was interesting to read through the Stack Exchange on a question like this involving a similar proble, the thing that caught my interest right away was the first answer as the responder talks about how if k is continuous, one can abstract it out to test or see the fluxes. In the last tasksheet, I wonder if this would have worked to test for the fluxes near the endpoints. Upon looking into the fluxes that can occur or be modeled, it is important to note that these fluxes that the first responder notes are due to some sort of non-linear k.  I learned that the k(x) is very helpful in modeling and predicting the outcome for fluxes on a system.

#### Sources
[Stack Exchange discussion](https://math.stackexchange.com/questions/1509291/numerical-solution-of-non-constant-coefficient-diffusion-equation-via-finite-dif)       
[Another discussion on Math Stack Exchange](https://math.stackexchange.com/questions/2952304/discretize-derivative-of-heat-flux-with-variable-conductivity?noredirect=1&lq=1)       
For a more information on flux I googled flux and read the definition that resulted.
