// This implementation is of a MinHeap, where the minimum value is the highest priority.
public class BinaryHeap {

	private int[] arr;
	private int size;
	
	// Constructor
	public BinaryHeap() {
		this.arr = new int[10];
		this.size = 0;
	}
	
	// Main functionality to add priority numbers to the heap.
	public void add(int n) {
		// Check and grow the the size of the heap if more spots are needed in this array-based implementation.
		if(arr.length == size) {
			grow_heap();
		}
		
		// Placing the priority number at the first open spot, then incrementing tracker size.
		arr[size++] = n;
		
		// Swap priority numbers while the inserted number n has a higher priority (lower value) than its parent. 
		int index = size - 1;
		while(arr[index] < arr[parent(index)] && index > 0) {
			int temp = arr[index];
			arr[index] = arr[parent(index)];
			arr[parent(index)] = temp;
			index = parent(index);
		}
	}
	
	
	// Main functionality to remove the highest priority number from the heap.
	// This will be the lowest integer value and the root of the tree.
	public int remove() {
		
		// Throws an exception if the heap is empty.
		if(size == 0) {
			throw new RuntimeException("Trying to call remove on an empty heap.");
		}
		
		// Store the value to be returned.
		int removed = arr[0];
	
		// Adjust the tree.
		arr[0] = arr[--size];
		if(size != 0) {
			siftdown(0);
		}
		return removed;
	}
	
	// Sift the integer at the specified position down the tree recursively to its proper position.
	private void siftdown(int position) {
		if(left_child(position) >= size) {
			return;
		}
		int child = left_child(position);
		if(right_child(position) < size && arr[right_child(position)] < arr[child]) {
			child = right_child(position);
		}
		if(arr[child] < arr[position]) {
			int temp = arr[child];
			arr[child] = arr[position];
			arr[position] = temp;
			siftdown(child);
		}
		
	}

	// Function to handle and double the size of the heap.
	private void grow_heap() {
		int[] temp = arr;
		arr = new int[size*2];
		java.lang.System.arraycopy(temp, 0, arr, 0, size / 2);	
	}
	
	// Returns the array-index of the parent node given an index.
	private int parent(int index) {
		return (index-1) / 2;
	}
	
	// Returns the array-index of the left child given an index.
	private int left_child(int index) {
		return (index*2) + 1;
	}
	
	// Returns the array-index of the right child given an index.
	private int right_child(int index) {
		return (index*2) + 2;
	}
	
}
