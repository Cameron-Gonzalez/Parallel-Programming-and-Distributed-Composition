import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibDemo
{
    public static void main(String[] args)
    {
        Fibonacci task = new Fibonacci(6);
        ForkJoinPool pool = new ForkJoinPool();
        int result = pool.invoke(task);
        System.out.println(result);
    }
}

class Fibonacci extends RecursiveTask<Integer>
{
    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    protected Integer compute() {
        System.out.println(Thread.currentThread().getName() + "computing Fib(" + n + ")");
        if (n <= 1)
            return n;

        Fibonacci f1 = new Fibonacci(n - 1);
        Fibonacci f2 = new Fibonacci(n - 2);

        f1.fork();
        f2.fork();
        int result = f1.join() + f2.join();
        return result;
    }
}