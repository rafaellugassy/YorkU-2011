import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

public class MapTester
{

	public static void main(String[] args)
	{
		// maps
		TreeMap<Integer, Integer> integerTreeMap = new TreeMap<Integer, Integer>();
		TreeMap<String, String> stringTreeMap = new TreeMap<String, String>();
		HashMap<Integer, Integer> integerHashMap = new HashMap<Integer, Integer>();
		HashMap<String, String> stringHashMap = new HashMap<String, String>();

		// time variables
		long startTime, endTime, totalTime;
		double[] insertTreeTime = new double[2], insertHashTime = new double[2], keyPresentTreeTime = new double[2],
				keyPresentHashTime = new double[2], valuePresentTreeTime = new double[2],
				valuePresentHashTime = new double[2], keyAbsentTreeTime = new double[2],
				keyAbsentHashTime = new double[2], valueAbsentTreeTime = new double[2],
				valueAbsentHashTime = new double[2];

		// random Integers/Strings to do steps with
		Integer[] searchInteger, randomInteger;
		String[] searchString, randomString;

		// takes over 30s with 100,000 and 1,000,000
		int[] N =
		{ 10, 100, 1000, 10000 /* , 100000, 1000000 */ };

		for (Integer n : N)
		{
			// create an array of 2 N integers
			randomInteger = new Integer[2 * n];
			Random rand = new Random();
			for (int i = 0; i < 2 * n; i++)
				randomInteger[i] = rand.nextInt(n * 10);

			// create an array of 2 N Strings
			randomString = new String[2 * n];
			for (int i = 0; i < 2 * n; i++)
				randomString[i] = generateString(rand);

			/** insert half of the same integers/Strings **/
			{
				// insert for integerTreeMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n; i++)
					integerTreeMap.put(randomInteger[i], randomInteger[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				insertTreeTime[0] = (totalTime / (double) n);

				// insert for integerHashMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n; i++)
					integerHashMap.put(randomInteger[i], randomInteger[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				insertHashTime[0] = (totalTime / (double) n);

				// insert for stringTreeMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n; i++)
					stringTreeMap.put(randomString[i], randomString[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				insertTreeTime[1] = (totalTime / (double) n);

				// insert for stringHashMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n; i++)
					stringHashMap.put(randomString[i], randomString[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				insertHashTime[1] = (totalTime / (double) n);
			}
			/** end of insert for Maps **/

			// get a tenth of the numbers to search from
			searchInteger = new Integer[n / 10];
			for (int i = 0; i < n / 10; i++)
				searchInteger[i] = randomInteger[rand.nextInt(n)];

			// get a tenth of the Strings to search from
			searchString = new String[n / 10];
			for (int i = 0; i < n / 10; i++)
				searchString[i] = randomString[rand.nextInt(n)];

			/** checking contains key on first half **/
			{
				// contains key for integerTreeMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					integerTreeMap.containsKey(searchInteger[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				keyPresentTreeTime[0] = (totalTime / (double) (n / 10));

				// contains key for integerHashMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					integerHashMap.containsKey(searchInteger[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				keyPresentHashTime[0] = (totalTime / (double) (n / 10));

				// contains key for StringTreeMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					stringTreeMap.containsKey(searchString[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				keyPresentTreeTime[1] = (totalTime / (double) (n / 10));

				// contains key for StringHashMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					stringHashMap.containsKey(searchString[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				keyPresentHashTime[1] = (totalTime / (double) (n / 10));
			}
			/** end of first half contains key **/

			/** checking contains value on first half **/
			{
				// contains value for integerTreeMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					integerTreeMap.containsValue(searchInteger[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				valuePresentTreeTime[0] = (totalTime / (double) (n / 10));

				// contains value for integerHashMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					integerHashMap.containsValue(searchInteger[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				valuePresentHashTime[0] = (totalTime / (double) (n / 10));

				// contains value for stringTreeMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					stringTreeMap.containsValue(searchString[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				valuePresentTreeTime[1] = (totalTime / (double) (n / 10));

				// contains value for stringHashMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					stringHashMap.containsValue(searchString[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				valuePresentHashTime[1] = (totalTime / (double) (n / 10));
			}
			/** end of first half contains value **/

			// get a tenth of the second half numbers to search from
			searchInteger = new Integer[n / 10];
			for (int i = 0; i < n / 10; i++)
				searchInteger[i] = randomInteger[rand.nextInt(n) + n];

			// get a tenth of the second half Strings to search from
			searchString = new String[n / 10];
			for (int i = 0; i < n / 10; i++)
				searchString[i] = randomString[rand.nextInt(n) + n];

			/** checking contains key on elements not in first half **/
			{
				// contains key for integerTreeMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					integerTreeMap.containsKey(searchInteger[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				keyAbsentTreeTime[0] = (totalTime / (double) (n / 10));

				// contains key for integerHashMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					integerHashMap.containsKey(searchInteger[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				keyAbsentHashTime[0] = (totalTime / (double) (n / 10));

				// contains key for StringTreeMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					stringTreeMap.containsKey(searchString[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				keyAbsentTreeTime[1] = (totalTime / (double) (n / 10));

				// contains key for StringHashMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					stringHashMap.containsKey(searchString[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				keyAbsentHashTime[1] = (totalTime / (double) (n / 10));
			}
			/** end of contains key on non-included elements **/

			/** checking contains value on elements not in the first half **/
			{
				// contains value for integerTreeMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					integerTreeMap.containsValue(searchInteger[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				valueAbsentTreeTime[0] = (totalTime / (double) (n / 10));

				// contains value for integerHashMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					integerHashMap.containsValue(searchInteger[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				valueAbsentHashTime[0] = (totalTime / (double) (n / 10));

				// contains value for stringTreeMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					stringTreeMap.containsValue(searchString[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				valueAbsentTreeTime[1] = (totalTime / (double) (n / 10));

				// contains value for stringHashMap, while recording time
				startTime = System.currentTimeMillis();
				for (int i = 0; i < n / 10; i++)
					stringHashMap.containsValue(searchString[i]);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				valueAbsentHashTime[1] = (totalTime / (double) (n / 10));
			}
			/** end of contains value on non-included elements **/

			/** print all values **/
			{
				System.out.println("N = " + n + " (times are per operation):");
				System.out.println("\t\t\tStrings\t\tNumbers");
				/* tree map times */
				// insert times
				System.out.println("TreeMap, put\t\t" + insertTreeTime[1] + "ms\t\t" + insertTreeTime[0] + "ms");

				// key/value check present
				System.out.println("(when key/value present)");
				System.out.println(
						"TreeMap, containsKey\t" + keyPresentTreeTime[1] + "ms\t\t" + keyPresentTreeTime[0] + "ms");
				System.out.println("TreeMap, containsValue\t" + valuePresentTreeTime[1] + "ms\t\t"
						+ valuePresentTreeTime[0] + "ms");

				// key/value check absent
				System.out.println("(when key/value absent)");
				System.out.println(
						"TreeMap, containsKey\t" + keyAbsentTreeTime[1] + "ms\t\t" + keyAbsentTreeTime[0] + "ms");
				System.out.println(
						"TreeMap, containsValue\t" + valueAbsentTreeTime[1] + "ms\t\t" + valueAbsentTreeTime[0] + "ms");

				System.out.println("");

				/* hash map times */
				// insert times
				System.out.println("HashMap, put\t\t" + insertHashTime[1] + "ms\t\t" + insertHashTime[0] + "ms");

				// key/value check present
				System.out.println("(when key/value present)");
				System.out.println(
						"HashMap, containsKey\t" + keyPresentHashTime[1] + "ms\t\t" + keyPresentHashTime[0] + "ms");
				System.out.println("HashMap, containsValue\t" + valuePresentHashTime[1] + "ms\t\t"
						+ valuePresentHashTime[0] + "ms");

				// key/value check absent
				System.out.println("(when key/value absent)");
				System.out.println(
						"HashMap, containsKey\t" + keyAbsentHashTime[1] + "ms\t\t" + keyAbsentHashTime[0] + "ms");
				System.out.println(
						"HashMap, containsValue\t" + valueAbsentHashTime[1] + "ms\t\t" + valueAbsentHashTime[0] + "ms");

				// print two new lines
				System.out.println("\n");
			}
			/** end of print **/
		}
	}

	/**
	 * implementation source (minor edit):
	 * https://stackoverflow.com/questions/2863852/how-to-generate-a-random-
	 * string-in-java
	 **/
	public static String generateString(Random rng)
	{
		// random max length of 32
		int length = rng.nextInt(32);
		String characters = "abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()_+-=[]{};:\"\'\\|,./<>?";
		char[] text = new char[length];
		for (int i = 0; i < length; i++)
		{
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}

}
