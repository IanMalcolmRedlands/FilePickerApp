package file_reader;

import java.util.ArrayList;

public class CustomLinkedList implements Iterable<Double> {
	private ArrayList<Node> nodes;
	private ArrayList<Integer> freeSpaces; //stores free spaces in array
	private int headIndex, endIndex;
	private int freeSpacesHighestIndex;
	
	/** Internal nodes to store value and previous and next indices in nodes array **/
	private class Node {
		public int last;
		public int next;
		public double value;
		
		public Node(double value, int last, int next) {
			this.value = value;
			this.last = last;
			this.next = next;
		}
		
		public void set(double value, int last, int next) {
			this.value = value;
			this.last = last;
			this.next = next;
		}
	}
	
	public CustomLinkedList() {
		nodes = new ArrayList<Node>();
		freeSpaces = new ArrayList<Integer>();
		headIndex = endIndex = -1;
		freeSpacesHighestIndex = -1;
	}
	
	public void add(double num) {
		if (this.isEmpty()) {
			nodes.add(new Node(num, -1, -1));
			headIndex = endIndex = 0;
			return;
		}
		
		if (freeSpacesIsEmpty()) {
			nodes.add(new Node(num, endIndex, -1));
			nodes.get(endIndex).next = endIndex = nodes.size()-1;
		}
		else {
			int index = getNextFreeSpace();
			nodes.get(index).set(num, endIndex, -1);
			nodes.get(endIndex).next = endIndex = index;
		}
	}
	
	public double get(int index) {
		return nodes.get(index).value;
	}
	
	public boolean hasNext(int index) {
		if (index < 0) {
			return !isEmpty();
		}
		return nodes.get(index).next != -1;
	}
	
	public int getNextIndex(int index) {
		if (index < 0) {
			return headIndex;
		}
		return nodes.get(index).next;
	}
	
	private boolean freeSpacesIsEmpty() {
		return freeSpacesHighestIndex == -1;
	}
	
	private int getNextFreeSpace() {
		freeSpacesHighestIndex--;
		return freeSpaces.get(freeSpacesHighestIndex+1);
	}
	
	public boolean isEmpty() {
		return headIndex == -1;
	}

	@Override
	public CustomIterator iterator() {
		return new CustomIterator(this);
	}
}