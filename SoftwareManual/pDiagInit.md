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

        -4 -4 -4 -4 -4 -4 -4 -4 -4  //ad
        
        1 : 1      //ld : ud
        1 : 1
        1 : 1
        1 : 1
        1 : 1
        1 : 1
        
        1 :: 1     //al :: as
        1 :: 1
        1 :: 1
        1 :: 1
        1 :: 1
        1 :: 1
        1 :: 1
        1 :: 1

**Usage/Example:**

The routine has five 1D arrays that it stores into one matrix. The logic for the rows of zeros is coming, but this is the basic initialization.
                  
        iter.pDiagInit(ld, al, ad, as, ud);
        for(int i = 0; i < ad.length; i++)
        {
          System.out.println(ad[i] + " ");  
        }
        for(int i = 0; i < ld.length; i++)
        {
            System.out.println(ld[i] + " : "); 
            System.out.println(ud[i] + " \n");
        }
        for(int i = 0; i < as.length; i++)
        {
            System.out.println(al[i] + " :: ")
            System.out.println(as[i] + " \n");
        }
        
        

**Implementation/Code:** The following is the unfinished code for DirichletSolve
 
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

     

**Last Modified:** 10/March/2020
