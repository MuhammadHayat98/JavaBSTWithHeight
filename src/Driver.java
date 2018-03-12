import java.util.*;
public class Driver {



	
	   public static void main(String[] args)
	   {
	      BST<Integer> bst = new BST();
	      int[] values = {50, 20, 100, 150, 70, 30, 110};
	      for( int i = 0; i < values.length; i++)
	         bst.insert(values[i]);
	      System.out.println("Practice Test");
	      System.out.println("Inorder Traversal1: ");
	      bst.inOrderTraversal();
	      System.out.println("Preorder Traversal1: "); 
	      bst.preOrderTraversal();

	      System.out.println( "Tree Height = " + bst.getHeight() + "\t\tCorrect Answer: 3" );
	      System.out.println( "Min Value =   " + bst.findMin() + "\t\tCorrect Answer:20");
	      System.out.println( "Max Value =   " + bst.findMax() + "\t\tCorrect Answer:150");
	      
	      int max = bst.removeMax();
	      int min = bst.removeMin();
	      System.out.println ("removeMax returns " + max + "\t\tCorrect Answer:150");
	      System.out.println ("removeMin returns " + min + "\t\tCorrect Answer:20");
	      System.out.println( "Tree Height = " + bst.getHeight() + "\t\tCorrect Answer: 2" );
	      System.out.println( "Min Value =   " + bst.findMin() + "\tCorrect Answer:30");
	      System.out.println( "Max Value =   " + bst.findMax() + "\tCorrect Answer:110");
	      
	      
	      System.out.println("Inorder Traversal2: ");
	      bst.inOrderTraversal();
	      System.out.println("Preorder Traversal2: "); 
	      bst.preOrderTraversal();
	      
	      bst.insert( 60);
	      bst.insert(55); 
	      bst.insert(62);
	      bst.insert(61);
	      bst.insert(63);
	      bst.remove(70);
	      bst.remove(50);

	      System.out.println( "Tree Height = " + bst.getHeight() + "\t\tCorrect Answer: 4" );
	      System.out.println( "Min Value =   " + bst.findMin() + "\t\tCorrect Answer:30");
	      System.out.println( "Max Value =   " + bst.findMax() + "\t\tCorrect Answer:110");
	      System.out.println("Inorder Traversal3: ");
	      bst.inOrderTraversal();
	      System.out.println("Preorder Traversal3: "); 
	      bst.preOrderTraversal();
	      



	   }
	      
	  }
	/*******Results **************************
	     ----jGRASP exec: java Proj3PracticeTestCase
	 Practice Test
	 Inorder Traversal1: 
	 (i:20,h:1) (i:30,h:0) (i:50,h:3) (i:70,h:0) (i:100,h:2) (i:110,h:0) (i:150,h:1) 
	 Preorder Traversal1: 
	 (i:50,h:3) (i:20,h:1) (i:30,h:0) (i:100,h:2) (i:70,h:0) (i:150,h:1) (i:110,h:0) 
	 Tree Height = 3		Correct Answer: 3
	 Min Value =   20		Correct Answer:20
	 Max Value =   150		Correct Answer:150
	 removeMax returns 150		Correct Answer:150
	 removeMin returns 20		Correct Answer:20
	 Tree Height = 2		Correct Answer: 2
	 Min Value =   30	Correct Answer:30
	 Max Value =   110	Correct Answer:110
	 Inorder Traversal2: 
	 (i:30,h:0) (i:50,h:2) (i:70,h:0) (i:100,h:1) (i:110,h:0) 
	 Preorder Traversal2: 
	 (i:50,h:2) (i:30,h:0) (i:100,h:1) (i:70,h:0) (i:110,h:0) 
	 Tree Height = 4		Correct Answer: 4
	 Min Value =   30		Correct Answer:30
	 Max Value =   110		Correct Answer:110
	 Inorder Traversal3: 
	 (i:30,h:0) (i:55,h:4) (i:60,h:2) (i:61,h:0) (i:62,h:1) (i:63,h:0) (i:100,h:3) (i:110,h:0) 
	 Preorder Traversal3: 
	 (i:55,h:4) (i:30,h:0) (i:100,h:3) (i:60,h:2) (i:62,h:1) (i:61,h:0) (i:63,h:0) (i:110,h:0) 
	 
	  ----jGRASP: operation complete.

	*/




