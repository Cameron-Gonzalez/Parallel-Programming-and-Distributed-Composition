//ThreadDemo1
public class ThreadDemo1
{
    public static void main(String args[])
    {
        // Create a Threads. Pass Runnable object to execute
        Thread t = new Thread(new Greeting());

        //Start Thread
        t.start();
    }
}

class Greeting implements Runnable
{
    public void run()
    {
        for (int i = 1; i <= 5; i++)
        {
            System.out.println(Thread.currentThread().getName() + " says Hello" + i);
        }
    };
}
