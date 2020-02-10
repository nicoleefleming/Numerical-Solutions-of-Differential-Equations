# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           DirichletSolve

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

**Description/Purpose:** This routine will solve for the V values as found when computing the Dirichlet method in class. This method remains unfinished due to a lot of confusion behind what was supposed to be happnening and how to calculate the values to graph. Most likely it is due to a lack of understanding, or an overcomplication of simple things.

**Input:** There are eight inputs:double[][] A, double[] lambda, double[] b, double b1, double c, int k,  double u0, double u1. The input is used to calculate the values in the v array. The value of V, after being solved for would then be printed into a file, the values of x in another for ease of graphing using MatLab. The value of k is the value specifying the order of the derivative. I was unsure if h was needed in the problem given as 1/m+1, and b1 and c were two values I wasn't sure of where they came from, u0 is the lower bound, and u1 is the upperbound.

**Output:** This routine remains unfinished, and no output was Tested. 

**Usage/Example:**

The routine has eight arguments. Ideally this routine would return two files, Vvalues and X where these could then be used to graph the result in MatLab to include here.

**Implementation/Code:** The following is the unfinished code for DirichletSolve

     public double[] DirichletSolve(double[][] A, double[] lambda, double[] b, double b1, double c, int k,  double u0, double u1) throws IOException
    {
        File file = new File("Vvalues");
        FileOutputStream output = new FileOutputStream(file);

        //TODO: SOLVE FOR ROOTS
        double[] r = new double[k];
        double[] v = new double[A.length];
        double mew;
        double gamma;

        for (int i = 0; i < k; i++)
        {
            if(k == 2)
            {
                //#DO-DO??
                r[i] = solveRoots(A, lambda, b1, c, i);

            }
        }

        //TODO: SOLVE FOR CONSTANTS

        //TODO: SOLVE FOR V

        //TODO: ASSIGN V

        //TODO: PUT VALUES OF X AND V FOR 0.01-0.99 INTO A FILE
        //ASSIGN 0 AND 1 BEFORE THE LOOP

        output.close();
        //TODO: RETURN V
        return b;
    }

**Last Modified:** 10/February/2020
