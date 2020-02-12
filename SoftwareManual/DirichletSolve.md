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

    "FILE-PATH" src/LinearSolvers/*.java -d classes
    "FILE-PATH" -cp classes LinearSolvers.java
    
These commands should also work.

**Description/Purpose:** This routine will solve for the values of x in the LUx = f(x) factorization. This function will take values, compute the correct values, print out the values of x and the respective y coordinate for each point into a two seperate files, for ease to graph. 

**Input:** There are  inputs: double[][] array, double[] u, double ua, double ub. The array is an array of coefficients, tridiagonal or just a square matrix. U is the array of the unsolved right-hand side that will be solved for in this method. This array, u, could be a double or int, since it is only used to find the size. The two doubles ua, and ub are the given endpoints for the Dirichlet conditions ua being the lower value, and ub the higher value.

**Output:** This routine will return a one dimensional array u, and will print values to graph to two files. The files are overwritten each time the program is run, but the files used are X.txt, Y.txt, Xvalues.txt, and Vvalues.txt. Below is an example of u where the size is 10.

    -5.820766091346741E-11 
    -5.174014303419325E-11 
    -4.365574568510055E-11 
    -3.7252902984619135E-11 
    -3.233758939637078E-11 
    -2.8509874733126902E-11 
    -2.5465851649641997E-11 
    -2.2995619126308117E-11 
    -2.095475792884827E-11 
    -8.910802411444396E-10

**Usage/Example:**

The routine has two arguments the 2D array and a 1D  array. To test that it was working the following lines of code were used.
                  
        solution = linsolve.DirichletSolve(ts3A, b, ua, ub);

        for (int i = 0; i < n; i++)
        {
            System.out.println(solution[i] + " ");
        }

**Implementation/Code:** The following is the unfinished code for DirichletSolve
 
    public double[] DirichletSolve(double[][] array, double[] u, double ua, double ub)
    {
        try {
            //for : with f(x) = 10*sin(2i*pi)
            BufferedWriter writer = new BufferedWriter(new FileWriter("Y.txt"));
            BufferedWriter xwriter = new BufferedWriter(new FileWriter("X.txt"));
            // for 1:3 with f(x) = 0
    //            BufferedWriter writer = new BufferedWriter(new FileWriter("Vvalues.txt"));
    //            BufferedWriter xwriter = new BufferedWriter(new FileWriter("Xvalues.txt"));



            double[] solFor;
            double[] sol;
            int len = array.length;
            double[] fx = new double[len];
            //initialize Right Hand Side
            //first case, f(x) = 0;
            fx = RHSinit(u, ua, ub);

            //second case 10sin(2pix)


            //Linear Solve for Lower*y = RHS
            solFor = forward(array, fx);

            //Linear Solve for Upper*u = y
            sol = backward(array, solFor);

            //Print out the values to a file of U and f(x)
            for (int i = 0; i < len; i++) {
                //write file to Vvalues.txt for all u
                String writeMe = Double.toString(sol[i]);
                writer.write(writeMe);
                writer.write("\n");

                //write to file all x for i
                String xVal = Double.toString(i);
                xwriter.write(xVal);
                xwriter.write("\n");
            }

            writer.close();
            xwriter.close();

            //TODO: RETURN values of u
            //return u;
        }
        catch(IOException io)
        {
            System.out.println("IO EXCEPTION CAUGHT \n");
            io.printStackTrace();
        }
        return u;
    }
     

**Last Modified:** 11/February/2020
