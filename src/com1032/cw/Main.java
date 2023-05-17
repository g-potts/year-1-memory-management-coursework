package com1032.cw;

public class Main {

	
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
		System.out.println("Process made without permissions specified:");
		Process p7 = new Process("7, 200");
		System.out.println(p7.toString());
		System.out.println(p7.getSegment(0).getPermissions());
		
		System.out.println("\nProcess made with permissions specified");
		Process p8 = new Process("8, [100; r-x], [200; -w-], [300; rw-]");
		System.out.println(p8.toString());
		System.out.println("S0 permissions:");
		System.out.println(p8.getSegment(0).getPermissions());
		System.out.println("\nS1 permissions:");
		System.out.println(p8.getSegment(1).getPermissions());
		System.out.println("\nS2 permissions:");
		System.out.println(p8.getSegment(2).getPermissions());
		
		System.out.println("\nP8 allocated to memory");
		m2.allocate(p8);
		m2.memoryState();
		p8.segmentTable();
		
		System.out.println("checking permissions for P8 S1 based on locations");
		System.out.println("read: " + m2.checkHasPermission(1000, 'r'));
		System.out.println("write: " + m2.checkHasPermission(1000, 'w'));
		
		System.out.println("\nChanging P8 S2 from rw- to r-x");
		System.out.println("- Before:");
		System.out.println(p8.getSegment(2).getPermissions());
		System.out.println("\n+ After:");
		p8.getSegment(2).setPermissions(true, false, true);
		System.out.println(p8.getSegment(2).getPermissions());
		System.out.println("##### END COMPONENT B.2.2 #####");
		
		System.out.println("##### START COMPONENT B.2.4 : compaction #####");
		System.out.println("initial memory state:");
		Memory m3 = new Memory(1250, 350);
		m3.memoryState();
		System.out.println("\nAllocate P1 to memory:");
		m3.allocate(p1);
		m3.memoryState();
		p1.segmentTable();
		System.out.println("Deallocate P1 S1 from memory:");
		m3.deallocate(p1, p1.getSegment(1));
		p1.segmentTable();
		
		System.out.println("- Before compaction:");
		m3.memoryState();
		System.out.println("+ After compaction:");
		m3.compact();
		m3.memoryState();
		System.out.println("##### END COMPONENT B.2.4 #####");
		
		System.out.println("##### START COMPONENT B.3 : exceptions #####");
		System.out.println("1) if OS is larger than whole memory:");
		try {
			Memory mem = new Memory(200, 300);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		System.out.println("\n2) not enough memory for all segments:");
		Memory mem = new Memory(300, 200);
		Process process = new Process("10, 100, 200");
		mem.allocate(process);
		mem.memoryState();
		
		System.out.println("\n3) resized segment becoming negative:");
		try {
			mem = new Memory(1010, 10);
			mem.allocate(process);
			mem.resizeProcess(process, "-10, -150, -300");
			System.out.println(process.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		System.out.println("\n4) process ID not being positive:");
		try {
			process = new Process("-10, 1, 2, 3");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		System.out.println("\n5) out of bounds exception:");
		System.out.println("when finding segment within process by index:");
		try {
			process.getSegment(5);
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		System.out.println("\n6) reference exception:");
		System.out.println("when checking permissions of segment not in memory");
		mem.allocate(process, process.getSegment(0));
		try {
			mem.checkHasPermission(500, 'x');
		} catch (NullPointerException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		System.out.println("##### END COMPONENT B.3 #####");
	}
}
