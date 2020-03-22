# Tasksheet 5
 
## Task 1
Write a finite difference code for the nonlinear elliptic problem
θ′′(t) + (g/l) * sin(θ(t)) = 0
with 
θ(0) = 1 and theta(2) = −1. Use (g/l) = 1.
### Response
In following the textbook and using notes in class, the two dimensional array that was solved for had 1/h^2 on the first super and first sub diagonal, with -2/h^2 +(g/l)* cos(U_1^(i)). The method to initialize the tridagonla system is [nonLinearInit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/nonLinearInit.md). The solution matrix for the finite difference of this is found by calling the method [nonlinearFDSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/nonlinearFDSolve.md) which first initializes the matrix, and recieves the data of x and b to solve for the finite differences. The code here changed from the [findCoeffs] function a bit, when it was seen that just sending the non-linear problem through the linear version resulted in some strange numbers.     

     1.0; -1.0; 1.0; -1.0; 1.0; -1.0; 1.0; -1.0; 1.0;      
     1.0; -3.616410206630505E-6; 7.23283349168824E-6; -1.0849269855173205E-5; 1.4465719297307444E-5; -1.8082181817868914E-5; 2.169865741707966E-5; -2.5315146094939678E-5; 2.893164785144897E-5;      
     0.5; 3.616410206686016E-6; 3.923528169025303E-11; -1.1770562302615417E-10; 2.3541107951885465E-10; -3.9235126259029585E-10; 5.885261722404778E-10; -8.239356419393348E-10; 1.0985795051466371E-9;       
     0.16666666666666666; -1.808205103148719E-6; 1.3078399475355761E-11; -2.9605846912873843E-16; 1.0084504546832968E-15; -2.5720138801278387E-15; 5.04225650858122E-15; -8.780004658036882E-15; 1.3951788840992835E-14;      
     0.041666666666666664; 6.027350344106619E-7; 1.0898687045297813E-11; 6.322083467401124E-17; -8.456792346800492E-17; 2.078776832319561E-16; -4.598468529576996E-16; 8.281879208811183E-16; -1.3629166468915242E-15;      
     0.008333333333333333; -1.506837586044002E-7; 2.1797349805102238E-12; -6.611222277796677E-17; 5.908552982317688E-18; 1.1019753514874725E-17; -1.2416473576942801E-17; 2.781808298260394E-17; -3.487758059849759E-17;      
     0.001388888888888889; 3.013675171567587E-8; 7.629082550706069E-13; 1.0210788361605002E-17; -9.392291586754771E-18; -1.9520046942429635E-18; 2.879467343635298E-18; -4.459563910161167E-18; 9.57441809817169E-18;      
     1.984126984126984E-4; -5.022791952540365E-9; 1.0898687250637129E-13; -3.788081804446311E-18; 1.2510880190933343E-18; 2.454363328830329E-19; 3.3556471032324563E-19; -2.934272318756838E-19; 6.095170199061154E-19;      
     2.48015873015873E-5; 7.175417074815654E-10; 2.3354324604672663E-14; 4.641420490520567E-19; -4.035133867950194E-19; -5.059478229902519E-20; 5.519426181183146E-20; 2.5292454081128653E-20; -1.0854520737225377E-20;      


       
The Software Manual Pages to the methods used are listed below           
[nonLinearInit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/nonLinearInit.md)         
[nonlinearFDSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/nonlinearFDSolve.md)          
[factorial](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/factorial.md)        
[triDiagSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/triDiagSolve.md)        

## Task 2
Simplify the problem in Task 1 using the small angle assumption 
sin(θ) ≈ θ.
### Response
Upon reding the first, here is the second for the small angle results. 

    1.0; -1.0; 1.0; -1.0; 1.0; -1.0; 1.0; -1.0; 1.0; 
    1.0; -3.616410206630505E-6; 7.23283349168824E-6; -1.0849269855173205E-5; 1.4465719297307444E-5; -1.8082181817868914E-5; 2.169865741707966E-5; -2.5315146094939678E-5; 2.893164785144897E-5; 
    0.5; 3.616410206686016E-6; 3.923528169025303E-11; -1.1770562302615417E-10; 2.3541107951885465E-10; -3.9235126259029585E-10; 5.885261722404778E-10; -8.239356419393348E-10; 1.0985795051466371E-9; 
    0.16666666666666666; -1.808205103148719E-6; 1.3078399475355761E-11; -2.9605846912873843E-16; 1.0084504546832968E-15; -2.5720138801278387E-15; 5.04225650858122E-15; -8.780004658036882E-15; 1.3951788840992835E-14; 
    0.041666666666666664; 6.027350344106619E-7; 1.0898687045297813E-11; 6.322083467401124E-17; -8.456792346800492E-17; 2.078776832319561E-16; -4.598468529576996E-16; 8.281879208811183E-16; -1.3629166468915242E-15; 
    0.008333333333333333; -1.506837586044002E-7; 2.1797349805102238E-12; -6.611222277796677E-17; 5.908552982317688E-18; 1.1019753514874725E-17; -1.2416473576942801E-17; 2.781808298260394E-17; -3.487758059849759E-17; 
    0.001388888888888889; 3.013675171567587E-8; 7.629082550706069E-13; 1.0210788361605002E-17; -9.392291586754771E-18; -1.9520046942429635E-18; 2.879467343635298E-18; -4.459563910161167E-18; 9.57441809817169E-18; 
    1.984126984126984E-4; -5.022791952540365E-9; 1.0898687250637129E-13; -3.788081804446311E-18; 1.2510880190933343E-18; 2.454363328830329E-19; 3.3556471032324563E-19; -2.934272318756838E-19; 6.095170199061154E-19; 
    2.48015873015873E-5; 7.175417074815654E-10; 2.3354324604672663E-14; 4.641420490520567E-19; -4.035133867950194E-19; -5.059478229902519E-20; 5.519426181183146E-20; 2.5292454081128653E-20; -1.0854520737225377E-20; 
    

The Software Manual Pages to the methods used are listed below           
[nonLinearInit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/nonLinearInit.md)         
[nonlinearFDSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/nonlinearFDSolve.md)          
[factorial](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/factorial.md)        
[triDiagSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/triDiagSolve.md)      


## Task 3
 Let's start into approximate solution of partial difference equations. With the simplest equation,       
Δu = ∂^2u/∂x^2 + ∂^2u/∂y^2 = f(x,y)       
Start by writing a code to initialize the associated pentadiagonal matrix using central differences. Do this using the sparse storage into 5 vectors. Also, write a routine that initializes the right hand side of the system of equations.       
### Response   
The first method used here, is the pDiagInit, which uses sparse storage of the matrix form, only storing the values in the non-zero diagonals ad, as, al, ud, and ld. Where ad is the main diagonal, as is the first super diagonal, al is the first sub diagonal, ud is the second super diagonal, and ld is the second lower diagonal. 
       
        
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
    
        
    public double[] jacobi(double[] ad, double[] as1, double[] al1, double[] as2, double[] al2, double[] b, double[] x, int n, int m)
    {
        int nx = n; //length of row
        int ny = m; //length of column
        int nxny = nx*ny;
        double[] xold = x;
        double[] xnew = new double[nxny];

        System.out.println(b.length);
        for(int i = 0; i < nxny; i++)
        {
            xnew[i] = b[i];
        }

        for (int i = 0; i < nxny - 1; i++)
        {
            xnew[i] = xnew[i] - (as1[i]*xold[i + 1]);
            xnew[i] = xnew[i] - (al1[i]*xold[i]);
        }

        for (int i = 0; i < nxny - nx; i++)
        {
            xnew[i] = xnew[i] - (as2[i] * xold[i + nx]);
            xnew[i] = xnew[i] - (al2[i] * xold[i]);
        }

        for (int i = 0; i < nxny; i++)
        {
            xnew[i] = xnew[i]/ad[i];
        }

        return xnew;
    }




[jacobi](https://github.com/nicoleefleming/math5620/tree/master/SoftwareManual/jacobi.md)    

## Task 5
Write a code that solves the elliptic problem in Task 1 using your Jacobi iteration. Use f(x,y) = xy and homogeneous boundary conditions.
### Response
I believe the following is true with the code below. This is subject to change, since x and y are two unknowns, and currently Jacobi only solves for x, so I will update if I figure out how to or if y needs to be incorporated. Here is the code, followed by the solution. Also, I will add in the error and tolerance parameters in the while loop, but they are not there at the moment. 

        double[] tenAns = new double[pdx.length];
        iters = 0;
        maxIters = 100;
        while(iters < maxIters)
        {
            tenAns = iter.jacobi(ad, as,al, ud, ld, rs, x, n, n);
            iters++;
            x[0] = xZero/iters;
        }
        for (int i = 0; i < tenAns.length; i++)
        {
            System.out.println("Jacobi Solution :" + tenAns[i] +" ");
        }

solution:         

      Jacobi Solution :-0.4949531113597013 
      Jacobi Solution :-0.49999638358979354 
      Jacobi Solution :-2.0000036164102064 
      Jacobi Solution :-4.499996383589794 
      Jacobi Solution :-8.000003616410208 
      Jacobi Solution :-12.499996383589798 
      Jacobi Solution :-18.00000090410255 
      Jacobi Solution :-24.499999095897447 
      Jacobi Solution :-32.0 

## Task 6
Search the internet for discussions of using finite difference methods for elliptic operators. Write a brief paragraph (3 or 4 sentences) that describes your findings. Include links to the sites you cite.
### Response
The article in Sciendo discussed the uses and had more of a walk through of the convergence, the method, and the truncation error. I found the other discussion more interesting, with the modeling of Diffusion of the charge effects. The application of using the finite difference method to calculate volume was very interesting, and the modeling was really interesting to read about.

#### Sources
[Sciendo](https://content.sciendo.com/configurable/contentpage/journals$002famns$002f3$002f1$002farticle-p311.xmlSciendo)       
[Modeling of Diffuse Charge Effects in a Microfluidic Based Laminar Flow Fuel Cell](https://www.researchgate.net/publication/228821547_Modeling_of_Diffuse_Charge_Effects_in_a_Microfluidic_Based_Laminar_Flow_Fuel_Cell)       
