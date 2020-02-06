/**
 * Data Parallelism:
 *
 * Counts the Birthdays in each month using 3 different processes.
 * This Birthday counter uses Data Parallelism to take a section of the text file
 * and count the number of birthdays in each month.
 * By: Cameron Gonzalez
 */

import java.io.*;
import java.util.*;

public class BirthdayCounter1
{
    public static void main(String[] args) throws FileNotFoundException {
        Counter counter = new Counter();

        //create 3 different threads for counting
        Thread t1 = new Thread(counter);
        Thread t2 = new Thread(counter);
        Thread t3 = new Thread(counter);

        //Gets the time at the start of threads
        long lStartTime = new Date().getTime();

        t1.start();
        t2.start();
        t3.start();
        try
        {
            if(t1.isAlive())
                t1.join();
            else if(t2.isAlive())
                t2.join();
             if(t3.isAlive())
                t3.join();
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }

        //prints all recorded birthday tallies
        counter.print();

        //Record End Time
        long lEndTime = new Date().getTime();
        //Report Elapsed Time
        long difference = lEndTime - lStartTime;
        System.out.println("\nTime Elapsed: "+difference+" milliseconds.");
    }
}

/**
 * Counter is a task that counts the number of birthday each month using the Runnable interface.
 */
class Counter implements Runnable
{
    //counters to hold the number of birthdays in each month
    int jan;
    int feb;
    int mar;
    int apr;
    int may;
    int jun;
    int jul;
    int aug;
    int sep;
    int oct;
    int nov;
    int dec;
    File infile = new File("birthdays.txt");
    Scanner scanner = new Scanner(infile);

    Counter() throws FileNotFoundException
    {
    }

    /**
     * prints the number of birthdays in each month
     */
    public void print()
    {
        System.out.printf("January: %d\nFebruary: %d\nMarch: %d\nApril: %d\nMay: %d\nJune: %d\nJuly: %d\nAugust: %d\n" +
                "September: %d\nOctober: %d\nNovember: %d\nDecember: %d\n", jan, feb, mar, apr, may, jun, jul, aug, sep,
                oct, nov, dec);
    }

    /**
     * implements the Runnable interface and is executed at start of thread
     * counts the number of birthdays
     */
    public void run()
    {
        synchronized(this)
        {
            while(scanner.hasNext())
            {
                //counts the next 25 values
                for (int i = 0; i < 25; i++)
                {
                    String date = scanner.nextLine();
                    String[] tokens = date.split("/");
                    int month = Integer.parseInt(tokens[0]);
                    if(month == 1)
                    {
                        jan++;
                    }
                    else if(month == 2)
                    {
                        feb++;
                    }
                    else if(month == 3)
                    {
                        mar++;
                    }
                    else if(month == 4)
                    {
                        apr++;
                    }
                    else if(month == 5)
                    {
                        may++;
                    }
                    else if(month == 6)
                    {
                        jun++;
                    }
                    else if(month == 7)
                    {
                        jul++;
                    }
                    else if(month == 8)
                    {
                        aug++;
                    }
                    else if(month == 9)
                    {
                        sep++;
                    }
                    else if(month == 10)
                    {
                        oct++;
                    }
                    else if(month == 11)
                    {
                        nov++;
                    }
                    else if(month == 12)
                    {
                        dec++;
                    }
                }
            }
        }
    }
}
