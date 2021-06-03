package ru.devstend.practice.tree;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PingPong {

  private final AtomicBoolean flag = new AtomicBoolean(true);
  private final AtomicInteger count = new AtomicInteger(20);

  public synchronized void ping() {
    while (count.get() > 0) {

      while (!flag.get()) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      System.out.println(Thread.currentThread().getName() + " Ping");
      count.decrementAndGet();
      flag.set(false);

      notifyAll();
    }
  }

  public synchronized void pong() {
    while (count.get() > 0) {

      while (flag.get()) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      System.out.println(Thread.currentThread().getName() + " Pong");
      count.decrementAndGet();
      flag.set(true);

      notifyAll();
    }
  }

  public static void main(String[] args) {
    PingPong pingPong = new PingPong();

    Thread thread = new Thread(pingPong::ping);
    thread.setName("Thread_one");
    thread.start();

    Thread thread1 = new Thread(pingPong::pong);
    thread1.setName("Thread_two");
    thread1.start();

  }
}
