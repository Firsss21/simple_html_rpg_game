package app.utils;

import java.util.concurrent.*;

public class CustomExecutor {
    private final ScheduledExecutorService executor;
    private final Semaphore semaphore;

    public CustomExecutor(int corePoolSize, int taskLimit) {
        RejectedExecutionHandler handler = (r, executor)->System.out.println("Task was rejected by thread pool.");
        executor = new ScheduledThreadPoolExecutor(corePoolSize, handler);
        semaphore = new Semaphore(taskLimit);
    }


    public void execute(Runnable command)
    {
        try
        {
            exec(command);
        }
        catch(InterruptedException e)
        {
            System.out.println("Interrupted before task executed.");
        }
    }

    private synchronized void exec(final Runnable command) throws InterruptedException
    {
        semaphore.acquire();
        try
        {
            executor.execute(()->
            {
                try { command.run(); }
                finally { semaphore.release(); }
            });
        }
        catch(RejectedExecutionException e)
        {
            semaphore.release();
            throw e;
        }
    }

    public void schedule(Runnable command, long delay, TimeUnit unit)
    {
        try
        {
            schd(command, delay, unit);
        }
        catch(InterruptedException e)
        {
            System.out.println("Interrupted before task scheduled.");
        }
    }

    private synchronized void schd(final Runnable command, long delay, TimeUnit unit) throws InterruptedException
    {
        semaphore.acquire();
        try
        {
            executor.schedule(()->
            {
                try { command.run(); }
                finally { semaphore.release(); }
            }, delay, unit);
        }
        catch(RejectedExecutionException e)
        {
            semaphore.release();
            throw e;
        }
    }
}
