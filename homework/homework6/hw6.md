# Tasksheet 6
 
 IN PROCESS
 
## Task 1
Task 1: Write a code that solves the Poisson equation where the linear solver used is Gauss-Seidel. So, use the equation
Δu = ∂2u/∂x2 + ∂2u/∂y2 = f(x,y) with f(x,y) = xy       
and homogeneous boundary conditions.

### Response
Using the textbook, in Chapter 4, the equation that was implemented for the Gauss-Seidel model was the equation used in 4.10, for the 1D Poisson Problem. In not being certain if this will effectively solve the problem above that is 2D another method is being written for a 2D version of the Poisson problem given above. The code used for this is coming from page 70 of the text book, and it is the second code listing to implement the Gauss-Seidel method, according to the book. When the 2D method is updated, since this prompt seems to want a 2D solution since it uses two variables, x and y, That will also be added here as the solution to the problem stated above.        
          
For the 1D problem the code written had the form of xnew = (b - U * xold)/(D + L).      
The code for it is listed in the software manual under gaussSeidel. The 2D solution to the problem will be listed under gaussSeidel2D. To test the code, for either case the form used is listed below.
           
           for(iters < maxIters)
           {
                //iter.gaussSeidel2D();
                iter.gaussSeidel(ad, as1, al1, as2, al2, b, x, n, n);
           }
           //where n and m are equal to give a square matrix.

answers for the 1D Poisson Problem are:
           
           Answers for x estimate:
           
           Gauss Seidel solution: -1.0101046265112168 
           Gauss Seidel solution: 1.0000036164102066 
           Gauss Seidel solution: 2.4999945754000397 
           Gauss Seidel solution: -1.1666648981441874 
           Gauss Seidel solution: -0.7352452609336343 
           Gauss Seidel solution: 0.03693714340241449 
           Gauss Seidel solution: 0.009069498333371265 
           Gauss Seidel solution: -0.005965909811283892 
           Gauss Seidel solution: 0.0 
           

The answers for the 2D Poisson Problem are:
           
           2D Answers:
           For a 9 x 9 matrix the results are as follows in groups by row
           
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 1.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0

           Gauss-Seidel 2D: 1.0
           Gauss-Seidel 2D: -0.0078125
           Gauss-Seidel 2D: -0.03125
           Gauss-Seidel 2D: -0.0703125
           Gauss-Seidel 2D: -0.125
           Gauss-Seidel 2D: -0.1953125
           Gauss-Seidel 2D: -0.28125
           Gauss-Seidel 2D: -0.3828125
           Gauss-Seidel 2D: 0.0

           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: -0.0078125
           Gauss-Seidel 2D: -0.03125
           Gauss-Seidel 2D: -0.0703125
           Gauss-Seidel 2D: -0.125
           Gauss-Seidel 2D: -0.1953125
           Gauss-Seidel 2D: -0.28125
           Gauss-Seidel 2D: -0.3828125
           Gauss-Seidel 2D: 0.0

           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: -0.0078125
           Gauss-Seidel 2D: -0.03125
           Gauss-Seidel 2D: -0.0703125
           Gauss-Seidel 2D: -0.125
           Gauss-Seidel 2D: -0.1953125
           Gauss-Seidel 2D: -0.28125
           Gauss-Seidel 2D: -0.3828125
           Gauss-Seidel 2D: 0.0

           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: -0.0078125
           Gauss-Seidel 2D: -0.03125
           Gauss-Seidel 2D: -0.0703125
           Gauss-Seidel 2D: -0.125
           Gauss-Seidel 2D: -0.1953125
           Gauss-Seidel 2D: -0.28125
           Gauss-Seidel 2D: -0.3828125
           Gauss-Seidel 2D: 0.0

           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: -0.0078125
           Gauss-Seidel 2D: -0.03125
           Gauss-Seidel 2D: -0.0703125
           Gauss-Seidel 2D: -0.125
           Gauss-Seidel 2D: -0.1953125
           Gauss-Seidel 2D: -0.28125
           Gauss-Seidel 2D: -0.3828125
           Gauss-Seidel 2D: 0.0

           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: -0.0078125
           Gauss-Seidel 2D: -0.03125
           Gauss-Seidel 2D: -0.0703125
           Gauss-Seidel 2D: -0.125
           Gauss-Seidel 2D: -0.1953125
           Gauss-Seidel 2D: -0.28125
           Gauss-Seidel 2D: -0.3828125
           Gauss-Seidel 2D: 0.0

           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: -0.0078125
           Gauss-Seidel 2D: -0.03125
           Gauss-Seidel 2D: -0.0703125
           Gauss-Seidel 2D: -0.125
           Gauss-Seidel 2D: -0.1953125
           Gauss-Seidel 2D: -0.28125
           Gauss-Seidel 2D: -0.3828125
           Gauss-Seidel 2D: 0.0

           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           Gauss-Seidel 2D: 0.0
           
           
This is going off of the formula given in class (the 1D method) and the code written in the textbook (page 70).

        
The Software Manual Pages to the methods used are listed below           
[gaussSeidel](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/gaussSeidel.md)            
[gaussSeidel2D](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/gaussSeidel2D.md)

## Task 2
Write a code to solve a linear system of equations using the conjugate gradient method. For a test case, use the matrix for the Poisson equation. Use the methodology discussed in class to test the method. That is, generate a right hand side from a vector of ones in the specification of the linear system.

### Response
    

The Software Manual Pages to the methods used are listed below           
[]()
[]()
[]()

## Task 3
Solve Δu =∂2u/∂x2 +∂2u/∂y2 = f(x,y) with f(x,y) = xy and homogeneous boundary conditions. Use at least three different mesh sizes, say h = 0.1, h = 0.01, and h = 0.001 for the test.
### Response   
I used the method developed in Task one for this, the gaussSeidel2D. I have put the results for the different mesh sizes below. If the conjugate gradient needs to be used here instead, I will replace the answers when that code is developed.

For h = 0.1

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 1.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 1.0
       Gauss-Seidel 2D: -0.005000000000000001
       Gauss-Seidel 2D: -0.020000000000000004
       Gauss-Seidel 2D: -0.04500000000000001
       Gauss-Seidel 2D: -0.08000000000000002
       Gauss-Seidel 2D: -0.12500000000000003
       Gauss-Seidel 2D: -0.18000000000000005
       Gauss-Seidel 2D: -0.24500000000000005
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -0.005000000000000001
       Gauss-Seidel 2D: -0.020000000000000004
       Gauss-Seidel 2D: -0.04500000000000001
       Gauss-Seidel 2D: -0.08000000000000002
       Gauss-Seidel 2D: -0.12500000000000003
       Gauss-Seidel 2D: -0.18000000000000005
       Gauss-Seidel 2D: -0.24500000000000005
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -0.005000000000000001
       Gauss-Seidel 2D: -0.020000000000000004
       Gauss-Seidel 2D: -0.04500000000000001
       Gauss-Seidel 2D: -0.08000000000000002
       Gauss-Seidel 2D: -0.12500000000000003
       Gauss-Seidel 2D: -0.18000000000000005
       Gauss-Seidel 2D: -0.24500000000000005
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -0.005000000000000001
       Gauss-Seidel 2D: -0.020000000000000004
       Gauss-Seidel 2D: -0.04500000000000001
       Gauss-Seidel 2D: -0.08000000000000002
       Gauss-Seidel 2D: -0.12500000000000003
       Gauss-Seidel 2D: -0.18000000000000005
       Gauss-Seidel 2D: -0.24500000000000005
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -0.005000000000000001
       Gauss-Seidel 2D: -0.020000000000000004
       Gauss-Seidel 2D: -0.04500000000000001
       Gauss-Seidel 2D: -0.08000000000000002
       Gauss-Seidel 2D: -0.12500000000000003
       Gauss-Seidel 2D: -0.18000000000000005
       Gauss-Seidel 2D: -0.24500000000000005
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -0.005000000000000001
       Gauss-Seidel 2D: -0.020000000000000004
       Gauss-Seidel 2D: -0.04500000000000001
       Gauss-Seidel 2D: -0.08000000000000002
       Gauss-Seidel 2D: -0.12500000000000003
       Gauss-Seidel 2D: -0.18000000000000005
       Gauss-Seidel 2D: -0.24500000000000005
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -0.005000000000000001
       Gauss-Seidel 2D: -0.020000000000000004
       Gauss-Seidel 2D: -0.04500000000000001
       Gauss-Seidel 2D: -0.08000000000000002
       Gauss-Seidel 2D: -0.12500000000000003
       Gauss-Seidel 2D: -0.18000000000000005
       Gauss-Seidel 2D: -0.24500000000000005
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0

For h = 0.01

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 1.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 1.0
       Gauss-Seidel 2D: -5.0E-5
       Gauss-Seidel 2D: -2.0E-4
       Gauss-Seidel 2D: -4.5000000000000004E-4
       Gauss-Seidel 2D: -8.0E-4
       Gauss-Seidel 2D: -0.00125
       Gauss-Seidel 2D: -0.0018000000000000002
       Gauss-Seidel 2D: -0.00245
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-5
       Gauss-Seidel 2D: -2.0E-4
       Gauss-Seidel 2D: -4.5000000000000004E-4
       Gauss-Seidel 2D: -8.0E-4
       Gauss-Seidel 2D: -0.00125
       Gauss-Seidel 2D: -0.0018000000000000002
       Gauss-Seidel 2D: -0.00245
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-5
       Gauss-Seidel 2D: -2.0E-4
       Gauss-Seidel 2D: -4.5000000000000004E-4
       Gauss-Seidel 2D: -8.0E-4
       Gauss-Seidel 2D: -0.00125
       Gauss-Seidel 2D: -0.0018000000000000002
       Gauss-Seidel 2D: -0.00245
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-5
       Gauss-Seidel 2D: -2.0E-4
       Gauss-Seidel 2D: -4.5000000000000004E-4
       Gauss-Seidel 2D: -8.0E-4
       Gauss-Seidel 2D: -0.00125
       Gauss-Seidel 2D: -0.0018000000000000002
       Gauss-Seidel 2D: -0.00245
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-5
       Gauss-Seidel 2D: -2.0E-4
       Gauss-Seidel 2D: -4.5000000000000004E-4
       Gauss-Seidel 2D: -8.0E-4
       Gauss-Seidel 2D: -0.00125
       Gauss-Seidel 2D: -0.0018000000000000002
       Gauss-Seidel 2D: -0.00245
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-5
       Gauss-Seidel 2D: -2.0E-4
       Gauss-Seidel 2D: -4.5000000000000004E-4
       Gauss-Seidel 2D: -8.0E-4
       Gauss-Seidel 2D: -0.00125
       Gauss-Seidel 2D: -0.0018000000000000002
       Gauss-Seidel 2D: -0.00245
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-5
       Gauss-Seidel 2D: -2.0E-4
       Gauss-Seidel 2D: -4.5000000000000004E-4
       Gauss-Seidel 2D: -8.0E-4
       Gauss-Seidel 2D: -0.00125
       Gauss-Seidel 2D: -0.0018000000000000002
       Gauss-Seidel 2D: -0.00245
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0

For h = 0.001

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 1.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 1.0
       Gauss-Seidel 2D: -5.0E-7
       Gauss-Seidel 2D: -2.0E-6
       Gauss-Seidel 2D: -4.5E-6
       Gauss-Seidel 2D: -8.0E-6
       Gauss-Seidel 2D: -1.2499999999999999E-5
       Gauss-Seidel 2D: -1.8E-5
       Gauss-Seidel 2D: -2.45E-5
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-7
       Gauss-Seidel 2D: -2.0E-6
       Gauss-Seidel 2D: -4.5E-6
       Gauss-Seidel 2D: -8.0E-6
       Gauss-Seidel 2D: -1.2499999999999999E-5
       Gauss-Seidel 2D: -1.8E-5
       Gauss-Seidel 2D: -2.45E-5
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-7
       Gauss-Seidel 2D: -2.0E-6
       Gauss-Seidel 2D: -4.5E-6
       Gauss-Seidel 2D: -8.0E-6
       Gauss-Seidel 2D: -1.2499999999999999E-5
       Gauss-Seidel 2D: -1.8E-5
       Gauss-Seidel 2D: -2.45E-5
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-7
       Gauss-Seidel 2D: -2.0E-6
       Gauss-Seidel 2D: -4.5E-6
       Gauss-Seidel 2D: -8.0E-6
       Gauss-Seidel 2D: -1.2499999999999999E-5
       Gauss-Seidel 2D: -1.8E-5
       Gauss-Seidel 2D: -2.45E-5
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-7
       Gauss-Seidel 2D: -2.0E-6
       Gauss-Seidel 2D: -4.5E-6
       Gauss-Seidel 2D: -8.0E-6
       Gauss-Seidel 2D: -1.2499999999999999E-5
       Gauss-Seidel 2D: -1.8E-5
       Gauss-Seidel 2D: -2.45E-5
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-7
       Gauss-Seidel 2D: -2.0E-6
       Gauss-Seidel 2D: -4.5E-6
       Gauss-Seidel 2D: -8.0E-6
       Gauss-Seidel 2D: -1.2499999999999999E-5
       Gauss-Seidel 2D: -1.8E-5
       Gauss-Seidel 2D: -2.45E-5
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: -5.0E-7
       Gauss-Seidel 2D: -2.0E-6
       Gauss-Seidel 2D: -4.5E-6
       Gauss-Seidel 2D: -8.0E-6
       Gauss-Seidel 2D: -1.2499999999999999E-5
       Gauss-Seidel 2D: -1.8E-5
       Gauss-Seidel 2D: -2.45E-5
       Gauss-Seidel 2D: 0.0

       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0
       Gauss-Seidel 2D: 0.0

    
The links to the Software Manual pages are listed below.        
[gaussSeidel2D](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/gaussSeidel2D.md)

## Task 4
Build routines that will assemble the matrix for a nine point stencil. Also build a code that will create the right hand side for the Poisson equation.

### Response


The links to the Software Manual pages are listed below.        
[]()
[]()
[]()

## Task 5
Build a code using previously created codes that will compute an approximate solution for the Poisson equation using a 9 point stencil for the Poisson equation in Task 1.

### Response



## Task 6
Search the internet for articles that compare 5 point stencil finite difference approximations and 9 point stencil finite difference approximations. Write a brief paragraph (3 or 4 sentences) that describes your findings. Include links to the sites you cite.

### Response
Upon reading some discussions on Math Forums, specifically the Math Stack Exchange, I didn't feel like I understood what the difference was, or why one would be better than the other, beyond what was discussed in class. I found "Truncated Gaussian RBF..." very helpful where in the article it makes the arguement that most applications of finitie difference stencils show the mehtod works, rather than which stencil or method is better. I also looked online for different applications of each stencil, and it was interesting that a few articles mentioned that one would work more than the other. There was also one that mentioned Comouter GPUs with finite difference stencils, where the GPU has some code to process to calcualte different stencil dimensions that would be optimal for the GPU to use. I thought it was neat that there is a way to see one might not work optimally for one GPU, but could be optimal for a GPU on a different system. 

#### Sources
[9 point solution for Poisson Equation](https://pdf.sciencedirectassets.com/271503/1-s2.0-S0898122199X00010/1-s2.0-0898122175900358/main.pdf?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFQaCXVzLWVhc3QtMSJHMEUCICrlQjEWON2asLY%2FbTZLKRE2IU7JlvNEL3K70iDG4emiAiEAwRguXVHV2DqoV%2B12HPrNV7utLZZgS8DdAdDOik2m3rEqtAMITRACGgwwNTkwMDM1NDY4NjUiDBFNrcRbB2lJcSQ1hSqRA413n9TOlfoZLImy8%2FywZe7ScwMRaFuNZX7hmxdl3JDhtmsgyoo5%2FWvOTtMnMBjvGnNnAaPhhQL9%2FCaIZm3VewU84P0kdDRp7XcmxUyFZHUftTiv0IjEyxRYkPZ5wCaZkEIIQ1CjeLXWBwBDWb2Y%2FKVzNEQSZBG0JPRLCRuDQsZrYkAiTVDehV5Hxw%2FGPYpxlCl8TtW9nldiAEN32rb5pcUJamzs58U0HPzCYEYdaeaGAvOGwpEuuHV2bGJFIxy7VNIbox3nB6M%2B2YZkKztYaaPWNfCOH7CFqvcmHdfDVATjSzujAnKqmsFssrH5JLvW4tYTTAE31kMqg9ePdWXUaIqcg4FLfX2gPx%2BszlQKIn20dzJFn9AdfOzTcvfcZCw%2FyaoN7q%2FCtMtUEhnhR00BCo%2FK72T7IQyEecT9PXfLilJeDe%2B0DhapGjggYlZIfyqI1eNoy0Mj2vuDoFfhtuwyNVvVw01tC0HSEwz%2B%2FMNlmZv75BwSTqb27jw%2FVxDRXbkBQIILxNpZ0wZ8nGkBeak5u3PtMIum5PMFOusBtZQwuP7bATTAQsNpI2MhxnnmDR4VnLUcPjmFGNayWBLon75lyIEm6Bl%2FThHbPFQJVcLmx3xEwIum9VRGXVXWt3ID6r8M7UVBh%2FbVvPQUBkKb%2BM1l5ESOHK1kD89ryjdKUk5ZMg1eKqWIrcwWVI%2BLcZGFRqnVmM4UEn57wD2WKFugUOunZnaSUrFWCljLBYtIgNjmzjfNMw5KXqE3%2BP9LXyluIbK33lMI6coXsyhdu2xuTcIrkOgjblj%2BnW94Gte28pdzJ4YMFKD1tnXuWsXbUleAiifnVjdqR9lO4E0K6j57Tk%2BCJNrLxbP0dw%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20200323T205939Z&X-Amz-SignedHeaders=host&X-Amz-Expires=300&X-Amz-Credential=ASIAQ3PHCVTYZMUP37MC%2F20200323%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=c64455f5da300b1a87de2ad450ceedb73c78fea8ee052531b2b52d4ea3b4601d&hash=1a1d76b361105fe780f038106a845f4b61eb4d6c9c053b025987025192d6d55b&host=68042c943591013ac2b2430a89b270f6af2c76d8dfd086a07176afe7c76c2c61&pii=0898122175900358&tid=spdf-ac8e352a-0696-424d-a63c-6d975070d1f7&sid=0a0bb9e19043614613199784585c4ff71710gxrqa&type=client)        
[Article: 5-point vs 9-point stencil](https://www.researchgate.net/figure/Five-point-versus-nine-point-stencils_fig5_258391173)        
[Optimized Finite Difference schemes perform poorly for decaying or growing oscillations](http://homepages.warwick.ac.uk/staff/E.J.Brambley/files/brambley-2016-jcp.pdf)        
[Development of High-Performance Software Components for Heterogeneous Architectures](https://www.researchgate.net/publication/259188042_Development_of_High-Performance_Software_Components_for_Heterogeneous_Architectures)             
[Truncated Gaussian RBF Differences](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.498.7683&rep=rep1&type=pdf)
