package ru.devstend.practice.tree;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentCounter {
  private volatile int count = 0; // вот тут интересно, не лишний ли модификатор volatile?
  private Lock lock;

  public ConcurrentCounter(Lock lock) {
    this.lock = lock;
  }

  public void increment() {
    lock.lock();
    try {
      ++count;
      System.out.println(Thread.currentThread().getName() + " count=" + count );
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    Lock lock = new ReentrantLock();
    ConcurrentCounter concurrentCounter = new ConcurrentCounter(lock);

    for (int i = 0; i < 10; i++) {
      Thread thread = new Thread(() -> {
        int iter = 10;
        while (iter > 0) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          concurrentCounter.increment();
          --iter;
        }
      } );
      thread.setName("Thread_" + i);
      thread.start();
    }
  }

}
