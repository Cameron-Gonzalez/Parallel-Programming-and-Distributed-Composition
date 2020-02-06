
public class ThreadDemo2C
{
    public static void main(String args[])
    {
        for (int i = 1; i < 4; i++)
        {
            new Thread( new Greeting()).start();
        }

        for(int i = 1; i <= 4; i++)
        {
            System.out.println(Thread.currentThread().getName() + " Hello" + i);
        }
    }
}

class Greeting3 implements Runnable
{
    public void run()
    {
        for (int i = 1; i <= 5; i++)
        {
            System.out.println(Thread.currentThread().getName() + " says Hello" + i);
        }
    };
}