# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           calculateP

**Author:**                 Nicole Fleming

**Language:**              Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/LinearSolvers/*.java -d classes
    "FILE-PATH" -cp classes LinearSolvers.java
    
These commands should also work.

**Description/Purpose:** This routine will solve for the value pf p in the equation log(||E||) = log(C) + plog(h). This tells us the error/convergence rate.

**Input:** There are  inputs: double[] arr4err, double length. The arr4err is initialized into 4 columns, with many rows, holding various mesh values of h, ||E||, log(h), and log(||E||) respectively. The length is how many rows the array has. 

**Output:** This routine will return a double p. This is based on the equation A.24 in the textbook, located in Appendix A. The returned value will read      
  
    p is 0.9833132538405166  

**Usage/Example:**

The routine has two arguments the 2D array and a length condition. To test that it was working the following lines of code were used.
                  
        //The arrays H, eNorm, logH, and logerr are initialized inline, this may get moved into an initialize function, but for now, it         //is in the main method. 
        //assign array4error values, H, eNorm, logH, logerr
        for (int i = 0; i < H.length; i++)
        {
            arrayForError[0][i] = H[i];
            arrayForError[1][i] = eNorm[i];
            arrayForError[2][i] = logH[i];
            arrayForError[3][i] = logerr[i];
        }

        double length = H.length;
        double P = 0;
        P = linsolve.calculateP(arrayForError, length);
        
        //test for P
        System.out.println("p is " + P);
        
This returns the following if k(x) = sin(pi * x),

      p is 0.9810922471434016

and if k(x)= 1, the value returned is 

      p is 0.9833132538405166
      
.

**Implementation/Code:** The following is the unfinished code for DirichletSolve
 
    public double calculateP(double[][] arr4err, double length)
    {

        double ans = 0;

        //calculate ans based on equation on pg 255 in Appendix A in textbook
        for (int i = 0; i < length-1; i++)
        {
            ans = ((arr4err[3][i]/arr4err[3][i+1])/(arr4err[2][i]/arr4err[2][i+1]));
        }

        return ans;
    }
     

**Last Modified:** 21/February/2020
