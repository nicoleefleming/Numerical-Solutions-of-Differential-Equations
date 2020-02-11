# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           AssignLambda

**Author:**                 Nicole Fleming

**Language:**              Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/TriDiagOps/*.java -d classes
    "FILE-PATH" -cp classes TriDiagOps.java
    
These commands should also work.

**Description/Purpose:** This routine will solve for the lambda values as found when computing the Dirichlet method in class.

**Input:** There are six inputs: double[][] A, double[] lambda, double h, double b1, double c, double pi. The input is used to calculate the values in the lamda array. The value of h is the value specified in the problem given as 1/m+1, and for the problems specific to TS#3, 1/8, and 1/256. b1 and c were two values I wasn't sure of where they came from. pi is the numerical value of pi to the 10th or 11th digit. 

**Output:** This routine returns a one dimensional array of doubles that can be printed to the screen to verify 
values. 

**Usage/Example:**

The routine has six arguments needed to return the values after assinging lamdba. The code is written using for loops, and 
the data type is a double due to needing more precision with the output.

        lambda = triOps.AssignLambda(A, lambda, h, b1, c, pi);
        for(int i = 0; i < n; i++)
        {
                System.out.println(ts3A[i][j] + " ");
                System.out.println("\n");
        }

Output from the lines above where lambda was unassigned being passed in; A is the tridiagonal matrix of al, ad, and as; h is 1 for testing; b1 is 2; c is 8; and pi is 3.1415926...to the 10th digit, before entering the method Assignlambda:

      6
      -10 
      6 
      

The first value shows the value found for the lambda in the first column, and is printed as expected, when verified with a calculator. The second and third values are also what was expected with the values found on a calculator.. 
This is for one case, using the above specified values to pass into the function. The values found with h = 1/8 and 1/256 were not verified, as the confusion came with what b1 and c should be with respect to A. 

**Implementation/Code:** The following is the code for AssignLambda

    public double[] AssignLambda(double[][] A, double[] lambda, double h, double b1, double c, double pi)
    {
        for (int i = 0; i < A.length; i++)
        {
            lambda[i] = A[i][i] - (2*b1)*sqrt(c/b1)*cos(h*pi*i);
        }

        return lambda;
    }

**Last Modified:** 10/February/2020
