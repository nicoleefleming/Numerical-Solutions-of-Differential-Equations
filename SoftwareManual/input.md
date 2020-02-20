# Math 5620 Finite Difference Methods for Ordinary and Partial Differential Equations


**Routine Name:**           input

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

**Description/Purpose:** This routine will read data from a file, and convert the data into a double array from the file format.

**Input:** There is one input, the file, where the routine will take the content from.

**Output:** This routine returns a one dimensional array of doubles that were given in the file of data. 

**Usage/Example:**

The routine has one argument needed to return the values in the file. The following is an example of how it was used for Tasksheet 4.4. 

        double[] Kfile;
        Kfile = triOps.input(file);
        //test and verify input is working
        for (int i = 0; i < Kfile.length; i++)
        {
            System.out.println(Kfile[i] + ", ");
        }

Output from the lines above are shown below with the data provided for Tasksheet 4.4. This was what printed to the console. The values were the same as those given.

      2.867333,  4.569805,  3.316993,  8.092610,  8.140566,  0.143155,  6.342265,  5.241447, 6.605599,  0.933701,  3.324111,  8.192863,  7.079724,  7.245939,  2.395458,  1.355006, 2.288142,  5.042498,  7.306108,  4.821304,  7.771159,  7.423749,  9.122419,  8.120050, 3.580566 , 3.393176 , 8.418107 , 2.601973 , 0.217588 , 3.894094 , 4.362981,  5.842914, 0.907254,  4.713689,  7.163621,  8.341334,  7.430906,  3.820236,  0.691511,  8.002433, 0.649783,  9.228502,  6.750084,  8.722010 , 9.548238,  4.976954,  1.182421,  6.113974, 3.805117,  8.248314,  8.706021,  1.765543,  8.366169,  6.572338,  0.780077,  7.053698, 9.839133,  7.316398,  1.064157,  6.615321,  2.466196,  8.995519,  7.014925,  9.431717, 5.246028,  9.218961,  3.189759,  8.365877,  1.979470,  9.335279,  6.782625,  5.677813, 7.699140,  4.541973,  6.108508,  5.039710,  6.613854,  1.989440,  6.377985 , 9.878402, 1.553754,  0.131856,  6.063194,  9.487799,  1.741344,  6.059971 , 7.150494,  4.978408, 8.837417,  9.392483,  4.803539,  9.965004,  0.118921,  5.103728,  9.323259,  2.436715, 7.738321,  0.125891,  1.088443,  4.506494,  0.890854,  4.083744,  0.392129,  3.998879, 7.077300,  5.999135,  0.354940,  8.906408, 7.295522,  4.941008,  5.796811,  4.225764, 3.623979, 6.294709,  2.683174,  9.874883,  9.374789,  1.681627,  4.830779,  6.591775, 6.992642,  2.776946,  0.961683,  5.724664,  8.758983,  9.249325,  2.331766,  8.641368
       
       

**Implementation/Code:** The following is the code for AssignLambda

    public static double[] input(File file)
    {
        String expression = "";
        try {
            //created a new File object passed in

            //create a new Scanner
            Scanner read = new Scanner(file);

            //scans file and puts into string to convert.
            expression = read.nextLine();
        }
        catch (FileNotFoundException error) {
            //will print ONLY if the file is not found. Will print in RED
            System.err.println("Did not find file");
        }

        //put read in string into string array and split
        String[] toInt = expression.split(" ");

        // Create new array to store integers from conversion of string array
        double[] ints = new double[toInt.length];

        //Debugging to make sure it is correct
        //System.out.println(ints);

        //convert string to array of integers
        for(int i = 0;i < toInt.length;i++)
        {
            try
            {
                ints[i] = Double.parseDouble(toInt[i]);
                ints[i] = ints[i];
            }
            catch (NumberFormatException nfe)
            {
                ints[i] = Double.parseDouble("0.0");
            }
        }
        double[] sendME = new double[toInt.length];
        sendME = removeNumber(ints, 0.0);

        //return array of doubles
        return sendME;
    }
    }

**Last Modified:** 20/February/2020
