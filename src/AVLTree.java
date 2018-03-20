//Muhammad Hayat
//Comp 282
// Project 4
// 3/15/18

class AVLNode<E >
{
   E item;   
   AVLNode<E> left;
   AVLNode<E> right;
   AVLNode<E> parent;
   int height;
  
   public AVLNode ( E  x)
   {
      item = x; left = null; right = null; parent = null; this.height = 0;
      
   }
   
   public AVLNode (E x , AVLNode<E> left, AVLNode<E> right, AVLNode<E> parent)
   {
      item = x; 
      this.left = left; this.right = right; this.parent = parent; this.height = 0;  
   }
   
   public String toString()
   {
      return "(i:" + item + ", h: " + height + ")";
   }
}

/*----------------class AVLTree ---------------------------*/
public class AVLTree<E extends Comparable<E>>
{
   private AVLNode<E> root;
   private int size;

   
   
   public AVLTree()
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
         root = new AVLNode(x, null, null, root);
         size++;
         return true;
      }    
       
      AVLNode<E> parent = null;
      AVLNode<E>  p = root;
      
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
      AVLNode<E> insertedNode = new AVLNode<E>(x, null, null, parent);
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
      AVLNode<E> p = find(x, root);
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
         AVLNode<E> succ =  getSuccessorNode(p);;
        
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
		   AVLNode<E> tmp = this.root;
		   while(tmp.left != null) {
			   tmp = tmp.left;
		   }
		   return tmp.item;
	   }
   }
   
   public E findMax() {
	   if(root == null) 
		   return null;
	   else {
		   AVLNode<E> tmp = root;
		   while(tmp.right != null) {
			   tmp = tmp.right;
		   }
		   return tmp.item;
	   }
   }
   
   public E removeMin() {
	   E min = findMin();
	   remove(min);
	   return min;
   }
   
   public E removeMax() {
	   E max = findMax();
	   remove(max);
	   return max;
   }
   
   public boolean isHeightBalanced() {
	   return isHeightBalanced(root);
   }
   
 
  /********************private methods ******************************/
  
        

   private AVLNode<E> find(E x, AVLNode<E> t)  
   {
      AVLNode<E> p = t;
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
   
   private void assignHeight(AVLNode<E> x) {
	   AVLNode<E> tmp = x;
	   while(tmp != null) {
		   int a = (tmp.left == null) ? -1 : tmp.left.height;
		   int b = (tmp.right == null) ? -1 : tmp.right.height;
		   tmp.height = 1 + Math.max(a, b);
		   tmp = tmp.parent;
	   }
   }
   
   private boolean isHeightBalanced(AVLNode t) {
	   if(t == null) {
		   return true;
	   }
	   else {
		   int a = (t.right == null) ? -1 : t.right.height;
		   int b = (t.left == null) ? -1 : t.left.height;
		   int c = Math.abs((a-b));
		   if( c > 1) 
			   return false;
		   
		   else
			   return (isHeightBalanced(t.left) && isHeightBalanced(t.right));
	   }
   }
   
             
     /***************** private remove helper methods ***************************************/
   
   private void deleteLeaf( AVLNode<E> t)
   {
      if ( t == root)
         root = null;
      else
      {
         AVLNode<E>  parent = t.parent;
         if( t.item.compareTo(parent.item) < 0)
            parent.left = null;
         else
            parent.right = null;
      }
      size--;
   }
    
   private void deleteNodeWithOnlyLeftChild( AVLNode<E> t)
   {
      if( t == root)
      {
         root = t.left; root.parent = null; //WAS WRONG t.left.parent = root;
      }
      else
      {
         AVLNode<E> parent = t.parent;
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
     
   private void deleteNodeWithOnlyRightChild( AVLNode<E> t)
   {
      if( t == root)
      {
         root = t.right; root.parent = null; // WAS WRONG t.right.parent = root;
      }
      else
      {
         AVLNode<E> parent = t.parent;
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

   private AVLNode<E>  getSuccessorNode(AVLNode<E> t)
   {
     //only called when t.right != null
      AVLNode<E> parent = t;
      AVLNode<E> p = t.right;
      while (p.left != null)
      {
         parent = p; p = p.left; 
      }
      return p;
   }
     
   
               
   //private traversal methods      
           
         
   private void preOrder ( AVLNode<E> t)
   {
      if ( t != null)
      {
         System.out.print(t + " ");
         preOrder(t.left);
         preOrder(t.right);
      }
   }
     
   private void inOrder ( AVLNode<E> t)
   {
      if ( t != null)
      {
             
         inOrder(t.left);
         System.out.print(t + " " );
         inOrder(t.right);
      }
   }

   
}