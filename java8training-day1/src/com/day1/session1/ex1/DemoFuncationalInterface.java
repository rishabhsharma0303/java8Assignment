package com.day1.session1.ex1;

import java.security.KeyStore.Entry;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import javax.security.auth.kerberos.KerberosKey;

public class DemoFuncationalInterface {

	public static void main(String[] args) {

		List<String> list = Arrays.asList("foo", "barrr", "jar", "car");

		list.forEach(b -> System.out.println(b));

		Map<String, Integer> map = new HashMap<>();
		map.put("raj", 102);
		map.put("ekta", 104);

		List<String> tempList = list.stream().sorted((a, b) -> Integer.compare(a.length(), b.length()))
				.collect(Collectors.toList());
		System.out.println(tempList);

		map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(b -> System.out.println(b.getValue()));

		// What is functional interface?

		/*
		 * What is functional interface?
		 * 
		 * @FunctionalInterface Interface and diamond problem and solution Interface
		 * evolution: default method , static method examples
		 */
	}
}
