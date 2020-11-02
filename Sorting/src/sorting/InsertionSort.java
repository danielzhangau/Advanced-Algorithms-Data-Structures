package sorting;

public class InsertionSort implements Sorting {
	
	/* Insertion sort based on CLRS p18 (3rd) */
	public void sort(int[] A, int n, SortStatistics stats) {
		// Pre: A == A0
		for (int j = 1; j < n; j++) {
			int key = A[j];
			stats.incrMove();
			// Insert A[j] into the sorted sequence A[0..j-1]
			int i = j - 1;
			while (i >= 0 && A[i] > key) {
				stats.incrCompare();
				stats.incrMove();
				A[i + 1] = A[i];
				i = i - 1;
			}
			stats.incrCompare();
			A[i + 1] = key;
			stats.incrMove();
		}
		/* Post: ordered(A) && (items(A) = items(A0)) */
	}

}
