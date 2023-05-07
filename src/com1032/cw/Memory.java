package com1032.cw;

import java.util.ArrayList;
import java.util.List;

public class Memory {

	/**
	 * Declare private variables and use the following methods and new methods to
	 * access them
	 */
	private int OSsize;
	private int total_size;
	private List<MemoryItem> userMemory;
	
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
		this.userMemory = new ArrayList<MemoryItem>();
		userMemory.add(new MemoryItem("Hole", size - os_size));
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
		for (Segment s : process.getSegments()) {
			try {
				allocate(process, s);
			} catch (NullPointerException e) {}
		}
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
		if (userMemory.contains(seg)) {
			return;
		}
		
		List<MemoryItem> holes = new ArrayList<MemoryItem>();
		for (MemoryItem item : userMemory) {
			if (item.getName() == "Hole") {
				holes.add(item);
			}
		}
		
		//find hole with least difference in size - BEST FIT
		System.out.println("holes found: " + holes.size());
		MemoryItem chosenHole = holes.get(0);
		for (MemoryItem hole : holes) {
			System.out.println("current hole size " + hole.getSize());
			int currentDif = hole.getSize() - seg.getSize();
			if (currentDif >= 0 && (currentDif < (holes.get(0).getSize() - seg.getSize()))) {
				chosenHole = hole;
				System.out.println("changed");
			}
		}
		int newHoleSize = chosenHole.getSize() - seg.getSize();
		if (newHoleSize < 0) {
			throw new NullPointerException("No hole found for this segment");
		} else {
			//change from [hole] to [segment and hole] AT SAME INDEXt
			int holeIndex = userMemory.indexOf(chosenHole);
			userMemory.remove(chosenHole);
			userMemory.add(holeIndex, seg);
			holeIndex++;
			MemoryItem newHole = new MemoryItem("Hole", newHoleSize);
			userMemory.add(holeIndex, newHole);
			p.allocateSegment(seg, getSegmentLocation(seg));
		}
		
	}

	/**
	 * remove a segment of the process from the memory
	 * 
	 * @param p   the process with the segment
	 * @param seg the segment to be removed from the main memory
	 */
	public int deallocate(Process p, Segment seg) {
		if (!userMemory.contains(seg)) {
			return -1;
		}
		//change from [seg] [hole] to [hole]
		int segIndex = userMemory.indexOf(seg);
		MemoryItem nextItem = userMemory.get(segIndex + 1);
		if (((segIndex + 1) < userMemory.size()) && nextItem.getName() == "Hole") {
			MemoryItem newHole = new MemoryItem("Hole", seg.getSize() + nextItem.getSize());
			userMemory.remove(seg);
			userMemory.remove(nextItem);
			userMemory.add(segIndex, newHole);
		} else {
			userMemory.remove(seg);
			userMemory.add(segIndex, new MemoryItem("Hole", seg.getSize()));
		}
		p.deallocateSegment(seg);
		return 1;
	}

	/**
	 * Deallocate memory allocated to this process
	 * 
	 * @param process the process to be deallocated
	 * @return return 1 if successful, -1 otherwise with an error message
	 */
	public int deallocate(Process process) {
		boolean successful = false;
		for (Segment s : process.getSegments()) {
			if (deallocate(process, s) == 1) {
				successful = true;
			}
		}
		if (successful) {
			refreshMemory();
			return 1;
		} else {
			System.out.println("No segments of this process found");
			return -1;
		}	
	}
	
	private void refreshMemory() {
		for (MemoryItem item : userMemory) {
			int itemIndex = userMemory.indexOf(item);
			if (itemIndex + 1 < userMemory.size()) {
				MemoryItem nextItem = userMemory.get(itemIndex + 1);
				if (item.getName() == "Hole" && nextItem.getName() == "Hole") {
					MemoryItem combinedHole = new MemoryItem("Hole", item.getSize() + nextItem.getSize());
					userMemory.remove(item);
					userMemory.remove(nextItem);
					userMemory.add(itemIndex, combinedHole);
					refreshMemory();
				}
			}
			
		}
	}

	private int getSegmentLocation(Segment seg) {
		int location = OSsize;
		for (MemoryItem i : userMemory) {
			if (i == seg) {
				break;
			} else {
				location += i.getSize();
			}
		}
		return location;
	}
	
	/**
	 * the process p will be updated
	 * 
	 * @param p the input process to be updated/resized
	 * @return return 1 if successful, -1 otherwise with an error message
	 */
	public int resizeProcess(Process p) {
		//called by process method
		return 1; //-1 if fail
	}

	/**
	 * function to display the state of memory to the console
	 */
	public void memoryState() {
		String output = "";
		output += "[OS " + OSsize + "] | ";
		for (MemoryItem item : userMemory) {
			output += String.format("[%s: %d] ", item.getName(), item.getSize());
		}
		System.out.println(output);
	}

}
