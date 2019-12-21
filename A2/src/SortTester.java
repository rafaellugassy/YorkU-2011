import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SortTester
{

	public static void main(String[] args)
	{
		// takes too long with N = 100000, and 1000000
		int[] N =
		{ 10, 100, 1000, 10000/*,100000 , 1000000 */ };
		for (int i = 0; i < N.length; i++)
		{
			System.out.println("N = " + N[i] + ":");
			Random r = new Random();
			Integer[] arr = new Integer[N[i]];

			for (int g = 0; g < N[i]; g++)
			{
				arr[g] = r.nextInt(N[i]);
			}
			Integer[] arrCopy = arr.clone();
			
			// variables to store the time
			long startTime, endTime, total;
			
			startTime = System.currentTimeMillis();
			TreeSort.sort(new A2BSTree<Integer>(), arr);
			endTime = System.currentTimeMillis();
			total = endTime - startTime;
			System.out.println("BST\t\t" + total + "ms");

			startTime = System.currentTimeMillis();
			TreeSort.sort(new A2AVLTree<Integer>(), arrCopy);
			endTime = System.currentTimeMillis();
			total = endTime - startTime;
			System.out.println("AVL\t\t" + total + "ms");

			for (int g = 0; g < N[i]; g++)
			{
				arr[g] = -g;
			}
			arrCopy = arr.clone();

			startTime = System.currentTimeMillis();
			TreeSort.sort(new A2BSTree<Integer>(), arr);
			endTime = System.currentTimeMillis();
			total = endTime - startTime;
			System.out.println("BST(rev-sorted)\t" + total + "ms");

			startTime = System.currentTimeMillis();
			TreeSort.sort(new A2AVLTree<Integer>(), arrCopy);
			endTime = System.currentTimeMillis();
			total = endTime - startTime;
			System.out.println("AVL(rev-sorted)\t" + total + "ms");

		}
	}
}
