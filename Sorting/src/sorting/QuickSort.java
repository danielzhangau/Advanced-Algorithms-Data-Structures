package sorting;
public class QuickSort implements Sorting {
	
	SortStatistics stats;

	private void swap(int[] A, int i, int j) {
		int t;
		t = A[i];
		A[i] = A[j];
		A[j] = t;
		stats.incrMove();
	}

	private int partition(int[] A, int lo, int hi) {
		int x;
		/*
		 * Pre: (0 <= lo < hi < A.length) && (A = A0) 
		 * Partitioning on the middle element of the array ensures quick sort
		 * works efficiently for already sorted arrays. To achieve that with
		 * this partitioning algorithm we swap the middle and last elements
		 * before beginning the partition proper.
		 * 
		 * If this swap is omitted, the partition will still work correctly, but
		 * the algorithm will have its worst-case behaviour for the already
		 * ordered array.
		 */

		swap(A, (lo + hi) / 2, hi);

		/* Partition on A[hi] -- was the middle element */
		x = A[hi];
		int part = lo;
		for (int j = lo; j < hi; j++) {
			/* A[lo..part-1] <= x < A[part..j-1] */
			stats.incrCompare();
			if (A[j] <= x) {
				swap(A, part, j);
				part = part + 1;
			}
			/* A[lo..part-1] <= x < A[part..j] */
		}
		/* A[lo..part-1] <= x < A[part..hi-1] */
		swap(A, part, hi);
		/*
		 * lo <= part <= hi && A[lo..part-1] <= A[part] < A[part+1..hi] &&
		 * items(A) = items(A0) && (A[0..lo-1] = A0[0..lo-1]) &&
		 * (A[hi+1..] = A0[hi+1..])
		 */
		return part;
	}

	public void qSort(int[] A, int lo, int hi) {
		/* (0 <= lo <= hi < A.length) && (A = A0) */
		if (lo < hi) {
			/* 0 <= lo < hi < A.length */
			int part = partition(A, lo, hi);
			/*
			 * lo <= part <= hi && A[lo..part-1] <= A[part] <= A[part+1..hi] &&
			 * items(A) = items(A0) && (A[0..lo-1] = A0[0..lo-1]) &&
			 * (A[hi+1..] = A0[hi+1..])
			 */
			qSort(A, lo, part - 1);
			/*
			 * ordered(A[lo..part-1] && items(A) = items(A0) && A[lo..part-1]
			 * <= A[part] <= A[part+1..hi] && (A[0..lo-1] = A0[0..lo-1]) &&
			 * (A[hi+1..] = A0[hi+1..])
			 */
			qSort(A, part + 1, hi);
			/*
			 * ordered(A[lo..part-1]) && ordered(A[part+1..hi]) &&
			 * A[lo..part-1] <= A[part] <= A[part+1..hi] && items(A) = items(A0)
			 * && (A[0..lo-1] = A0[0..lo-1]) && (A[hi+1..] = A0[hi+1..])
			 */
			/*
			 * Therefore: ordered(A[lo..hi]) && items(A) = items(A0) &&
			 * (A[0..lo-1] = A0[0..lo-1]) && (A[hi+1..] = A0[hi+1..])
			 */
		}
	}

	public void sort(int[] A, int n, SortStatistics stats) {
		/* Pre: n <= A.length && A = A0 */
		/*
		 * Post: ordered(A[0..n-1]) && items(A) = items(A0) && (A[n..] =
		 * A0[n..])
		 */
		this.stats = stats;
		qSort(A, 0, n - 1);
		/*
		 * Post: ordered(A[0..n-1]) && items(A) = items(A0) && 
		 * (A[n..] = A0[n..])
		 */
	}

}