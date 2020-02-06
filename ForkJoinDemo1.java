//Cameron Gonzalez
//CSCI 3950 - Dr. Phelps
//Finished on: 2/4/2020

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinDemo1
{
    public static void main(String[] args)
    {
        // create an array with twelve elements
        int[] numbers = new int[12];

        // set the first 10 elements to 100
        initializeArray task = new initializeArray(numbers, 0, 9, 100);

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        // prints the resulting array
        System.out.print("Array of numbers: [");
        for (int i = 0; i < numbers.length - 1; i++)
        {
            System.out.print(numbers[i]+", ");
        }
        System.out.print(numbers[numbers.length - 1]);
        System.out.print("]");
    }
}

class initializeArray extends RecursiveAction
{
    final int[] array;
    final int startIndex;
    final int endIndex;
    final int value;

    /**
     * initializeArray assigns the desired indexes to be assigned one particular value.
     * Using recursion, the constructor will assign the startIndex's value to the param
     * value unless the endIndex is lower than the startIndex (which makes sure there
     * is no IndexOutOfBoundsException thrown).
     *
     * @param srcArray holds the address for array value to be assigned to.
     * @param startIndex holds the desired start index for assignment.
     * @param endIndex holds the desired end index for assignment.
     * @param value is the value that the desired indexes will be assigned.
     */
    initializeArray(int[] srcArray, int startIndex, int endIndex, int value)
    {
        this.array = srcArray;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.value = value;
    }

    /**
     * Uses fork and join methods in order to recursively assign the indexes
     */
    protected void compute()
    {
        int midIndex = (startIndex + endIndex)/2;

        if(startIndex == endIndex)
        {
            array[startIndex] = value;
            return;
        }

        initializeArray task1 = new initializeArray(array, startIndex, midIndex, value);
        initializeArray task2 = new initializeArray(array, midIndex + 1, endIndex, value);
        task1.fork();
        task2.fork();
        task1.join();
        task2.join();
    }
}
