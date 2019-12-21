import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListTester {

	private static int[] N = { 10, 100, 1000, 10000, 100000 };
	private static int counter = 0;

	private static class Vector3 {
		public long start, end, rnd;

		public Vector3(int start, int end, int rnd) {
			this.start = start;
			this.end = end;
			this.rnd = rnd;
		}
	}

	public static void main(String[] args) {
		for (counter = 0; counter < N.length; counter++) {
			List<Integer> numbers = new ArrayList<Integer>();
			Random r = new Random();
			int randomLocation = r.nextInt(N[counter] - 2) + 1;
			List<Integer> values = new ArrayList<Integer>();
			for (int i = 0; i < N[counter]; i++)
				numbers.add(r.nextInt(10 * N[counter] + 1));
			List<Integer> tempNumbers = new ArrayList<Integer>(numbers);
			for (int i = 0; i < N[counter]; i++) {
				int rand = r.nextInt(10 * N[counter] + 1);
				values.add(rand);
			}

			System.out.println("N = " + N[counter]
					+ ": ms to Ins.	start,	end,	rnd;	Rmv. start,	end,	rnd;	Rmv. by value");

			makeRow(new ArrayList<Integer>(), numbers, randomLocation, values, "ArrayList");

			makeRow(new A1ArrayList<Integer>(), numbers, randomLocation, values, "A1ArrayList");

			makeRow(new LinkedList<Integer>(), numbers, randomLocation, values, "LinkedList");

			makeRow(new A1LinkedList<Integer>(), numbers, randomLocation, values, "A1LinkedList");
		}

	}

	private static void makeRow(List<Integer> list, List<Integer> numbers, int randomLoc, List<Integer> values,
			String type) {
		Vector3 aValues = partA(list, numbers, randomLoc);
		Vector3 bValues = partB(list, numbers, randomLoc);
		long cValue = partC(list, values);

		System.out.println(type + "\t\t" + aValues.start + "\t" + aValues.end + "\t" + aValues.rnd + "\t\t"
				+ bValues.start + "\t" + bValues.end + "\t" + bValues.rnd + "\t\t" + cValue);

	}

	private static long partC(List<Integer> list, List<Integer> values) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < values.size(); i++) {
			list.remove((Integer) values.get(i));
		}
		long totalTime = System.currentTimeMillis() - startTime;
		return totalTime;
	}

	private static Vector3 partB(List<Integer> list, List<Integer> numbers, int randomLoc) {
		Vector3 result = new Vector3(0, 0, 0);

		// first we start with the beginning
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < numbers.size(); i++) {
			list.remove(0);
		}
		long totalTime = System.currentTimeMillis() - startTime;
		result.start = totalTime;
		// add it back for later
		for (int i = 0; i < numbers.size(); i++)
			list.add(numbers.get(i));

		// now for the end
		startTime = System.currentTimeMillis();
		while (list.size() != 0) {
			list.remove(list.size() - 1);
		}
		totalTime = System.currentTimeMillis() - startTime;
		result.end = totalTime;
		// add them back for later
		for (int i = 0; i < numbers.size(); i++)
			list.add(numbers.get(i));

		// now for the random spot
		// assuming if the random spot is too large it will go to the max
		startTime = System.currentTimeMillis();
		for (int i = randomLoc; i < numbers.size(); i++)
			list.remove(randomLoc);
		while (list.size() != 0){
			list.remove(list.size() - 1);
		}
		totalTime = System.currentTimeMillis() - startTime;
		result.rnd = totalTime;
		// add them back for later
		for (int i = 0; i < numbers.size(); i++)
			list.add(numbers.get(i));

		return result;
	}

	private static Vector3 partA(List<Integer> list, List<Integer> numbers, int randomLoc) {
		Vector3 result = new Vector3(0, 0, 0);

		// System.out.println("check");
		// first we start with the beginning
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < numbers.size(); i++)
			list.add(0, numbers.get(i));
		long totalTime = System.currentTimeMillis() - startTime;
		result.start = totalTime;
		// now to reset
		list.clear();
		
		
		// now for the end
		startTime = System.currentTimeMillis();
		for (int i = 0; i < numbers.size(); i++)
			list.add(list.size(), numbers.get(i));
		totalTime = System.currentTimeMillis() - startTime;
		result.end = totalTime;
		// now to reset
		list.clear();
		
		// now for the random spot
		startTime = System.currentTimeMillis();
		// assuming that the spot will be max until the spot is available
		for (int i = 0; i < randomLoc; i++)
			list.add(list.size(), numbers.get(i));
		for (int i = randomLoc; i < numbers.size(); i++)
			list.add(randomLoc, numbers.get(i));
		totalTime = System.currentTimeMillis() - startTime;
		result.rnd = totalTime;
		// now to reset
		list.clear();
		
		// adding everything back in order
		for (int i = 0; i < numbers.size(); i++)
			list.add(i, numbers.get(i));

		return result;
	}

}
