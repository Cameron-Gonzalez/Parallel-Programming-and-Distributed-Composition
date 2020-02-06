//Cameron Gonzalez
//CSCI 3950 - Dr. Phelps
//Finished on: 2/4/2020

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo2
{
    public static void main(String[] args)
    {
        //declares an array of 10,000 integers
        int[] numbers = new int[10000];

        //sets all integers in the array to 1
        initializeArray task = new initializeArray(numbers, 0, numbers.length - 1, 1);

        //calculate the sum of indexes 0 - 19
        SumTask task2 = new SumTask(numbers, 0, 19, 10);

        //create the ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        //invoke the task that initializes the array numbers
        pool.invoke(task);

        //invoke the task2 and obtain the result of the SumTask (in this instance, should be 19)
        int result = pool.invoke(task2);

        //print the result
        System.out.println("Result: " + result);
    }
}

class SumTask extends RecursiveTask<Integer>
{
    final int[] numbers;
    int startIndex;
    int endIndex;
    int threshold;

    /**
     * Implements a recursive algorithm to find the sum of a subset of an  array of numbers.
     *
     * @param srcArray holds the array value for the array to be assigned to.
     * @param startIndex holds the desired index to start assignment.
     * @param endIndex holds the desired index to end assignment.
     * @param threshold holds the size of the array to compute sequentially.
     */
    public SumTask(int[] srcArray, int startIndex, int endIndex, int threshold)
    {
        this.numbers = srcArray;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threshold = threshold;
    }

    protected Integer compute()
    {
        //base case
        if((endIndex - startIndex) <= threshold)
        {
            int sum = 0;
            for(int i = startIndex; i < endIndex; i++)
            {
                sum += numbers[i];
            }
            return sum;
        }
        //working case
        else
        {
            int midIndex = (startIndex + endIndex)/2;
            SumTask task1 = new SumTask(numbers, startIndex, midIndex, threshold);
            SumTask task2 = new SumTask(numbers, midIndex, endIndex, threshold);
            //create recursive sub-tasks using fork()
            task1.fork();
            task2.fork();
            //waits for all recursive sub-tasks to finish using join()
            int sum = task1.join() + task2.join();
            return sum;
        }

    }
}
