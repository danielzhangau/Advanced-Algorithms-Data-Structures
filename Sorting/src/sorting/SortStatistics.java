package sorting;

public class SortStatistics {
		int size;
		long moveCount;
		long compareCount;
		long startTime;
		long executionTime;
		
		public SortStatistics() {
			super();
			reset();
		}
		public void setSize( int n ) {
			size = n;
		}
		public void incrMove() {
			moveCount++;
		}
		public void incrCompare() {
			compareCount++;
		}
		public void startTime() {
			startTime = System.nanoTime();
		}
		public void finishTime() {
			executionTime = System.nanoTime() - startTime;
		}
		public void reset() {
			moveCount = 0;
			compareCount = 0;
			startTime = 0;
			executionTime = 0;
		}
		public void printStats( String sortType ) {
			System.out.printf( sortType + " sort for %,6d takes %,14d compares %,14d moves %8d milliseconds%n",
					size, compareCount, moveCount, executionTime/1000000 );
		}
}
