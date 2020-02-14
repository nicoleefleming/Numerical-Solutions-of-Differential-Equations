# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations

**Routine Name:**           NeumannSolve

**Author:** Nicole Fleming

**Language:** Java. The code can be compiled using the commandline, I use it with gradle specifically when I do this, but I also compile with IntelliJ.

For example,

    commandline: gradle build .
                 gradle run
    IntelliJ:    Ctrl+F9 (build)
                 Shift+F9 (run)

will produce in running the program in both sources. If one does not have the gradle installed with the project, to run from the commandline

    "FILE-PATH" src/LinearSolvers/*.java -d classes
    "FILE-PATH" -cp classes LinearSolvers.java
    
where the method belonds to the class LinearSolvers. These commands should also work.

**Description/Purpose:** This routine will take input to solve a Boundary Value Problem with Neumann conditions. this method takes an array of finite difference coefficients due to the initial conditions of the Neumann problem being u'(a) and u'(b), this creates two more unknowns, and the finite difference method is beneficial to aid in solving Neumann coefficient matrices.

**Input:**  This routine takes four input values, a two dimentional double array, a one dimensional double array, and two doubles for the initial conditions. The array is sent through the findCoeffs method prior to being passed in, and the two doubles are provided in the problem.

**Output:** This method will output to two files, where one is the x values, and the other is the values of U in the A * U = F equation. The test code will print out the values of U to the console after calculations are complete.

    -1.0
    
     1.0
    
    -0.7762500000000001    

**Usage/Example:**
To test this method the following code was used. Before the NeumannSolve was called, the Coeffs array was pre loaded, and after the answer would be printed to the console.
       
       Coeffs = triOps.findCoeffs(2, x, 1, b);
       b = linsolve.NeumannSolve(Coeffs, b, 1, 3);

        //Test for Neumann with values from Finite Difference Calc
        for(int i = 0; i < n; i++)
        {
            System.out.println(b[i] + "\n");
        }


**Implementation/Code:** The following is the code for NeumannSolve

      public double[] NeumannSolve(double[][] array, double[] u, double uaprime, double ubprime)
      {
        try {
            //for : Neumann Conditions specifically those on the third tasksheet.
            BufferedWriter writer = new BufferedWriter(new FileWriter("Y.txt"));
            BufferedWriter xwriter = new BufferedWriter(new FileWriter("X.txt"));
            // for 1:3 with f(x) = 0
            //BufferedWriter writer = new BufferedWriter(new FileWriter("Vvalues.txt"));
            //BufferedWriter xwriter = new BufferedWriter(new FileWriter("Xvalues.txt"));

            double[] solve;
            double[] fullSol;
            int len = array.length;
            double[] fx = new double[len];
            fx = u;
            //use coeffs for array values.
            //1, {0,1,2}, 2
            //BEFORE ENTER INTO NEUMANN

            //need to initialize RHS for solving, with values to prove if it is right.
            solve = RHSinit(fx, 1, 3);

            //LU factorization?? or GEsolve??
            solve = forward(array,solve);
            fullSol = backward(array, solve);


            //Print out the values to a file of U and f(x)
            for (int i = 0; i < len; i++) {
                //write file to Vvalues.txt for all u
                String writeMe = Double.toString(fullSol[i]);
                writer.write(writeMe);
                writer.write("\n");

                //write to file all x for i
                String xVal = Double.toString(i);
                xwriter.write(xVal);
                xwriter.write("\n");
            }

            writer.close();
            xwriter.close();
            u = fullSol;

        }
        catch(IOException io)
        {
            System.out.println("IO EXCEPTION CAUGHT \n");
            io.printStackTrace();
        }

        //return answer array.
        return u;
    }

**Last Modified:** 14/February/2020
