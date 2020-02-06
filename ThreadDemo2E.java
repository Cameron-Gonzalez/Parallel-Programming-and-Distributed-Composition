//Demonstrates the sleep method
public class ThreadDemo2E
{
    public static void main(String[] args)
    {
        System.out.println(Thread.currentThread().getName() + " BEGIN");

        //Creating 3 new threads
        Thread t1 = new Thread(new Greeting5());
        Thread t2 = new Thread(new Greeting5());
        Thread t3 = new Thread(new Greeting5());

        //Starts those threads
        t1.start();
        t2.start();
        t3.start();
        try
        {
            if(t1.isAlive())
            {
                t1.join();
            }
            if(t2.isAlive())
            {
                t2.join();
            }
            if(t3.isAlive())
            {
                t3.join();
            }
        }
        catch(InterruptedException ex)
        {
        }

        System.out.println(Thread.currentThread().getName() + " FINISHED");

    }
}

class Greeting5 implements Runnable
{
    public void run()
    {
        for (int i = 1; i <= 5; i++)
        {
            System.out.println(Thread.currentThread().getName() + " says Hello" + i);
            try
            {
                Thread.sleep(4000);
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }

    };
}