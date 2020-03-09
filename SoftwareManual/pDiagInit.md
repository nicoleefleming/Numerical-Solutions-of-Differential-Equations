# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           pDiagInit

**Author:**                 Nicole Fleming

**Language:**              Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/IterativeMethods/*.java -d classes
    "FILE-PATH" -cp classes IterativeMethods.java
    
These commands should also work.

**Description/Purpose:** This routine will initialize a pentadiagonal matrix into a sparse storage method, with 5 rows and n columns. 

**Input:** There are 5 inputs: double[] ld, double[] al, double[] ad, double[] as, double[] ud. 

**Output:** This routine will return a 5 x n dimensional array pDiag. The pDiag array is initailized by putting each 1D array into the 5D array. There is logic for handling the rows of zeros that is coming, but it is not implemented yet. 

      1  1  -4  1  1
      1  1  -4  1  1
      1  1  -4  1  1
      1  1  -4  1  1
      1  1  -4  1  1
      1  1  -4  1  1
      1  1  -4  1  1
      1  1  -4  1  1
      1  1  -4  1  1
      1  1  -4  1  1
        

**Usage/Example:**

The routine has five 1D arrays that it stores into one matrix. The logic for the rows of zeros is coming, but this is the basic initialization.
                  
        pDiag = iter.pDiagInit(ld, al, ad, as, ud);

        for (int i = 0; i < n; i++)
        {
            System.out.println(pDiag[0][i] + " " + pDiag[1][i] + " " + pDiag[2][i] + " " + pDiag[3][i] + " " +pDiag[4][i] + "\n");
        }

**Implementation/Code:** The following is the unfinished code for DirichletSolve
 
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

     

**Last Modified:** 8/March/2020
