package file_reader;

import java.util.ArrayList;
import java.util.Iterator;

public class CustomLinkedList implements Iterable<Double> {
	private ArrayList<Node> values;
	private ArrayList<Integer> freeSpaces;
	private int headIndex, endIndex;
	private int freeSpacesHighestIndex;
	
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
		values = new ArrayList<Node>();
		freeSpaces = new ArrayList<Integer>();
		headIndex = endIndex = -1;
		freeSpacesHighestIndex = -1;
	}
	
	public void add(Double num) {
		if (this.isEmpty()) {
			values.add(new Node(num, -1, -1));
			headIndex = endIndex = 0;
			return;
		}
		
		if (freeSpacesIsEmpty()) {
			values.add(new Node(num, endIndex, -1));
			endIndex = values.size()-1;
		}
		else {
			int index = getNextFreeSpace();
			values.get(index).set(num, endIndex, -1);
			endIndex = index;
		}
	}
	
	public double get(int index) {
		return values.get(index).value;
	}
	
	public boolean hasNext(int index) {
		if (index < 0) {
			return !isEmpty();
		}
		return values.get(index).next != -1;
	}
	
	private boolean freeSpacesIsEmpty() {
		return freeSpacesHighestIndex == -1;
	}
	
	private int getNextFreeSpace() {
		freeSpacesHighestIndex--;
		return freeSpaces.get(freeSpacesHighestIndex+1);
	}
	
	private void clearAll() {
		headIndex = endIndex = -1;
		values.clear();
		freeSpaces.clear();
	}
	
	public boolean isEmpty() {
		return headIndex == -1;
	}

	@Override
	public CustomIterator iterator() {
		return new CustomIterator(this);
	}
}