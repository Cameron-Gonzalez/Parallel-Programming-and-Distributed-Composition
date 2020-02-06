public class ThreadDemo3
{
   public static void main(String args[]) {
    //Delcare a subtask object
      Greeting hello = new Greeting();
      
       // Create 3 different Threads. 
      //  Pass Runnable object to execute        
      Thread t0 = new Thread(hello);
      Thread t1 = new Thread(hello);
      Thread t2 = new Thread(hello);               
       //Start the Threads and call the  Runnable's run method.
      t0.start();
      t1.start();
      t2.start();
   }
}
class Greeting implements Runnable{
   int count=1;
   public void run()  {
      String name = Thread.currentThread().getName();
      for (int i=1; i<= 5; i++)  
      {
         // Only added to allow threads to opportunity interleave
         for (int j=1; j <= 2; j++)
         {
            System.out.println(name + ".. " + j);
         }
         // this codes locks access to count while it is being modified.
         synchronized(this)
         {
            count++;
            System.out.println(Thread.currentThread().getName() +
                    " says Hello" + count);
         }
      }
   };
}
/*  What is the output?

*/