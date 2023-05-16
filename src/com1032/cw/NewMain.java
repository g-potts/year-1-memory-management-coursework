package com1032.cw;

public class NewMain {

	public static void main(String[] args) {
		
		Memory m = new Memory(1024, 124);
		System.out.println("starting memory state:");
		m.memoryState();
		
		System.out.println("make process with rwe permissions");
		Process p = new Process("1, [100; r--], [200; r-x], [300; -wx]");
		System.out.println(p.toString());
		System.out.println("permissions:");
		System.out.println(p.getSegment(0).getPermissions());
		System.out.println("changing from r-- to rwx");
		p.setSegmentPermissions(0, "rwx");
		System.out.println(p.getSegment(0).getPermissions());
		m.allocate(p);
		m.memoryState();
		
		Process p2 = new Process("2, 100, 200, 20");
		System.out.println(p2.toString());
		System.out.println("permissions:");
		System.out.println(p2.getSegment(0).getPermissions());
		
		System.out.println("try to read p1s0 via memory");
		System.out.println(m.checkHasPermission(124, 'r'));
		
//		Memory m = new Memory(1024, 124);
//		System.out.println("starting memory state:");
//		m.memoryState();
//		
//		Process p1 = new Process("1, 100, 200, 20");
//        Process p2 = new Process("2, 70, 87, 20, 55");
//        System.out.println("processes:");
//        System.out.println(p1.toString());
//        System.out.println(p2.toString());
//		
//        System.out.println("allocate p1");
//        m.allocate(p1);
//        m.memoryState();
//        
//        System.out.println("try deallocating s2 to trigger refresh");
//        m.deallocate(p1, p1.getSegment(2));
//        m.memoryState();
//        
//        System.out.println("deallocate all p1");
//        m.deallocate(p1);
//        m.memoryState();
//        
//        m.allocate(p1);
//        m.allocate(p2);
//        m.memoryState();
//        m.deallocate(p1);
//        m.memoryState();
//        m.compact();
//        m.memoryState();
		
		/*
		Memory m = new Memory(1024, 124);
		System.out.println("Current memory state: ");
		m.memoryState();
		
		Process p1 = new Process("1, 100, 200, 20");
        Process p2 = new Process("2, 70, 87, 20, 55");
        System.out.println("Current process states:");
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        
        System.out.println("Allocating p1 to m: ");
        m.allocate(p1);
        m.memoryState();
        p1.segmentTable();
        
        System.out.println("Allocating p2 to m: ");
        m.allocate(p2);
        m.memoryState();
        p2.segmentTable();
        
        System.out.println("Resizing process p2: ");
        System.out.println("segment table before");
        p2.segmentTable();
        p2.resize("10, -10, 100, -50");
        m.resizeProcess(p2);
        System.out.println("memory state after: ");
        m.memoryState();
        System.out.println("Segment table after");
        p2.segmentTable();
        
        System.out.println("Attempting to add p3:");
        Process p3 = new Process("3, 10, 260, 40, 10, 70");
        p3.toString();
        m.allocate(p3);
        m.memoryState();
        p3.segmentTable();
        
        System.out.println("allocating process with only segment larger than hole");
        Process p4 = new Process("4, 350");
        p4.toString();
        m.allocate(p4);
        
        m.deallocate(p1);
        m.memoryState();
        m.deallocate(p2);
        m.memoryState(); */
	}

}
