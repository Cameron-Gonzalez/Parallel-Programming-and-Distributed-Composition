import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo
{
    public static void main(String[] args)
    {
        ExecutorService threads = Executors.newCachedThreadPool();
        threads.execute(new MyTaskOne());
        threads.execute(new MyTaskTwo());
        threads.execute(new MyTaskThree());

        for (int i = 1; i <= 5; i++)
        {
            System.out.println(Thread.currentThread().getName() + " says No! " + i);
        }

        //application will keep running unless you shutdown the ExecutorService
        threads.shutdown();
    }
    private static class MyTaskOne implements Runnable
    {
        public void run()
        {
            for (int i = 1; i <= 5; i++)
            {
                System.out.println(Thread.currentThread().getName() + " says Hello  " + i);
            }
        }
    }
    private static class MyTaskTwo implements Runnable
    {
        public void run()
        {
            for (int i = 1; i <= 5; i++)
            {
                System.out.println(Thread.currentThread().getName() + " says Bye  " + i);
            }
        }
    }
    private static class MyTaskThree implements Runnable
    {
        public void run()
        {
            for (int i = 1; i <= 5; i++)
            {
                System.out.println(Thread.currentThread().getName() + " says Yes  " + i);
            }
        }
    }
}
