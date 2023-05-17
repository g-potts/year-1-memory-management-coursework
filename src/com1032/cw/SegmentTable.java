package com1032.cw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SegmentTable {
	
	private ArrayList<Segment> segments;
	private Map<Segment, Integer> allocations;
	
	/**
	 * constructor for segment table object
	 * @param seg list of segments to add to table
	 */
	public SegmentTable(ArrayList<Segment> seg) {
		this.segments = seg;
		this.allocations = new HashMap<Segment, Integer>();
	}
	
	/**
	 * find segment in table from id number
	 * @param id id of segment to be found
	 * @return the segment found
	 */
	public Segment findSegment(int id) {
		Segment segment = null;
		for (Segment s : segments) {
			if (s.getID() == id) {
				segment = s;
			}
		}
		return segment;
	}
	
	/**
	 * stores location of segment
	 * @param seg segment to allocate
	 * @param location location in main memory
	 */
	public void allocateSegment(Segment seg, int location) {
		allocations.put(seg, location);
		seg.setAllocation(location);
	}
	/**
	 * remove allocation of segment
	 * @param seg segment to deallocate
	 */
	public void deallocateSegment(Segment seg) {
		allocations.remove(seg);
		seg.setAllocation(-1);
	}
	
	/**
	 * remove segment from table
	 * @param seg segment to remove
	 */
	public void removeSegment(Segment seg) {
		segments.remove(seg);
	}
	
	public ArrayList<Segment> getSegments() {
		return segments;
	}
	
	/**
	 * add new segment to table
	 * @param size size of segment
	 */
	public void addSegment(String name, int id, int size) {
		segments.add(new Segment(name, id, size));
	}
	
	/**
	 * create table of segments and their information
	 */
	@Override
	public String toString() {
		String output = "SID | base | limit | valid-invalid\n";
		for (Segment segment : segments) {
			int bit = 0;
			if (allocations.containsKey(segment)) {
				bit++;
			}
			output += segment.toString() + bit + "\n";
		}
		return output;
	}

}
