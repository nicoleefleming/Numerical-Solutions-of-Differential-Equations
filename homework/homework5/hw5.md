# Tasksheet 5
 
 IN PROCESS
 
## Task 1
Write a finite difference code for the nonlinear elliptic problem
θ′′(t) + (g/l) * sin(θ(t)) = 0
with 
θ(0) = 1 and theta(2) = −1. Use (g/l) = 1.
### Response
Working on the code will push when finished.

## Task 2
Simplify the problem in Task 1 using the small angle assumption 
sin(θ) ≈ θ.
### Response
Working on the code will push when finished.   

## Task 3
 Let's start into approximate solution of partial difference equations. With the simplest equation,
Δu = ∂^2u/∂x^2 + ∂^2u/∂y^2 = f(x,y)
Start by writing a code to initialize the associated pentadiagonal matrix using central differences. Do this using the sparse storage into 5 vectors. Also, write a routine that initializes the right hand side of the system of equations.
### Response   
Working on the code will push when finished.

## Task 4
Write a routine that implements Jacobi iteration for the approximate solution linear systems of equations.
### Response
Working on the code will push when finished.

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
