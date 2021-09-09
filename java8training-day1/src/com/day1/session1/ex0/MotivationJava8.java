package com.day1.session1.ex0;

import java.util.stream.LongStream;

class Prime{
	public static boolean isPrime(Long n){
		boolean isPrimeNumber=true;
		for(int i=2;i<n;i++){
			if(n%i==0)
				isPrimeNumber=false;
		}
		return isPrimeNumber;
	}
}
public class MotivationJava8 {
	
	public static void main(String[] args) {
		
		
		long prime = LongStream.rangeClosed(1, 100_00)
					.parallel()
					.filter((n) -> Prime.isPrime(n))
					.count();
		System.out.println(prime);
		
		
		/*
		 * System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
		or

		java -Djava.util.concurrent.ForkJoinPool.common.parallelism=8 DemoTakingAdvOfParallelProcessingwithOutPhd

		 */
	}

}
