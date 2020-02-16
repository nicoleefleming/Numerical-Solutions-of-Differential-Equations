# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           aXaverage

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

**Description/Purpose:** This routine will take an array, calculate the average of the values between every two values, (i.e. the first and second average, the second and third average, etc.),
and return an array of these average values. The purpose is to use this as the k(x) in the equation (k(x) * u')' = f(x).

**Input:** There are two inputs, both one dimenstional double arrays. The first is the initialized k(x) array, aX, and the second is the array that will be edited and returned, kX.


**Output:** This routine will return a one dimensional array of the averaged values of the original array.



**Usage/Example:**

The routine has two arguments the two 1D array and a 1D  array. To test that it was working the following lines of code were used.
         
        kX = linsolve.aXaverage(aX, kX);
        //test a(x) avg
        for (int i = 0; i < kX.length; i++)
        {
            System.out.println(kX[i] + " ");
        }
        
With k(x) initialized to 1, the values returned were 1. When the value was changed to 4, and other numbers the values remained accurate.
        

**Implementation/Code:** The following is the code for aXaverage
 
     public double[] aXaverage(double[] a, double[] k)
     {
        int n = k.length;

        //loop through a, to compute the average between numbers
        //returning final k array.
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                k[i] = (1 / 2.0) * a[i] + a[i - 1];
            } else {
                k[i] = (1 / 2.0) * (a[i] + a[i + 1]);
            }
        }

        return k;
    }
     

**Last Modified:** 15/February/2020
