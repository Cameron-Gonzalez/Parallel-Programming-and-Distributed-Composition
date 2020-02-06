//Cameron Gonzalez
//CSCI 3950 - Dr. Phelps
//Finished on: 2/5/2020

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinDemo3
{
    public static void main(String[] args)
    {
        //the word to be evaluated
        String word = "whiteleaf";

        //initialize the task and pool
        elfish task = new elfish(word.toCharArray(), 0, word.length() - 1);
        ForkJoinPool pool = new ForkJoinPool();

        //invoke the task
        pool.invoke(task);

        //check to see if all checks were passed
        if(elfish.checkE && elfish.checkL && elfish.checkF)
        {
            System.out.println("The word " + word + " is elfish.");
        }
        else
        {
            System.out.println("The word " + word + " is not elfish.");
        }
    }
}

class elfish extends RecursiveAction
{
    char[] charArray;
    int startIndex;
    int endIndex;

    //checks for each letter
    static boolean checkE = false;
    static boolean checkL = false;
    static boolean checkF = false;

    /**
     * elfish will initialize the charArray, startIndex, and endIndex, for the compute() method
     * to evaluate whether the word has an 'e', 'l', and 'f'. If it contains all three, it is
     * considered elfish, and will intitialize each static check to true.
     *
     * @param srcArray holds the address for the srcArray to be evaluated.
     * @param startIndex holds the index for the task to start evaluation.
     * @param endIndex holds the index for the task to end evaluation.
     */
    public elfish(char[] srcArray, int startIndex, int endIndex)
    {
        charArray = srcArray;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    protected void compute()
    {
        int midIndex = (startIndex + endIndex) / 2;

        //base case
        if(startIndex == endIndex)
        {
            if(charArray[startIndex] == 'e')
            {
                checkE = true;
            }
            else if(charArray[startIndex] == 'l')
            {
                checkL = true;
            }
            else if(charArray[startIndex] == 'f')
            {
                checkF = true;
            }
            return;
        }

        //working case
        else
        {
            elfish e1 = new elfish(charArray, startIndex, midIndex);
            elfish e2 = new elfish(charArray, midIndex + 1, endIndex);
            e1.fork();
            e2.fork();
            e1.join();
            e2.join();
        }

    }
}
