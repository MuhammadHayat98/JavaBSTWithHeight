//Comp 282
//Implementing a Binary Search Tree as a generic class
//Posted as BST.java for Project 3


class BSTNode<E >
{
   E item;   
   BSTNode<E> left;
   BSTNode<E> right;
   BSTNode<E> parent;
   int height;
  
   public BSTNode ( E  x)
   {
      item = x; left = null; right = null; parent = null; this.height = 0;
      
   }
   
   public BSTNode (E x , BSTNode<E> left, BSTNode<E> right, BSTNode<E> parent)
   {
      item = x; 
      this.left = left; this.right = right; this.parent = parent; this.height = 0;  
   }
   
   public String toString()
   {
      return "(i:" + item + ", h: " + height + ")";
   }
}

/*----------------class BST ---------------------------*/
public class BST<E extends Comparable<E>>
{
   private BSTNode<E> root;
   private int size;

   
   
   public BST()
   {  root = null;  size = 0;   
   }
   
   /*---------------- public operations --------------------*/
   
      
        
   
   public int getSize()
   {  
      return size;
   }
   
   public int getHeight() {
	   return root.height;
   }
   
        
   public boolean find( E x)
   {
      if( find(x,root) == null)
         return false;
      else
         return true;
   }
    
   
   public void preOrderTraversal()
   {
      preOrder (root);
      System.out.println();
   }
   
   public void inOrderTraversal()
   {
      inOrder (root);
      System.out.println();
   }
   
      
   public boolean insert( E x )
   {
   
      if( root == null)
      {
         root = new BSTNode(x, null, null, root);
         size++;
         return true;
      }    
       
      BSTNode<E> parent = null;
      BSTNode<E>  p = root;
      
      while (p != null)
      {
         if(x.compareTo(p.item) < 0)
         {
            parent = p; p = p.left;
         }
         else if ( x.compareTo(p.item) > 0)
         {
            parent = p; p = p.right;
         }
         else  // duplicate value
            return false;  
      }
      
      //attach new node to parent
      BSTNode<E> insertedNode = new BSTNode<E>(x, null, null, parent);
      if( x.compareTo(parent.item) < 0)
         parent.left = insertedNode;
      else
         parent.right = insertedNode;
      size++; 
      assignHeight(insertedNode);
      return true;   
        
   }  //insert
   
   
   public boolean remove(E x)
   {
      if(root == null)
         return false;  //x is not in tree
     
      //find x
      BSTNode<E> p = find(x, root);
      if( p == null)
         return false;  //x not in tree
                  
      //Case: p has a right child child and no left child
      if( p.left == null && p.right != null) {
         deleteNodeWithOnlyRightChild(p);
         assignHeight(p.right);
      }     
       //Case: p has a left child and has no right child
      else if( p.left !=null && p.right == null) {
         deleteNodeWithOnlyLeftChild(p);
         assignHeight(p.left);
      }
            //case: p has no children
      else if (p.left ==null && p.right == null) {
         deleteLeaf(p);
         assignHeight(p.parent);
      }
                
      else //case : p has two children. Delete successor node
      {
         BSTNode<E> succ =  getSuccessorNode(p);;
        
         p.item = succ.item;
           
          //delete succ node
         if(succ.right == null)
            deleteLeaf(succ);
         else
            deleteNodeWithOnlyRightChild(succ);
         
         assignHeight(succ.parent);
      }
      return true;         
   }   //remove
   
   public E findMin() {
	   if(root == null)
		   return null;
	   else {
		   BSTNode<E> tmp = this.root;
		   while(tmp.left != null) {
			   tmp = tmp.left;
		   }
		   return tmp.item;
	   }
   }
   
   public E findMax() {
	   //t
	   BSTNode<E> tmp = root;
	   while(tmp.right != null) {
		   tmp = tmp.right;
	   }
	   return tmp.item;
   }
   
   
   
 
  /********************private methods ******************************/
  
        

   private BSTNode<E> find(E x, BSTNode<E> t)  
   {
      BSTNode<E> p = t;
      while ( p != null)
      {
         if( x.compareTo(p.item) <0)
            p = p.left;
         else if (x.compareTo(p.item) > 0)
            p = p.right;
         else  //found x
            return p;
      }
      return null;  //x is not found
   }
   
   private void assignHeight(BSTNode<E> x) {
	   BSTNode<E> tmp = x;
	   while(tmp != null) {
		   int a = (tmp.left == null) ? -1 : tmp.left.height;
		   int b = (tmp.right == null) ? -1 : tmp.right.height;
		   tmp.height = 1 + Math.max(a, b);
		   tmp = tmp.parent;
	   }
   }
   
             
     /***************** private remove helper methods ***************************************/
   
   private void deleteLeaf( BSTNode<E> t)
   {
      if ( t == root)
         root = null;
      else
      {
         BSTNode<E>  parent = t.parent;
         if( t.item.compareTo(parent.item) < 0)
            parent.left = null;
         else
            parent.right = null;
      }
      size--;
   }
    
   private void deleteNodeWithOnlyLeftChild( BSTNode<E> t)
   {
      if( t == root)
      {
         root = t.left; root.parent = null; //WAS WRONG t.left.parent = root;
      }
      else
      {
         BSTNode<E> parent = t.parent;
         if( t.item.compareTo( parent.item)< 0)
         {
            parent.left = t.left;
            t.left.parent = parent;
         }
         else
         {
            parent.right = t.left;
            t.left.parent = parent;           
         }
      }
      size--;      
   }                  
     
   private void deleteNodeWithOnlyRightChild( BSTNode<E> t)
   {
      if( t == root)
      {
         root = t.right; root.parent = null; // WAS WRONG t.right.parent = root;
      }
      else
      {
         BSTNode<E> parent = t.parent;
         if( t.item.compareTo(parent.item) < 0)
         {
            parent.left = t.right;
            t.right.parent = parent;
         }
         else
         {
            parent.right = t.right;
            t.right.parent = parent;           
         }
      }
      size--;
              
   }                  

   private BSTNode<E>  getSuccessorNode(BSTNode<E> t)
   {
     //only called when t.right != null
      BSTNode<E> parent = t;
      BSTNode<E> p = t.right;
      while (p.left != null)
      {
         parent = p; p = p.left; 
      }
      return p;
   }
     
   
               
   //private traversal methods      
           
         
   private void preOrder ( BSTNode<E> t)
   {
      if ( t != null)
      {
         System.out.print(t + " ");
         preOrder(t.left);
         preOrder(t.right);
      }
   }
     
   private void inOrder ( BSTNode<E> t)
   {
      if ( t != null)
      {
             
         inOrder(t.left);
         System.out.print(t + " " );
         inOrder(t.right);
      }
   }

   
}