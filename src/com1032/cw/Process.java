package com1032.cw;

import java.util.ArrayList;

public class Process {
	
	private int pid;
	private SegmentTable segmentTable;
	
	/**
	 * constructor for process object
	 * @param processString string with sizes of segments separated by commas
	 */
	public Process (String processString) {
		ArrayList<Segment> segmentList = new ArrayList<Segment>();
		Parser P = new Parser();
		ArrayList<String>[] list = P.parseInputString(processString);
		if (Integer.valueOf(list[0].get(0)) < 0) {
			throw new IllegalArgumentException("Process ID must be greater than or equal to 0");
		}
		this.pid = Integer.valueOf(list[0].get(0));
		
		//parse segment sizes and any permissions
		for(int id = 1; id < list.length; id++) {
			int segmentSize = Integer.valueOf(list[id].get(0));
			int sid = id - 1;
			String segmentName = String.format("P%d S%d", pid, sid);
			Segment segment = new Segment(segmentName, sid, segmentSize);
			if (list[id].size() > 1) {
				char[] perm = list[id].get(1).toCharArray();
				if (!list[id].get(1).matches("(r|-)(w|-)(x|-)")) {
					throw new IllegalArgumentException("permissions must be in form rwx or ---");
				}
				segment.setPermissions((perm[0] == 'r'), (perm[1] == 'w'), (perm[2] == 'x'));
			}
			segmentList.add(segment);
		}
		segmentTable = new SegmentTable(segmentList);
	}

	/**
	 * change sizes for processes
	 * @param segments the list of segments that belong to the process
	 */
	public void resize(String segments) {
		Parser p = new Parser();
		ArrayList<String>[] parsedSegments = p.parseInputString(segments);
		if (parsedSegments.length > this.getSegments().size()) {
			throw new IllegalArgumentException("More resize arguments provided than segments available");
		}
		int i = 0;
		ArrayList<Segment> segmentlist = this.getSegments();
		for (ArrayList<String> s: parsedSegments) {
			Segment segToChange = segmentlist.get(i);
			segToChange.adjustSize(Integer.valueOf(s.get(0)));
			int newSize = segToChange.getSize();
			if (newSize < 0) {
				throw new IllegalArgumentException("Segment size cannot be negative");
			} else if (newSize == 0) {
				this.segmentTable.removeSegment(segToChange);
			}
			i++;
			
		}
		
	}
	
	/**
	 * tell segment table where a segment is allocated to
	 * @param seg segment being allocated
	 * @param location where segment is allocated to in main memory
	 */
	public void allocateSegment(Segment seg, int location) {
		segmentTable.allocateSegment(seg, location);
	}
	
	/**
	 * tell segment table segment has been deallocated
	 * @param seg segment being deallocated
	 */
	public void deallocateSegment(Segment seg) {
		segmentTable.deallocateSegment(seg);
	}
	
	public void setSegmentPermissions(int id, String permissions) {
		char[] perm = permissions.toCharArray();
		if (!permissions.matches("(r|-)(w|-)(x|-)")) {
			throw new IllegalArgumentException("permissions must be in form rwx or ---");
		}
		segmentTable.getSegments().get(id).setPermissions((perm[0] == 'r'), (perm[1] == 'w'), (perm[2] == 'x'));
	}
	/**
	 * return the segment with the input ID
	 * @param id is the segment ID of the process
	 */
	public Segment getSegment(int id) {
		Segment s = segmentTable.findSegment(id);
		if (s == null) {
			throw new IndexOutOfBoundsException("No such segment exists");
		} else {
			return s;
		}
	}
	
	public ArrayList<Segment> getSegments() {
		return segmentTable.getSegments();
	}
	
	/**
	 * print the details of the segments of this process
	 */
	public void segmentTable() {
		System.out.println(this.segmentTable.toString());
	}
	
	/**
	 * output the details of the process, which includes process Id and segment sizes
	 */
	public String toString() {
		String output = "P" + pid + "(";
		ArrayList<Segment> segments = this.getSegments();
		int i;
		for (i = 0; i < segments.size() - 1 ; i++) {
			output += segments.get(i).getSize() + ", ";
		}
		output += segments.get(i).getSize() + ")";
		return output;
	}
}
