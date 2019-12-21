import java.util.Iterator;

public class TreeSort{
	/** Sorts an array using TreeSort with a balanced BST implementation 
	 * @param a an array to sort
	 */
	public static <E> void sort( E[] a){
		Tree <E> tree = new A2AVLTree<>();
		sort(tree, a);
	}
	
	/**
	 * Sorts an array using TreeSort with a specified BST
	 * @param tree tree to use
	 * @param a an array to sort
	 */
	public static <E> void sort(Tree <E> tree, E[] a){
		// go through each element of the array, and add it to the tree
		for (E e : a)
			tree.add(e);
		
		// get the iterator for the tree, which will now be in sorted order
		Iterator<E> it = tree.iterator();
		
		// change the array's values to be the sorted order's values
		for (int i = 0; i < a.length && it.hasNext(); i++)
			a[i] = it.next();
	}
}
