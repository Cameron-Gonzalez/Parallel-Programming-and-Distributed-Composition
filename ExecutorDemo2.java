import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorDemo2
{
    public static void main(String[] args)
    {
        ExecutorService threads = Executors.newCachedThreadPool();
        Future<Integer> future1 = threads.submit(new MyTaskOne(10, 10, 10, 10, 10));
        Future<Integer> future2 = threads.submit(new MyTaskTwo(1, 1, 1));

        int value = 0;
        try
        {
            value = future1.get() + future2.get(); //This is a blocking call - returning null if task has finished
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //main thread prints No!
        System.out.println(Thread.currentThread().getName() + " Result: " + value);

        //application will keep running unless you shutdown the ExecutorService
        threads.shutdown();
    }
    private static class MyTaskOne implements Callable<Integer>
    {
        private int sum1 = 0;
        public MyTaskOne(int ...a)
        {
            int sum = 0;
            for (int i : a)
            {
                sum1 += i;
            }
        }

        public Integer call()
        {
            return sum1;
        };
    }
    private static class MyTaskTwo implements Callable<Integer>
    {
        private int sum2 = 0;
        public MyTaskTwo(int ...b)
        {
            int sum = 0;
            for (int i : b)
            {
                sum2 += i;
            }
        }
        public Integer call()
        {

            return sum2;
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
