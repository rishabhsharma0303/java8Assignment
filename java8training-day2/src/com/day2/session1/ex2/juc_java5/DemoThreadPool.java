package com.day2.session1.ex2.juc_java5;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

//Thread Pool: pool of threads ie thread lifecycle whould be managed by jvm

class Factorial implements Callable<Long> {
	private long n;
	
	public Factorial(long n) {
		this.n = n;
	}
	@Override
	public Long call() throws Exception {
		Thread.sleep(1000);
		System.out.println(Thread.currentThread().getName());
		if(n <= 0) {
			throw new Exception("wrong input processing failed");
		}
		return LongStream.rangeClosed(1, n).reduce(1, (long a, long b) -> a * b);
	}
}
public class DemoThreadPool {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Long> task = new Factorial(5);
		// callable only works with thread Pool
		ExecutorService eService = Executors.newSingleThreadExecutor();
		System.out.println("It is printing before");
		//non blocking code in java
		Future<Long> future = eService.submit(task);
		
		System.out.println(future.get());//Blocking code
		System.out.println("It is printing");
		eService.shutdown();
	}
}
