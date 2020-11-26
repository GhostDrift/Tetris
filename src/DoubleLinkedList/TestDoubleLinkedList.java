package DoubleLinkedList;

public class TestDoubleLinkedList {

	public static void main(String[] args) {
		
		// -- construct a linked-list of Integer and add some items
		//    print it forward and backward
		//    note that auto-boxing will convert int to Integer
		DoubleLinkedList<Integer> dlli = new DoubleLinkedList<Integer>();		
		System.out.println(dlli.isEmpty());
		dlli.add(0, 1);
		dlli.add(0, 2);
		dlli.add(0, 3);
		System.out.println(dlli.isEmpty());
		dlli.printForward();
		System.out.println();
		dlli.printReverse();
		System.out.println();
		
		// -- add and item to the back
		dlli.add(44, 4);
		dlli.printForward();
		System.out.println();

		// -- add some items in the middle
		dlli.add(2, 5);
		dlli.add(3, 6);
		dlli.printForward();
		System.out.println();
		dlli.printReverse();
		System.out.println();
		
		// -- convert to an Integer[] and print forward
		Integer ai[] = new Integer[dlli.size()];
		ai = dlli.toArray(ai);
		for (Integer i : ai) {
			System.out.println(i);
		}
		System.out.println();
		
		// -- check for containment 
		//    auto-boxing will convert int 4 and int 44 to Integers
		System.out.println(dlli.contains(4));
		System.out.println(dlli.contains(44));
		System.out.println();
		
		// -- clear all elements from the linked-list and check if it is empty
		dlli.clear();
		System.out.println(dlli.isEmpty());
		
		// -- construct a linked-list of Integer and print it forward
		dlli = new DoubleLinkedList<Integer>();
		dlli.add(0, 4);
		dlli.add(0, 3);
		dlli.add(0, 2);
		dlli.add(0, 1);
		dlli.printForward();
		System.out.println();
		
		// -- remove some values
		//    auto-boxing will not work in this instance because there
		//    is a method remove(int)
		dlli.remove(new Integer(2));
		dlli.printForward();
		System.out.println();

		dlli.remove(new Integer(1));
		dlli.printForward();
		System.out.println();

		dlli.remove(new Integer(4));
		dlli.printForward();
		System.out.println();
		
		dlli.remove(new Integer(3));
		dlli.printForward();
		System.out.println();
	
		// -- construct a linked-list of Integer
		dlli = new DoubleLinkedList<Integer>();
		dlli.add(0, 4);
		dlli.add(0, 3);
		dlli.add(0, 2);
		dlli.add(0, 1);
		dlli.printForward();
		System.out.println();

		// -- remove some objects at specific indices
		//    argument will not be auto-boxed since remove(int) exists
		System.out.println("removed " + dlli.remove(3) + " from location " + 3);
		dlli.printForward();
		System.out.println();

		// -- construct a linked-list of Double
		DoubleLinkedList<Double> dlld = new DoubleLinkedList<Double>();
		dlld.add(0, 4.4);
		dlld.add(0, 3.3);
		dlld.add(0, 2.2);
		dlld.add(0, 1.1);
		dlld.add(0, 3.3);
		dlld.printForward();
		System.out.println();
		
		// -- search the list from the front and back
		System.out.println(dlld.indexOf(3.3));
		System.out.println(dlld.lastIndexOf(3.3));
		
		// -- replace the object at location 2
		Double d = dlld.set(2,  5.5);
		System.out.println("replaced: " + d + " with " + new Double(5.5));
		dlld.printForward();
		System.out.println();
		
		// -- try to replace the object at location 100
		//    note that there is no object at location 100
		try {
			dlli.set(100,  100);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		System.out.println();

		// -- construct a link-list of String
		DoubleLinkedList<String> dlls = new DoubleLinkedList<String>();
		dlls.add(0, "a");
		dlls.add(0, "b");
		dlls.add(0, "c");
		dlls.add(0, "d");
		dlls.add(0, "e");
		dlls.printForward();
		System.out.println();

	}

}