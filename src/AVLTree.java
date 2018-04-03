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
		   int c = Math.abs(a) - Math.abs(b);
		   if(c > 1) {
			   System.out.println(c + " " + tmp.item);
			   restructure(tmp);
			   
		   }
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
		   int c = Math.abs(a) - Math.abs(b);
		   if( c > 1) 
			   return false;
		   
		   else
			   return (isHeightBalanced(t.left) && isHeightBalanced(t.right));
	   }
   }
    
   private AVLNode<E> restructure(AVLNode<E> t) {
	   
	   AVLNode<E> x = t;
	   
	   AVLNode<E> y = (x.left.height > x.right.height) ? x.left : x.right;
	   System.out.println();
	   AVLNode<E> z;
	   if(y.left.height > y.right.height)
		   z = y.left;
	   else if(y.left.height == y.right.height) {
		   if(y.item == x.left.item)
			   z = y.left;
		   else
			   z = y.right;
		   
	   }
	   else
		   z = y.right;
	   AVLNode<E> top = null;
	   
	  int c = whichCase(x, y, z);
	  if(c == 0) {
		  top = case_0(x,y,z);
		  
	  }
	  if(c == 1) {
		 top = case_1(x,y,z);
		  
	  }
	  if(c == 2) {
		 top = case_2(x,y,z);
	  }
	  if(c == 3) {
		 top = case_3(x,y,z);
	  }
	  
	  return top;
	  
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
   
  private int whichCase(AVLNode<E> x, AVLNode<E> y, AVLNode<E> z) {
	  if((x.left.item.compareTo(y.item) == 0) && (y.left.item.compareTo(z.item) == 0))
		  return 0;
	  if((x.right.item.compareTo(y.item) == 0) && (y.right.item.compareTo(z.item) == 0))
		  return 1;
	  if((x.left.item.compareTo(y.item) == 0) && (y.right.item.compareTo(z.item) == 0))
		  return 2;
	  if((x.right.item.compareTo(y.item) == 0) && (y.left.item.compareTo(z.item) == 0))
		  return 3;
	  else
		  return -1;
  }
  private AVLNode<E> case_0(AVLNode<E> x, AVLNode<E> y, AVLNode<E> z) {
	  AVLNode<E> t1 = z.left;
	  AVLNode<E> t2 = z.right;
	  AVLNode<E> t3 = y.right;
	  AVLNode<E> t4 = x.right;
	  AVLNode<E> parent = (x.parent == null) ? null : x.parent;
	  if(parent == null) 
		  root = y;
	  else {
		  if(parent.right.item == x.item) {
			  parent.right = y;
		  }
		  else {
			  parent.left = y;
		  }
	  }
	  y.left = z;
	  z.left = t1;
	  z.right = t2;
	  y.right = x;
	  x.left = t3;
	  x.right = t4;
	  x.parent = y;
	  z.parent = y;
	  return x.parent;
		  
	  
  }
  
  private AVLNode<E> case_1(AVLNode<E> x, AVLNode<E> y, AVLNode<E> z) {
	  AVLNode<E> t1 = x.left;
	  AVLNode<E> t2 = y.left;
	  AVLNode<E> t3 = z.left;
	  AVLNode<E> t4 = z.right;
	  AVLNode<E> parent = (x.parent == null) ? null : x.parent;
	  if(parent == null) 
		  root = y;
	  else {
		  if(parent.right.item == x.item) {
			  parent.right = y;
		  }
		  else {
			  parent.left = y;
		  }
	  }
	  y.left = x;
	  x.left = t1;
	  x.right = t2;
	  y.right = z;
	  z.left = t3;
	  z.right = t4;
	  x.parent = y;
	  z.parent = y;
	  return x.parent;
	  
  }
  private AVLNode<E> case_2(AVLNode<E> x, AVLNode<E> y, AVLNode<E> z) {
	  AVLNode<E> t1 = y.left;
	  AVLNode<E> t2 = z.left;
	  AVLNode<E> t3 = z.right;
	  AVLNode<E> t4 = x.right;
	  AVLNode<E> parent = (x.parent == null) ? null : x.parent;
	  if(parent == null) 
		  root = z;
	  else {
		  if(parent.right.item == x.item) {
			  parent.right = z;
		  }
		  else {
			  parent.left = z;
		  }
	  }
	  z.left = y;
	  y.left = t1;
	  y.right = t2;
	  z.right = x;
	  x.left = t3;
	  x.right = t4;
	  x.parent = z;
	  y.parent = z;
	  return x.parent;
  }
  private AVLNode<E> case_3(AVLNode<E> x, AVLNode<E> y, AVLNode<E> z) {
	  AVLNode<E> t1 = x.left;
	  AVLNode<E> t2 = z.left;
	  AVLNode<E> t3 = z.right;
	  AVLNode<E> t4 = y.right;
	  AVLNode<E> parent = (x.parent == null) ? null : x.parent;
	  if(parent == null) 
		  root = z;
	  else {
		  if(parent.right.item == x.item) {
			  parent.right = z;
		  }
		  else {
			  parent.left = z;
		  }
	  }
	  z.left = x;
	  x.left = t1;
	  x.right = t2;
	  z.right = y;
	  y.left = t3;
	  y.right = t4;
	  x.parent = z;
	  y.parent = z;
	  return x.parent;
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