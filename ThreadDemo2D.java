//Exercising the join() method in the Thread class. Executes the main "FINISHED" after all other threads finish
public class ThreadDemo2D
{
    public static void main(String[] args)
    {
        System.out.println(Thread.currentThread().getName() + " BEGIN");

        //Creating 3 new threads
        Thread t1 = new Thread(new Greeting());
        Thread t2 = new Thread(new Greeting());
        Thread t3 = new Thread(new Greeting());

        //Starts those threads
        t1.start();
        t2.start();
        t3.start();
        try
        {
            if(t1.isAlive())
            {
                t1.join(500);
            }
            if(t2.isAlive())
            {
                t2.join(1000);
            }
            if(t3.isAlive())
            {
                t3.join(2000);
            }
        }
        catch(InterruptedException ex)
        {
        }

        System.out.println(Thread.currentThread().getName() + " FINISHED");

    }
}