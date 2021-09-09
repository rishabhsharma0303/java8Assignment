package com.day2.session1.ex2.juc_java5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


class Producer implements Runnable {
	private BlockingQueue<Integer> que;
	
	public Producer(BlockingQueue<Integer> que) {
		this.que = que;
	}

	@Override
	public void run() {
		int i = 0;
		while(true) {
			try {
				++i;
				System.out.println("produce: " + i);
				que.put(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	private BlockingQueue<Integer> que;
	
	public Consumer(BlockingQueue<Integer> que) {
		this.que = que;
	}

	@Override
	public void run() {
		while(true) {
			try {
				System.out.println("get: " + que.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


public class DemoBlockingQueue {
	public static void main(String[] args) {
		// create a blocking queue of size 10
		BlockingQueue<Integer> que = new ArrayBlockingQueue<Integer>(1);
		// create a class Producer
		Producer producer = new Producer(que);
		// create a consumer
		Consumer consumer = new Consumer(que);
		// start both
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();
	}
}
