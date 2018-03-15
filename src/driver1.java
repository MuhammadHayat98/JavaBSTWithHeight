
public class driver1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST<Integer> itree = new BST();
		BST<String> stree = new BST();
		for(int i = 0; i < 5000;  i++) {
			int a = (int)(Math.random() * 1000000);
			itree.insert(a);
		}
		System.out.print(itree.getHeight());
		
		
		
		

	}

}
