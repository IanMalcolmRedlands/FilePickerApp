package tests;

import file_reader.CustomLinkedList;

public class ListTester {
	public static void main(String args[]) {
		CustomLinkedList list = new CustomLinkedList();
		
		list.add(123.0);
		list.add(12.5);
		list.add(3);
		list.add(122.5);
		list.add(8.9);
		
		for (double num : list) {
			System.out.println(num);
		}
	}
}