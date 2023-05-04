package com1032.cw;

import java.util.HashMap;
import java.util.Map;

public class Memory {

	/**
	 * Declare private variables and use the following methods and new methods to
	 * access them
	 */
	private int OSsize;
	private int total_size;
	private List<> userMemory; //Map<Name, Size>
	/**
	 * Main Memory Constructor
	 * 
	 * @param size is total memory size
	 */
	public Memory(int size, int os_size) {
		// TODO: this function is to be updated
		if (os_size > size) {
			throw new IllegalArgumentException("total memory size must be larger than os size");
		}
		this.total_size = size;
		this.OSsize  = os_size;
		this.userMemory = new ArrayList<>();
	}
	
	/**
	 * Allocate a process to the memory
	 * 
	 * @param process, a process to be allocated to the memory
	 * @return return 1 if successful, -1 otherwise with an error message
	 */
	public int allocate(Process process) {
		// make sure segments of a process are only allocated to the memory once
		// i.e, only allocate segments that aren't loaded in the memory
		
		//for each segement in process: allocate(process, segment)
		return 1; //-1 if fail
	}

	/**
	 * add a segment of the process to the memory
	 * 
	 * @param p   the process with the segment
	 * @param seg the segment to be allocated
	 */
	public void allocate(Process p, Segment seg) {
		//has arraylist sorted with items
	}

	/**
	 * remove a segment of the process from the memory
	 * 
	 * @param p   the process with the segment
	 * @param seg the segment to be removed from the main memory
	 */
	public int deallocate(Process p, Segment seg) {

		return 1; //-1 for unsuccessful
	}

	/**
	 * Deallocate memory allocated to this process
	 * 
	 * @param process the process to be deallocated
	 * @return return 1 if successful, -1 otherwise with an error message
	 */
	public int deallocate(Process process) {

		return 1; //-1 for unsuccessful
	}

	/**
	 * the process p will be updated
	 * 
	 * @param p the input process to be updated/resized
	 * @return return 1 if successful, -1 otherwise with an error message
	 */
	public int resizeProcess(Process p) {
		return 1; //-1 if fail
	}

	/**
	 * function to display the state of memory to the console
	 */
	public void memoryState() {
		//INCLUDES [PROCESS SEGMENT SIZE] THEN HOLE THEN OTHER PROCESS ETC
		System.out.println(String.format("[OS %d] | [Hole %d]", OSsize, total_size - OSsize)); //will break
	}

}
