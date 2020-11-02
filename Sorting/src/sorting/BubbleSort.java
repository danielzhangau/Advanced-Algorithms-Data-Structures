package sorting;

public class BubbleSort implements Sorting {

	public void sort(int[] A, int n, SortStatistics stats) {
		// Pre: A == A0
		for (int i = n - 1; i >= 1; i--) {
			for (int j = 1; j <= i; i++) {
				stats.incrCompare();
				if (A[j - 1] > A[j]) {
					// Swap A[j-1] and A[j]
					stats.incrMove();
					int temp = A[j - 1];
					A[j - 1] = A[j];
					A[j] = temp;
				}
			}
		}
		/* Post: ordered(A) && (items(A) = items(A0)) */
	}

}
