package de.frdnspnzr.tapeDriveEmulator;

import java.util.ArrayList;
import java.util.List;

public class Tape <E> {
	
	private int length;
	private List<E> values;
	
	public Tape(int length) {
		this.length = length;
		this.values = new ArrayList<E>(length);
		for (int i = 0; i < length; i++) {
			this.values.add(null);
		}
	}
	
	E getValue(int position) throws IndexOutOfBoundsException {
		if (position >= this.length)
			throw new IndexOutOfBoundsException();
		else
			return this.values.get(position);
	}
	
	void setValue(int position, E value) throws IndexOutOfBoundsException {
		if (position >= this.length)
			throw new IndexOutOfBoundsException();
		else
			this.values.set(position, value);
	}
	
	int getLength() {
		return this.length;
	}

}
