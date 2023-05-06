package com1032.cw;

import java.util.ArrayList;
import java.util.List;

public class Process {
	
	private int pid; // the id of the process
	private SegmentTable segmentTable;
	
	public Process (String processString) {
		ArrayList<Segment> segmentList = new ArrayList<Segment>();
		
		Parser P = new Parser();
		ArrayList<String>[] list = P.parseInputString(processString);
		
		this.pid = Integer.valueOf(list[0].get(0));
		
		for(int id = 1; id < list.length; id++) {
			int segmentSize = Integer.valueOf(list[id].get(0));
			int sid = id - 1;
			String segmentName = String.format("P%d S%d", pid, sid);//P1 S0
			Segment segment = new Segment(segmentName, sid, segmentSize);
			segmentList.add(segment);
		}
		
		segmentTable = new SegmentTable(segmentList);
	}

	/**
	 * 
	 * @param segments the list of segments that belong to the process
	 */
	public void resize(String segments) {
		//string is adjustments to each part, ie -10 or 5
		//if size below zero remove segment
		//does not need same no of elements to string, just goes in order
		//error if more than there ?
	}
	
	/**
	 * TODO: return the segment with the input ID
	 * @param id is the segment ID of the process
	 */
	public Segment getSegment(int id) {
		//find segment from id ---- done ???
		return segmentTable.findSegment(id);
	}
	
	public ArrayList<Segment> getSegments() {
		return segmentTable.getSegments();
	}
	/**
	 * to print the details of segments of the process
	 */
	public void segmentTable() {
		System.out.println(this.segmentTable.toString());
	}
	/**
	 * output the details of the process, which includes process Id and segment details
	 */
	public String toString() {
		//format: (segment, s2, etc)
		return "Process and its segments details";
	}
}
