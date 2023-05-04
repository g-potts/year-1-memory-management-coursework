package com1032.cw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class defines the Segment Table of a Process.
 * TODO: this class is incomplete
 */

public class SegmentTable {
	
	private ArrayList<Segment> segments;
	private Map<Segment, Integer> allocations;
	
	/*
	 * Constructor of SegmentTable
	 */
	public SegmentTable(ArrayList<Segment> seg) {
		this.segments = seg;
		this.allocations = new HashMap<Segment, Integer>();
	}
	
	
	/*
	 * display the details of all of the segments in the table
	 */
	public String toString() {
		//format: SID | base | limit - contents continue - padding to make table consistent?
		String output = "SID | base | limit\n";
		for (Segment segment : segments) {
			output += segment.toString();
		}
		return output;
		
	}

}
