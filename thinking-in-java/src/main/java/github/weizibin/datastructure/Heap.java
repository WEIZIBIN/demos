package github.weizibin.datastructure;

public class Heap<T extends Comparable<? super T>> {

    private T[] heap;
    private int size;

    public Heap(int initialCapacity) {
        heap = (T[]) new Comparable[initialCapacity];
        size = 0;
    }

    public void insert(T value) {
        int index = size++;
        while (index > 0) {
            // min heap
            if (value.compareTo(heap[parentIndex(index)]) < 0) {
                heap[index] = heap[parentIndex(index)];
                index = parentIndex(index);
            } else {
                break;
            }
        }
        heap[index] = value;
    }

    public T get() {
        if (isEmpty()) {
            return null;
        }

        T result = heap[0];
        size--;
        T value = heap[size];
        heap[size] = null;

        heap[0] = value;
        down(0);

        return result;
    }

    public boolean delete(T value) {
        if (isEmpty()) {
            return false;
        }

        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (value.equals(heap[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }

        size--;
        value = heap[size];
        heap[size] = null;
        heap[index] = value;

        down(index);

        return true;
    }

    private void down(int index) {
        T value = heap[index];

        T minValue = value;
        int minChildIndex = -1;
        T leftChildValue = checkIndex(leftChildIndex(index)) ? heap[leftChildIndex(index)] : null;
        T rightChildValue = checkIndex(rightChildIndex(index)) ? heap[rightChildIndex(index)] : null;
        if (leftChildValue != null && leftChildValue.compareTo(minValue) < 0) {
            minValue = leftChildValue;
            minChildIndex = leftChildIndex(index);
        }
        if (rightChildValue != null && rightChildValue.compareTo(minValue) < 0) {
            minValue = rightChildValue;
            minChildIndex = rightChildIndex(index);
        }
        if (minChildIndex != -1) {
            heap[index] = minValue;
            heap[minChildIndex] = value;
            down(minChildIndex);
        }
    }

    private boolean checkIndex(int index) {
        return index < size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    public static int leftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    public static int rightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

}
