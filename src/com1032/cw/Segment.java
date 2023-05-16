package com1032.cw;

// TODO: complete this Segment class

public class Segment extends MemoryItem {

	private int id; // the id of the segment
	private int allocated; //-1 when not allocated memory space
	private boolean hasPermissions = false;
	private boolean read;
	private boolean write;
	private boolean execute;

	/**
	 * the constructor of Segment
	 * 
	 * @param segmentID the id of the segment
	 * @param size      the size of the segment
	 */
	public Segment(String segmentID, int id, int size) {
		super(segmentID, size);
		this.id = id;
		allocated = -1;
	}
	
	public void setAllocation(int value) {
		allocated = value;
	}
	
	public void setPermissions(boolean r, boolean w, boolean x) {
		hasPermissions = true;
		read = r;
		write = w;
		execute = x;
	}
	public boolean canRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean canWrite() {
		return write;
	}

	public void setWrite(boolean write) {
		this.write = write;
	}

	public boolean canExecute() {
		return execute;
	}

	public void setExecute(boolean execute) {
		this.execute = execute;
	}

	public int getID() {
		return this.id;
	}
	
	public String getPermissions() {
		if (hasPermissions == false) {
			return "segments does not have permissions assigned";
		}
		String output = "";
		output += "Read: " + this.canRead();
		output += "\nWrite: " + this.canWrite();
		output += "\nExecute: " + this.canExecute();
		return output;
	}
	public String toString() {
		// TODO: print the details of this segment
		// format as id | location | size
		String location = " ";
		if (allocated > -1) {
			location = Integer.toString(allocated);
		}
		return String.format("%3s | %4s | %5d | ", id, location, this.getSize());
	}
}
