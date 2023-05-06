package com1032.cw;

// TODO: complete this Segment class

public class Segment extends MemoryItem {

	private int id; // the id of the segment
	private Boolean allocated;

	/**
	 * default constructor of a Segment
	 */
//	public Segment() {
//		// TODO: to be completed
//		allocated = false;
//	}

	/**
	 * the constructor of Segment
	 * 
	 * @param segmentID the id of the segment
	 * @param size      the size of the segment
	 */
	public Segment(String segmentID, int id, int size) {
		super(segmentID, size);
		this.id = id;
		allocated = false;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String toString() {
		// TODO: print the details of this segment
		// format as id | location | size
		String location = " ";
		if (allocated) {
			//get location from memory ???
		}
		return String.format("%3s | %4s | %5d \n", id, location, this.getSize());
	}
}
