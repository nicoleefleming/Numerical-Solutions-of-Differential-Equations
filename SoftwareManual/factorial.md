# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           factorial

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

**Description/Purpose:** This routine will solve the factorial of a number passed in.

**Input:** There is one input, int calculate, that is needed to have it's factorial found, i.e. 36/3!, the code would 
be written 36/factorial(3).

**Output:** This routine returns a single number as a result of this computation. For a simple number like 3 it will do the computation
3 * 2 * 1, and return 6.

**Usage/Example:**

The routine has one arguemnt needed in order to compute the factorial. That argument is passed in, and the computation done. It was tested
by the following, using a test integer. The way it is called to find the coefficients makes it return one integer. This could be modified easily 
to allow it to return a double if needed.

        int test;
        //Test factorial
        test = triOps.factorial(6);
        System.out.println(test);

Output from the lines above where calculate is  3, and 6 respectively.

      6 
      720

These numbers show the result of computing 3! = 3 * 2 * 1 = 6, and 6! = 6 * 5 * 4 * 3 * 2 * 1 = 720, which is accurate to what other tools 
calculate it as. 

**Implementation/Code:** The following is the code for factorial

     public int factorial(int calculate) {
        if (calculate <= 0) {
            calculate = 1;
            return calculate;
        } else {
            int res = 1, i;
            for (i = 2; i <= calculate; i++) {
                res *= i;
            }
            calculate = res;

            return calculate;
        }
     }

**Last Modified:** 31/January/2020
