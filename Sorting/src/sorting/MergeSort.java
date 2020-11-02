package sorting;

import java.util.LinkedList;
import java.util.List;

public class MergeSort implements Sorting {
	
	SortStatistics stats;
	
	private void merge(int[] A, int lo, int mid, int hi) {
		int i, j;
		List<Integer> L = new LinkedList<Integer>();
		/* A = A0 && ordered(A[lo..mid]) && ordered(A[mid+1..hi]) */
		i = lo;
		j = mid + 1;
		/*
		 * While both segments are non-empty select the least element and add to
		 * the list.
		 */
		while ((i <= mid) && (j <= hi)) {
			stats.incrCompare();
			stats.incrMove();
			if (A[i] <= A[j]) {
				L.add(A[i]);
				i = i + 1;
			} else {
				L.add(A[j]);
				j = j + 1;
			}
		}
		/* Move any elements left in the first segment to the list. */
		while (i <= mid) {
			stats.incrMove();
			L.add(A[i]);
			i = i + 1;
		}
		/*
		 * Move any elements left in the second segment to the list. Note that
		 * only one of this while loop and the one above actually do anything.
		 */
		while (j <= hi) {
			stats.incrMove();
			L.add(A[j]);
			j = j + 1;
		}
		/* Place the merged elements in the list back into the array. */
		for (i = lo; i <= hi; i++) {
			stats.incrMove();
			A[i] = L.remove(0);
		}
		/*
		 * ordered(A[lo..hi]) && items(A) = items(A0) && (A[0..lo-1] =
		 * A0[0..lo-1]) && (A[hi+1..HIGH(A)] = A0[hi+1..HIGH(A)])
		 */
	}

	private void mSort(int[] A, int lo, int hi) {
		int mid;
		/* (0 <= lo <= hi <= HIGH(A)) && (A = A0) */
		if (lo < hi) {
			mid = (lo + hi) / 2;
			mSort(A, lo, mid);
			/*
			 * ordered(A[lo..mid] && items(A) = items(A0) && (A[0..lo-1] =
			 * A0[0..lo-1]) && (A[mid+1..HIGH(A)] = A0[mid+1..HIGH(A)])
			 */
			mSort(A, mid + 1, hi);
			/*
			 * ordered(A[lo..mid]) && ordered(A[mid+1..hi]) && items(A) =
			 * items(A0) && (A[0..lo-1] = A0[0..lo-1]) && (A[hi+1..HIGH(A)] =
			 * A0[hi+1..HIGH(A)])
			 */
			merge(A, lo, mid, hi);
			/*
			 * ordered(A[lo..hi]) && items(A) = items(A0) && (A[0..lo-1] =
			 * A0[0..lo-1]) && (A[hi+1..HIGH(A)] = A0[hi+1..HIGH(A)])
			 */
		}
	}

	public void sort(int[] A, int n, SortStatistics stats) {
		/* Pre: A == A0 */
		this.stats= stats;
		mSort(A, 0, n - 1);
		/*
		 * Post: ordered(A[0..N-1]) && items(A) = items(A0) && (A[N..HIGH(A)] =
		 * A0[N..HIGH(A)])
		 */
	}


}
