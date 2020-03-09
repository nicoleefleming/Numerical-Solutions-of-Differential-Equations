# Tasksheet 5
 
 IN PROCESS
 
## Task 1
Write a finite difference code for the nonlinear elliptic problem
θ′′(t) + (g/l) * sin(θ(t)) = 0
with 
θ(0) = 1 and theta(2) = −1. Use (g/l) = 1.
### Response
In going through the steps the textbook sets up for solving this problem two new functions were created to aide with this problem, they are listed at the bottom of this prompt. The idea was to take some theta as a function of t and solve for theta''. Theta'' was found to be equal to -sin(theta(t)) with the parameter given that (g/l) = 1. The first step for this problem was to develop a non-linear initializing matrix method. The second task was to develop another method that took this information and solved for the finite difference of the problem. If I am correct, I went with the idea that the initial Finite Difference method written in Tasksheet 1 would work. If that is not true, some re-coding will be done to get accurate numbers. However, in documentation in the text, and when looking into it online, did not steer me away from reusing the method already written.

The results of this task with a 10 x 10 diagonal system, using the matrix 2.82 in the book was found to be     

1.0; -1.0; 1.0; -1.0; 1.0; -1.0; 1.0; -1.0; 1.0; -1.0;    

1.0; -3.616410206630505E-6; 7.23283349168824E-6; -1.0849269855173205E-5; 1.4465719297307444E-5; -1.8082181817868914E-5; 2.169865741707966E-5; -2.5315146094939678E-5; 2.893164785144897E-5; -3.2548162686829585E-5;    

0.5; 3.616410206686016E-6; 3.923528169025303E-11; -1.1770562302615417E-10; 2.3541107951885465E-10; -3.9235126259029585E-10; 5.885261722404778E-10; -8.239356419393348E-10; 1.0985795051466371E-9; -1.4124577063580096E-9;     

0.16666666666666666; -1.808205103148719E-6; 1.3078399475355761E-11; -2.9605846912873843E-16; 1.0084504546832968E-15; -2.5720138801278387E-15; 5.04225650858122E-15; -8.780004658036882E-15; 1.3951788840992835E-14; -2.074264562423161E-14;    

0.041666666666666664; 6.027350344106619E-7; 1.0898687045297813E-11; 6.322083467401124E-17; -8.456792346800492E-17; 2.078776832319561E-16; -4.598468529576996E-16; 8.281879208811183E-16; -1.3629166468915242E-15; 2.103935087417978E-15;    

0.008333333333333333; -1.506837586044002E-7; 2.1797349805102238E-12; -6.611222277796677E-17; 5.908552982317688E-18; 1.1019753514874725E-17; -1.2416473576942801E-17; 2.781808298260394E-17; -3.487758059849759E-17; 3.876893951767652E-17;    

0.001388888888888889; 3.013675171567587E-8; 7.629082550706069E-13; 1.0210788361605002E-17; -9.392291586754771E-18; -1.9520046942429635E-18; 2.879467343635298E-18; -4.459563910161167E-18; 9.57441809817169E-18; -1.630542994852895E-17;    

1.984126984126984E-4; -5.022791952540365E-9; 1.0898687250637129E-13; -3.788081804446311E-18; 1.2510880190933343E-18; 2.454363328830329E-19; 3.3556471032324563E-19; -2.934272318756838E-19; 6.095170199061154E-19; -1.4276288979578772E-18;     

2.48015873015873E-5; 7.175417074815654E-10; 2.3354324604672663E-14; 4.641420490520567E-19; -4.035133867950194E-19; -5.059478229902519E-20; 5.519426181183146E-20; 2.5292454081128653E-20; -1.0854520737225377E-20; 2.7123051272844995E-20;     

2.7557319223985893E-6; -8.969271343096052E-11; 2.59492525128631E-15; -1.0912965891160656E-19; 4.2201892402752574E-20; 1.0918557142468354E-20; 2.304859424507752E-21; -7.035945323795552E-21; 4.434137262868689E-22; 9.673275192234932E-22;     

      
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
Following the same procedure as in task 1 by following the book, the same methods were used, the only change being to sin(θ) was replaced by just using θ in the calcualtions. The results for a 10 x 10 matrix are listed below.     
      
1.0; -1.0; 1.0; -1.0; 1.0; -1.0; 1.0; -1.0; 1.0; -1.0; 
1.0; -3.616410206630505E-6; 7.23283349168824E-6; -1.0849269855173205E-5; 1.4465719297307444E-5; -1.8082181817868914E-5; 2.169865741707966E-5; -2.5315146094939678E-5; 2.893164785144897E-5; -3.2548162686829585E-5; 
0.5; 3.616410206686016E-6; 3.923528169025303E-11; -1.1770562302615417E-10; 2.3541107951885465E-10; -3.9235126259029585E-10; 5.885261722404778E-10; -8.239356419393348E-10; 1.0985795051466371E-9; -1.4124577063580096E-9; 
0.16666666666666666; -1.8082051031764745E-6; 1.307842723050786E-11; -2.40548745520745E-16; 8.696747026273475E-16; -2.3222173402158495E-15; 4.681440844053683E-15; -8.252657957586954E-15; 1.3230158274856779E-14; -1.9826732283512353E-14; 
0.041666666666666664; 6.027350344314786E-7; 1.0898680106615668E-11; 4.9343509654362456E-17; -8.682520185973973E-17; 2.2524678243057536E-16; -4.860772211496765E-16; 8.783909220990211E-16; -1.4548506323056226E-15; 2.2373424751373073E-15; 
0.008333333333333333; -1.5068375861307381E-7; 2.17973671518076E-12; -6.264278564232058E-17; -4.0472484087552784E-18; 3.0265471967472184E-17; -7.332798763901792E-17; 1.5044901091061033E-16; -2.467412298086778E-16; 3.759290390205685E-16; 
0.001388888888888889; 3.0136751718061114E-8; 7.629082550706069E-13; 9.9939214573283E-18; -7.824134436349514E-18; -7.152588203924873E-18; 1.507717828629127E-18; -1.7105728930271025E-18; 6.088193352522679E-18; -1.1261828816849923E-17; 
1.984126984126984E-4; -5.022791953082466E-9; 1.0898684539800825E-13; -3.8422950684132326E-18; 3.71334617199798E-19; 2.1275479696863096E-18; 2.0940707206814393E-21; -2.71012245975811E-19; 6.418324555520025E-20; -9.060665286436409E-21; 
2.48015873015873E-5; 7.175417075865975E-10; 2.3354327993011247E-14; 4.675295412831391E-19; -3.0097851922881956E-19; -3.0379747542649664E-19; 2.700187096377727E-20; 3.198213915269397E-20; 1.5758145147529013E-20; -7.768732777574792E-21; 
2.7557319223985893E-6; -8.969271344705414E-11; 2.5949248277181377E-15; -1.0997668946230143E-19; 1.8435463113960395E-20; 6.525015604270298E-20; -2.2501932210767406E-21; -7.703277700167968E-21; 2.8168891406845832E-21; -4.389238951317562E-21; 
     

The Software Manual Pages to the methods used are listed below                 
[nonLinearInit](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/nonLinearInit.md)            
[nonlinearFDSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/nonlinearFDSolve.md)           
[findCoeffs](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/findCoeffs.md)           
[factorial](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/factorial.md)           
[triDiagSolve](https://github.com/nicoleefleming/math5620/blob/master/SoftwareManual/triDiagSolve.md)          

## Task 3
 Let's start into approximate solution of partial difference equations. With the simplest equation,       
Δu = ∂^2u/∂x^2 + ∂^2u/∂y^2 = f(x,y)       
Start by writing a code to initialize the associated pentadiagonal matrix using central differences. Do this using the sparse storage into 5 vectors. Also, write a routine that initializes the right hand side of the system of equations.       
### Response   
I am still trying to figure out how to put in the logic to account for the rows of zeros in the matrix, to take it out of the sparse matrix that has been coded for solving. 

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
