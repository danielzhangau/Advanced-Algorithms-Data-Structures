package sorting;

public class SortTest {

	int A[];

	private SortTest(int n) {
		A = new int[n];
	}

	private void inOrder(int n) {
		for (int i = 0; i < n; i++) {
			A[i] = i;
		}
	}

	private void reverseOrder(int n) {
		for (int i = 0; i < n; i++) {
			A[i] = (n - 1) - i;
		}
	}

	private SortStatistics runTest(Sorting sort, int n, String sortType ) {
		SortStatistics stats = new SortStatistics();
		stats.setSize(n);
		stats.startTime();
		sort.sort(A, n, stats);
		stats.finishTime();
		for (int i = 0; i < n; i++) {
			if (A[i] != i) {
				System.out.println("Sort failed at " + i);
			}
		}
		stats.printStats( sortType );
		return stats;
	}

	private void inOrderTest(Sorting sort, int n) {
		inOrder(n);
		SortStatistics stats = runTest(sort, n, "In order" );
	}

	private void reverseOrderTest(Sorting sort, int n) {
		reverseOrder(n);
		SortStatistics stats = runTest(sort, n, "Reverse order" );
	}

	private void allSizes(Sorting sort, int baseSize) {
		inOrderTest(sort, 1*baseSize);
		inOrderTest(sort, 2*baseSize);
		inOrderTest(sort, 4*baseSize);
		inOrderTest(sort, 8*baseSize);
		reverseOrderTest(sort, 1*baseSize);
		reverseOrderTest(sort, 2*baseSize);
		reverseOrderTest(sort, 4*baseSize);
		reverseOrderTest(sort, 8*baseSize);
	}

	public static void main(String args[]) throws java.lang.Exception {
		int baseSize =10000;
		SortTest test = new SortTest(16*baseSize);
		System.out.println("Tests for quick sort:");
		test.allSizes(new QuickSort(), baseSize);
		System.out.println();
		System.out.println("Tests for quick sort:");
		test.allSizes(new QuickSort(), baseSize);
		System.out.println();
		System.out.println("Tests for insertion sort:");
		test.allSizes(new InsertionSort(), baseSize);
		System.out.println();
		System.out.println("Tests for merge sort:");
		test.allSizes(new MergeSort(), baseSize);
	}
}
