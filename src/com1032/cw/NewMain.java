package com1032.cw;

public class NewMain {

	public static void main(String[] args) {
		
		
		System.out.println("##### START COMPONENT B.1 #####");
		Memory m = new Memory(1024, 124);
		System.out.println("\n# initial memory state:");
		m.memoryState();
		
		System.out.println("\n# Creating processes P1-5:");
		Process p1 = new Process("1, 100, 200, 300");
		Process p2 = new Process("2, 70, 87, 20");
	    Process p3 = new Process("3, 10, 260, 40, 10, 70");
	    Process p4 = new Process("4, 5, 60, 97, 44");
        Process p5 = new Process("5, 26, 77, 42, 100");
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p3.toString());
		System.out.println(p4.toString());
		System.out.println(p5.toString());
		
		System.out.println("\n# Allocating P1 S2 to memory");
		System.out.println("- Before: ");
		m.memoryState();
		p1.segmentTable();
		m.allocate(p1, p1.getSegment(2));
		System.out.println("+ After: ");
		m.memoryState();
		p1.segmentTable();
		
		System.out.println("\n# Allocating P1 to memory");
		System.out.println("- Before: ");
		m.memoryState();
		p1.segmentTable();
		m.allocate(p1);
		System.out.println("+ After: ");
		m.memoryState();
		p1.segmentTable();
		
		System.out.println("# Deallocating P1 S0:");
		m.deallocate(p1, p1.getSegment(0));
		m.memoryState();
		p1.segmentTable();
		System.out.println("# Deallocating P1 S1:");
		m.deallocate(p1, p1.getSegment(1));
		m.memoryState();
		p1.segmentTable();
		
		System.out.println("# Allocating P2 to memory");
		System.out.println("- Before: ");
		m.memoryState();
		p2.segmentTable();
		m.allocate(p2);
		System.out.println("+ After: ");
		m.memoryState();
		p2.segmentTable();
		
		System.out.println("# Resizing P2");
		System.out.println("- before:");
		m.memoryState();
		p2.segmentTable();
		System.out.println("-> adjusting by (+10, -7, +100, +10) with one new segment being added");
		System.out.println("+ after:");
		m.resizeProcess(p2, "10, -7, 100, 10");
		m.memoryState();
		p2.segmentTable();
		System.out.println("##### END COMPONENT B.1 #####\n");
		
		System.out.println("##### START COMPONENT B.2.1 : valid-invalid bit #####");
		Memory m2 = new Memory(1500, 500);
		System.out.println("initial memory state:");
		m2.memoryState();
		System.out.println("create process P6:");
		Process p6 = new Process("6, 100, 200, 300");
		System.out.println(p6.toString());
		p6.segmentTable();
		System.out.println("allocating segments 0 and 2 to memory");
		m2.allocate(p6, p6.getSegment(0));
		m2.allocate(p6, p6.getSegment(2));
		m.memoryState();
		p6.segmentTable();
		System.out.println("##### END COMPONENT B.2.1 #####");
		
		System.out.println("##### START COMPONENT B.2.2 : read-write-execute permissions #####");
		//creating segment without perms
		//creating with perms
		//
		System.out.println("##### END COMPONENT B.2.2 #####");
		
		System.out.println("##### START COMPONENT B.2.4 : compaction #####");
		System.out.println("##### END COMPONENT B.2.4 #####");
	}

}
