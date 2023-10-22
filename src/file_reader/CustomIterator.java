package file_reader;

import java.util.Iterator;

public class CustomIterator implements Iterator<Double> {
	private CustomLinkedList list;
	private int currentIndex;
	
	public CustomIterator(CustomLinkedList list) {
		currentIndex = -1;
		this.list = list;
	}

	@Override
	public boolean hasNext() {
		return list.hasNext(currentIndex);
	}

	@Override
	public Double next() {
		currentIndex = list.getNextIndex(currentIndex);
		return list.get(currentIndex);
	}

}