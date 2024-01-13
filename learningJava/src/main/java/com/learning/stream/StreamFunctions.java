package com.learning.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamFunctions {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8 };
		// Lambda expressions
		// Convert array into streams
		IntStream intStream = Arrays.stream(nums);
		int sum = intStream.sum();
		System.out.println("Sum : " + sum);

		// Once stream is processed, can't reuse.
		// intStream.count();

		// Print nums range
		IntStream.range(0, 10).forEach(x -> System.out.print(x + " "));

		System.out.println(System.lineSeparator() + "Greater than 4");
		// Filter nums range: find values greater than 5
		IntStream.range(0, 10).filter(x -> x > 4).forEach(x -> System.out.print(x + " "));

		String[] names = { "Adam", "Daniel", "Martha", "Kevin", "Ben", "Joe", "Brad", "Susan" };

		System.out.println(System.lineSeparator() + "Convert Arrays to stream by :: Arrays.stream()");
		// Convert String arr to stream
		Arrays.stream(names).forEach(x -> System.out.print(x + " "));

		System.out.println(System.lineSeparator() + "Convert Arrays to stream by :: Stream.of()");
		// Convert String arr to stream
		Stream.of(names).forEach(x -> System.out.print(x + " "));

		System.out.println(System.lineSeparator() + "Convert Arrays to stream and sort by :: Stream.of().sorted()");
		Stream.of(names).sorted().forEach(x -> System.out.print(x + " "));

		System.out.println(System.lineSeparator()
				+ "Convert Arrays to stream and sort in reverse by :: Stream.of().sorted(Comparator.reverseOrder())");
		Stream.of(names).sorted(Comparator.reverseOrder()).forEach(x -> System.out.print(x + " "));

		System.out.println(System.lineSeparator()
				+ "Convert Arrays to stream and filter (Print all names starts with A):: Stream.of(names).filter(x -> x.startsWith(\"A\"))");
		Stream.of(names).filter(x -> x.startsWith("A")).forEach(x -> System.out.print(x + " "));

		System.out.println(System.lineSeparator() + "Print length of names in sorted way");
		Stream.of(names).sorted(Comparator.comparing(String::length)).map(String::length)
				.forEach(x -> System.out.print(x + " "));
		/*
		 * map() method will create individual arrays of stream of splitted
		 * characters and after creating an array we need to merge all different
		 * arrays into single one to merge so for that we are using flatMap()
		 */
		System.out.println(System.lineSeparator() + "Print distinct character");
		Stream.of(names).map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList())
				.forEach(System.out::print);
	}
}
