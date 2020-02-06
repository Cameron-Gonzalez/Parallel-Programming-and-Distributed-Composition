/**
 * Task Parallelism:
 *
 * Counts the Birthdays in each month using 4 different processes.
 * This Birthday counter uses Task Parallelism to take a section of the text file
 * and count the number of birthdays in each month.
 * By: Cameron Gonzalez
 */

import java.io.*;
import java.util.*;

public class BirthdayCounter2
{
    public static void main(String[] args)
    {
        Counter1 counter1 = new Counter1();
        Counter2 counter2 = new Counter2();
        Counter3 counter3 = new Counter3();
        Counter4 counter4 = new Counter4();

        long lStartTime = new Date().getTime();

        //create 4 different threads for counting (each takes 3 months)
        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);
        Thread t3 = new Thread(counter3);
        Thread t4 = new Thread(counter4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try
        {
            if(t1.isAlive())
                t1.join();
            else if(t2.isAlive())
                t2.join();
            else if(t3.isAlive())
                t3.join();
            else if(t4.isAlive())
                t4.join();
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }

        counter1.print();
        counter2.print();
        counter3.print();
        counter4.print();

        //Record End Time
        long lEndTime = new Date().getTime();

        //Report Elapsed Time
        long difference = lEndTime - lStartTime;

        System.out.println("\nTime Elapsed: "+difference+" milliseconds.");
    }
}

//print out the time as well
class Counter1 implements Runnable
{
    //counters to hold the number of birthdays in each month
    int jan;
    int feb;
    int mar;

    /**
     * prints the number of birthdays in each month Jan-Mar
     */
    public void print()
    {
        System.out.printf("January: %d\nFebruary: %d\nMarch: %d\n", jan, feb, mar);
    }

    /**
     * implements the Runnable interface and is executed at start of thread
     * counts the number of birthdays
     */
    public void run()
    {
        File infile = new File("birthdays.txt");
        try
        {
            Scanner scanner = new Scanner(infile);
            //counts the next 25 values
            synchronized(this)
            {
                while(scanner.hasNext())
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
                }
            }
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}

class Counter2 implements Runnable
{
    //counters to hold the number of birthdays in each month
    int apr;
    int may;
    int jun;

    /**
     * prints the number of birthdays in each month Jan-Mar
     */
    public void print()
    {
        System.out.printf("April: %d\nMay: %d\nJune: %d\n", apr, may, jun);
    }

    /**
     * implements the Runnable interface and is executed at start of thread
     * counts the number of birthdays
     */
    public void run()
    {
        File infile = new File("birthdays.txt");
        try
        {
            Scanner scanner = new Scanner(infile);
            //counts the next 25 values
            synchronized(this)
            {
                while(scanner.hasNext())
                {
                    String date = scanner.nextLine();
                    String[] tokens = date.split("/");
                    int month = Integer.parseInt(tokens[0]);
                    if(month == 4)
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
                }
            }
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}

class Counter3 implements Runnable
{
    //counters to hold the number of birthdays in each month
    int jul;
    int aug;
    int sep;

    /**
     * prints the number of birthdays in each month Jan-Mar
     */
    public void print()
    {
        System.out.printf("July: %d\nAugust: %d\nSeptember: %d\n", jul, aug, sep);
    }

    /**
     * implements the Runnable interface and is executed at start of thread
     * counts the number of birthdays
     */
    public void run()
    {
        File infile = new File("birthdays.txt");
        try
        {
            Scanner scanner = new Scanner(infile);
            //counts the next 25 values
            synchronized(this)
            {
                while(scanner.hasNext())
                {
                    String date = scanner.nextLine();
                    String[] tokens = date.split("/");
                    int month = Integer.parseInt(tokens[0]);
                    if(month == 7)
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
                }
            }
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}

class Counter4 implements Runnable
{
    //counters to hold the number of birthdays in each month
    int oct;
    int nov;
    int dec;

    /**
     * prints the number of birthdays in each month Jan-Mar
     */
    public void print()
    {
        System.out.printf("October: %d\nNovember: %d\nDecember: %d\n", oct, nov, dec);
    }

    /**
     * implements the Runnable interface and is executed at start of thread
     * counts the number of birthdays
     */
    public void run()
    {
        File infile = new File("birthdays.txt");
        try
        {
            Scanner scanner = new Scanner(infile);
            //counts the next 25 values
            synchronized(this)
            {
                while(scanner.hasNext())
                {
                    String date = scanner.nextLine();
                    String[] tokens = date.split("/");
                    int month = Integer.parseInt(tokens[0]);
                    if(month == 10)
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
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}