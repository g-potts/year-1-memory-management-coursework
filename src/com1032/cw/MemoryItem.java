package com1032.cw;

public class MemoryItem {
	
	private String name;
	private int size;
	
	public MemoryItem(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public void adjustSize(int amount) {
		this.size += amount;
	}
}
